package com.phonebook.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ContactViewActivity extends AppCompatActivity {

    private TextView firstNameTxt, lastNameTxt, emailTxt, phoneTxt, addressTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        //gets all details from the bundle it receives when it is opened
        String firstName = this.getIntent().getExtras().getString("firstName");
        String lastName = this.getIntent().getExtras().getString("lastName");
        String phone = this.getIntent().getExtras().getString("phone");
        String address = this.getIntent().getExtras().getString("address");
        String email = this.getIntent().getExtras().getString("email");

        //gets all views that are present
        this.firstNameTxt = this.findViewById(R.id.firstNameLabel);
        this.lastNameTxt = this.findViewById(R.id.lastNameLabel);
        this.emailTxt = this.findViewById(R.id.emailLabel);
        this.phoneTxt = this.findViewById(R.id.phoneLabel);
        this.addressTxt = this.findViewById(R.id.addressLabel);

        //sets text of views to values sent
        this.firstNameTxt.setText(firstName);
        this.lastNameTxt.setText(lastName);
        this.emailTxt.setText(email);
        this.phoneTxt.setText(phone);
        this.addressTxt.setText(address);
    }
}