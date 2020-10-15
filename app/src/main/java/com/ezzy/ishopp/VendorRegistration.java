package com.ezzy.ishopp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class VendorRegistration extends AppCompatActivity {
    private EditText mBusinessName;
    private EditText mBusinessLocation;
    private Spinner mBusinessType;
    private EditText mBusinessDescription;
    private EditText MpesaAccountName;
    private EditText MpesaPaybillNumber;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_registration);

        mBusinessName = findViewById(R.id.mBusinessName);
        mBusinessLocation = findViewById(R.id.BusinessLocation);
        mBusinessType = findViewById(R.id.spinner);
        MpesaAccountName = findViewById(R.id.MpesaAccName);
        MpesaPaybillNumber = findViewById(R.id.MpesaPaybill);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mBusinessType.setPrompt("Business Type");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Select Business Type");
        arrayList.add("Food");
        arrayList.add("Clothes");
        arrayList.add("Beauty");

        arrayList.add("Food");
        arrayList.add("Shoes");
        arrayList.add("Electronics");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
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

    }
}