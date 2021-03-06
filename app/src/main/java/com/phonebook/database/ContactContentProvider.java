package com.phonebook.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.phonebook.activity.R;

import java.net.URISyntaxException;

public class ContactContentProvider extends ContentProvider {
    /*
     * URI CONTENT
     */

    private static final String PROVIDER_NAME = "com.phonebook.database.ContactContentProvider";

    public static final Uri ASC_ALL_URI = Uri.parse("content://" + PROVIDER_NAME + "/contact");;

    private static final UriMatcher uriMatcher;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "contact", 1);
        uriMatcher.addURI(PROVIDER_NAME, "contact/#", 2);
    }


    private DatabaseHandler handler;

    public ContactContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        //deletes data
        SQLiteDatabase db = this.handler.getReadableDatabase();

        String table = "";

        //gets table/configuration based on URI
        switch (uriMatcher.match(uri)){
            case 1:
            case 2: {
                table = DatabaseHandler.TABLE_NAME;
                break;
            }
            default: {
                throw new UnsupportedOperationException(this.getContext().getString(R.string.invalidUri));
            }
        }

        //deletes rows and notifies of change.
        int i = db.delete(table, selection, selectionArgs);

        this.getContext().getContentResolver().notifyChange(uri, null, false);

        return i;
    }

    @Override
    public String getType(Uri uri) {
        return this.handler.getDatabaseName();
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = this.handler.getReadableDatabase();

        String table = "";

        //gets table/configuration based on URI
        switch (uriMatcher.match(uri)){
            case 1:
            case 2: {
                table = DatabaseHandler.TABLE_NAME;
                break;
            }
            default: {
                throw new UnsupportedOperationException(this.getContext().getString(R.string.invalidUri));
            }
        }

        //inserts data into table - notifies of update
        db.insert(table, null, values);
        this.getContext().getContentResolver().notifyChange(uri, null, false);
        return uri;
    }

    @Override
    public boolean onCreate() {
        this.handler = new DatabaseHandler(this.getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = this.handler.getReadableDatabase();

        String table = "";

        //gets table and ordering based on URI
        switch (uriMatcher.match(uri)){
            case 1:{
                table = DatabaseHandler.TABLE_NAME;
                if (TextUtils.isEmpty(sortOrder)) sortOrder = DatabaseHandler.CONTACT_FIRST_NAME_NAME + " DESC";
                break;
            }
            case 2:{
                table = DatabaseHandler.TABLE_NAME;
                if (TextUtils.isEmpty(sortOrder)) sortOrder = DatabaseHandler.CONTACT_FIRST_NAME_NAME + " ASC";
                break;
            }
            default: {
                throw new UnsupportedOperationException(this.getContext().getString(R.string.invalidUri));
            }
        }
        //does query
        Cursor c = db.query(table, projection, selection, selectionArgs, "", "", sortOrder);

        //updates curser with update
        c.setNotificationUri(this.getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = this.handler.getReadableDatabase();

        String table = "";

        //gets table/configuration based on URI
        switch (uriMatcher.match(uri)){
            case 1:
            case 2: {
                table = DatabaseHandler.TABLE_NAME;
                break;
            }
            default: {
                throw new UnsupportedOperationException(this.getContext().getString(R.string.invalidUri));
            }
        }

        //updates rows with appropiate data
        int i = db.update(table, values, selection, selectionArgs);
        this.getContext().getContentResolver().notifyChange(uri, null, false);
        return i;
    }
}
