package com.ezzy.ishopp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ezzy.ishopp.models.Vendor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.util.ArrayList;

public class VendorRegistration extends AppCompatActivity {
    private EditText mBusinessName;
    private EditText mBusinessLocation;
    private Spinner mBusinessType;
    private EditText mBusinessDescription;
    private EditText MpesaAccountName;
    private EditText MpesaPaybillNumber;
    private Toolbar mToolbar;

    private Button mRegisterVendor;

    private final FirebaseDatabase database = new FirebaseManager().database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_registration);

        mBusinessName = findViewById(R.id.mBusinessName);
        mBusinessLocation = findViewById(R.id.BusinessLocation);
        mBusinessType = findViewById(R.id.spinner);
        MpesaAccountName = findViewById(R.id.MpesaAccName);
        MpesaPaybillNumber = findViewById(R.id.MpesaPaybill);

        mRegisterVendor = findViewById(R.id.registerVendor);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mBusinessType.setPrompt("Vendor Type");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Select Vendor Type");

        mBusinessType.setPrompt("Business Type");
        ArrayList<String> arrayListl= new ArrayList<>();
        arrayListl.add("Select Business Type");

        arrayListl.add("Food");
        arrayListl.add("Clothes");
        arrayListl.add("Beauty");

        arrayListl.add("Food");
        arrayListl.add("Shoes");
        arrayListl.add("Electronics");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListl);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBusinessType.setAdapter(arrayAdapter);
        mBusinessType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }


            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });


        mRegisterVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveVendorDetails();
            }
        });

    }

    private void saveVendorDetails(){
        DatabaseReference mDatabaseReference = database.getReference();
        final Vendor mVendor = new Vendor();
        mVendor.setBusiness_name(mBusinessName.getText().toString());
        mVendor.setBusiness_description(mBusinessDescription.getText().toString());
        mVendor.setBusiness_category(mBusinessType.getSelectedItem().toString());
        mVendor.setBusiness_location(mBusinessLocation.getText().toString());
        mVendor.setMpesa_account_name(MpesaAccountName.getText().toString());
        mVendor.setMpesa_paybill_number(MpesaPaybillNumber.getText().toString());
        mDatabaseReference.child("vendors")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(mVendor)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            makeToast(getResources().getString(R.string.vendor_reg_successful));
                            Intent vendorPageIntent = new Intent(VendorRegistration.this, VendorPage.class);
                            vendorPageIntent.putExtra("vendor", mVendor);
                            startActivity(vendorPageIntent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                makeToast(getResources().getString(R.string.vendor_reg_error));
            }
        });
    }

    private void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}