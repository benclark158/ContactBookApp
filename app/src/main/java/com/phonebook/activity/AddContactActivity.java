package com.phonebook.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.phonebook.database.ContactContentProvider;
import com.phonebook.database.DatabaseHandler;

import org.w3c.dom.Text;

public class AddContactActivity extends AppCompatActivity {

    private Button buttonSave;
    private EditText firstNameTxt, lastNameTxt, emailTxt, phoneTxt, addressTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        //Gets all the views on the screen
        this.buttonSave = this.findViewById(R.id.saveBtn);
        this.firstNameTxt = this.findViewById(R.id.firstNameTxt);
        this.lastNameTxt = this.findViewById(R.id.lastNameTxt);
        this.emailTxt = this.findViewById(R.id.emailTxt);
        this.phoneTxt = this.findViewById(R.id.phoneTxt);
        this.addressTxt = this.findViewById(R.id.addressTxt);

        //Listen for when button is pressed
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName, lastName, email, phone, address;

                // gets all data enterd by user
                firstName = firstNameTxt.getText().toString();
                lastName = lastNameTxt.getText().toString();
                email = emailTxt.getText().toString();
                phone = phoneTxt.getText().toString();
                address = addressTxt.getText().toString();

                //validates the data entered
                String validation = validate(firstName, email, phone);

                if(validation == null) {
                    //Add to database!
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHandler.CONTACT_FIRST_NAME_NAME, firstName);
                    values.put(DatabaseHandler.CONTACT_LAST_NAME_NAME, lastName);
                    values.put(DatabaseHandler.CONTACT_EMAIL_NAME, email);
                    values.put(DatabaseHandler.CONTACT_PHONE_NAME, phone);
                    values.put(DatabaseHandler.CONTACT_ADDRESS_NAME, address);

                    getContentResolver().insert(ContactContentProvider.ASC_ALL_URI, values);


                    //Close screen!
                    setResult(RESULT_OK, new Intent());
                    finish();
                } else {
                    //Creates warning if data is not valid
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddContactActivity.this);
                    builder.setTitle("Invalid data entry.");
                    builder.setMessage(validation);
                    builder.create().show();
                }
            }
        });
    }

    /**
     * Validates firstname, email and phone number if they are entered
     * @param firstName name to validate
     * @param email email to validate
     * @param phone phone number to validate
     * @return null if no errors, error text if there is an issue
     */
    private String validate(String firstName, String email, String phone) {
        if(firstName == null || firstName == "" || TextUtils.isEmpty(firstName)){
            return "Invalid first name";
        }

        if(!TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "Invalid email";
        }

        if(!TextUtils.isEmpty(phone) && !Patterns.PHONE.matcher(phone).matches()) {
            return "Invalid phone number";
        }

        return null;
    }
}