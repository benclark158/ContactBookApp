package com.phonebook;

import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * Custom object to store contact information in the application
 */
public class Contact {

    //storage valiables
    private String firstName, lastName, phone, address, email;

    /**
     * Constructor - no other functionality
     * No validation, that should be done before!
     * @param firstName - first name of the person NOT NULL
     * @param lastName - last name of the person
     * @param phone - phone number, in string format
     * @param address - address of person
     * @param email - email of person
     */
    public Contact(@NonNull String firstName, String lastName, String phone, String address, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    /**
     * Gets the address of the person
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * gets the email of the contact
     * @return email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * gets the furst name of the person
     * @return first name of contact
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * gets the last name of the contact
     * @return last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * gets the phone number of the contact
     * @return phone number
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Computes the full name (first name + last name) of the contact
     * @return full name of person
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Converts the object into a bundle for distribution between activities
     * @return bundle object
     */
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("firstName", this.firstName);
        bundle.putString("lastName", this.lastName);
        bundle.putString("phone", this.phone);
        bundle.putString("address", this.address);
        bundle.putString("email", this.email);
        return bundle;
    }

    /**
     * Converts bundle into new contact object
     * @param bundle bundle containing valid keys
     * @return contact object
     */
    public static Contact fromBundle(Bundle bundle){
        String firstName = bundle.getString("firstName");
        String lastName = bundle.getString("lastName");
        String phone = bundle.getString("phone");
        String address = bundle.getString("address");
        String email = bundle.getString("email");

        return new Contact(firstName, lastName, phone, address, email);
    }
}
