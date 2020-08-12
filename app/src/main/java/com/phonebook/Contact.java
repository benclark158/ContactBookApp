package com.phonebook;

import android.os.Bundle;

public class Contact {

    private String firstName, lastName, phone, address, email;

    public Contact(String firstName, String lastName, String phone, String address, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("firstName", this.firstName);
        bundle.putString("lastName", this.lastName);
        bundle.putString("phone", this.phone);
        bundle.putString("address", this.address);
        bundle.putString("email", this.email);
        return bundle;
    }

    public static Contact fromBundle(Bundle bundle){
        String firstName = bundle.getString("firstName");
        String lastName = bundle.getString("lastName");
        String phone = bundle.getString("phone");
        String address = bundle.getString("address");
        String email = bundle.getString("email");

        return new Contact(firstName, lastName, phone, address, email);
    }
}
