package com.ezzy.ishopp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    //WIDGETS
    private Button sendButton;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //hide the action bar
        getSupportActionBar().hide();

        sendButton = findViewById(R.id.sendButton);
        emailEditText = findViewById(R.id.emailEditText);

        init();
    }

    private void init() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * TODO: handle firebase authentication reset password
                * */
            }
        });
    }

}