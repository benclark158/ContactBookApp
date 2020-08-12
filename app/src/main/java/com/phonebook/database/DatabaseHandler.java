package com.phonebook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Contacts";
    public static final String CONTACT_ID_NAME = "id";
    public static final String CONTACT_FIRST_NAME_NAME = "firstName";
    public static final String CONTACT_LAST_NAME_NAME = "lastName";
    public static final String CONTACT_ADDRESS_NAME = "address";
    public static final String CONTACT_PHONE_NAME = "phone";
    public static final String CONTACT_EMAIL_NAME = "email";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactDb.db";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    CONTACT_ID_NAME + " integer PRIMARY KEY AUTOINCREMENT," +
                    CONTACT_FIRST_NAME_NAME + " text NOT NULL," +
                    CONTACT_LAST_NAME_NAME + " text," +
                    CONTACT_ADDRESS_NAME + " text," +
                    CONTACT_PHONE_NAME + " text," +
                    CONTACT_EMAIL_NAME + " text" +
                    ");";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
