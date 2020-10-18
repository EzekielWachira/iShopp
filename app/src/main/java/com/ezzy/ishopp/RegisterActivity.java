package com.ezzy.ishopp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ezzy.ishopp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    //WIDGETS
    private EditText nameEditText, emailEditText, passwordEditText, repeatPasswordEditText;
    private Button registerButton;
    private TextView signInTextView;
    private final FirebaseAuth firebaseAuth = new FirebaseManager().auth;
    private final FirebaseDatabase database = new FirebaseManager().database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //hide the action bar
//        getSupportActionBar().hide();



        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        repeatPasswordEditText = findViewById(R.id.repeatPasswordEditText);
        registerButton = findViewById(R.id.registerButton);
        signInTextView = findViewById(R.id.signInTextView);

        init();
    }

    private void init() {
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String repeatPassword = repeatPasswordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter Your name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Your password is too short!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(repeatPassword)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setTitle("Requesting account");
                progressDialog.setMessage(getString(R.string.please_wait));
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
               firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Toast.makeText(getApplicationContext(), "Successfully created", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                if (!task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_LONG).show();

                                } else {
                                    progressDialog.dismiss();
                                    saveUserData();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    private void saveUserData(){
        DatabaseReference mDatabaseReference = database.getReference();
        User mUser = new User();
        mUser.setName(nameEditText.getText().toString());
        mUser.setLocation("Nairobi");
        mUser.setEmail(emailEditText.getText().toString());
        mUser.setPhone("123456789");
        mUser.setImage_url("");
        mDatabaseReference.child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(mUser);
    }

}