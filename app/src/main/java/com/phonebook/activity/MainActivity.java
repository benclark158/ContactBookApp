package com.phonebook.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.phonebook.Contact;
import com.phonebook.database.ContactContentProvider;
import com.phonebook.database.DatabaseHandler;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = this.findViewById(R.id.recyclerView);
        this.recyclerView.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        this.recyclerView.addItemDecoration(dividerItemDecoration);

        this.recyclerView.setAdapter(this.adapter = new ContactAdapter(this));

    }

    @Override
    protected void onResume() {
        this.adapter.notifyDataSetChanged();
        this.loadContacts();
        super.onResume();
    }

    public void loadContacts(){
        String[] projection = new String[] {
                DatabaseHandler.CONTACT_FIRST_NAME_NAME,
                DatabaseHandler.CONTACT_LAST_NAME_NAME,
                DatabaseHandler.CONTACT_PHONE_NAME,
                DatabaseHandler.CONTACT_ADDRESS_NAME,
                DatabaseHandler.CONTACT_EMAIL_NAME
        };

        String selection = "";
        String[] args = new String[0];

        Cursor results = this.getContentResolver().query(ContactContentProvider.ASC_ALL_URI, projection, selection, args, "");

        if(results == null){
            return;
        }

        ((ContactAdapter)this.recyclerView.getAdapter()).clear();

        while (results.moveToNext()) {

            String firstname = results.getString(0);
            String lastname = results.getString(1);
            String phone = results.getString(2);
            String address = results.getString(3);
            String email = results.getString(4);

            Contact contact = new Contact(firstname, lastname, phone, address, email);

            System.out.println("adding");
            this.adapter.addContact(contact);
        }
        results.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.getMenuInflater().inflate(R.menu.plus_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.app_bar_add) {
            Intent intent = new Intent(this, AddContactActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
