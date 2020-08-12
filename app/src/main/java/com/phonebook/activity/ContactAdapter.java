package com.phonebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.phonebook.Contact;

import java.util.LinkedList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    List<Contact> contactList = new LinkedList<>();
    Activity activity;

    public void clear() {
        this.contactList.clear();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ConstraintLayout constraintLayout;
        public ContactViewHolder(ConstraintLayout cl) {
            super(cl);
            this.constraintLayout = cl;
        }
    }

    public ContactAdapter(Activity activity) {
        this.activity = activity;
    }

    public void addContact(Contact c){
        this.contactList.add(c);
    }

    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout cl = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_mini_view, parent, false);

        ContactViewHolder vh = new ContactViewHolder(cl);
        return vh;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        final Contact contact = this.contactList.get(position);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ContactViewActivity.class);

                Bundle bundle = contact.toBundle();

                intent.putExtras(bundle);

                activity.startActivity(intent);
            }
        });

        TextView text = holder.constraintLayout.findViewById(R.id.fullNameText);
        text.setText(contact.getFullName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.contactList.size();
    }
}
