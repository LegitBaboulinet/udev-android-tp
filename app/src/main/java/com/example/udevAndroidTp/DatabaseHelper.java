package com.example.udevAndroidTp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.udevAndroidTp.classes.Client;

import java.io.File;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME_CLIENT = "Client";

    private static final String DATABASE_CREATE_TABLE_CLIENT = "CREATE TABLE Client (id INTEGER " +
            "PRIMARY KEY AUTOINCREMENT, civility TEXT, " +
            "name TEXT, firstName TEXT, address TEXT, company TEXT, " +
            "phoneNumber TEXT, mail TEXT, age INTEGER, webSite TEXT, premium INTEGER)";

    public DatabaseHelper(Context context, boolean hasTODBCheck) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (hasTODBCheck) {
            doDBCheck();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_TABLE_CLIENT);
    }

    private void doDBCheck() {
        try {
            File file = new File("data/data/com.example.udevAndroidTp/databases/" + DATABASE_NAME);
            file.delete();
        } catch (Exception e) {
            Log.e("ERR", "La DB n'existe pas");
        }
    }

    public Client[] selectAllClients(SQLiteDatabase database) {
        Cursor cursor = database.query(
                TABLE_NAME_CLIENT, // Argument de la table
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Client[] clientsFromDatabase = new Client[cursor.getCount()];

        int i = 0;
        while (cursor.moveToNext()) {
            Boolean premium = cursor.getInt(cursor.getColumnIndexOrThrow("premium")) == 1;
            clientsFromDatabase[i] = new Client(
                    cursor.getString(cursor.getColumnIndexOrThrow("civility")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("firstName")),
                    cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    cursor.getString(cursor.getColumnIndexOrThrow("company")),
                    cursor.getString(cursor.getColumnIndexOrThrow("phoneNumber")),
                    cursor.getString(cursor.getColumnIndexOrThrow("mail")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("age")),
                    cursor.getString(cursor.getColumnIndexOrThrow("webSite")),
                    premium
            );
            i++;
        }

        cursor.close();
        return clientsFromDatabase;
    }

    public void insertListeClient(List<Client> clientList, SQLiteDatabase db) {
        for (int i = 0; clientList.size() - 1 > i; i++) {
            Client currentClient = clientList.get(i);

            int premium = (currentClient.isPremium()) ? 1 : 0;

            // INSERT SQLite
            ContentValues values = new ContentValues();
            values.put("civility", currentClient.getCivility());
            values.put("name", currentClient.getName());
            values.put("firstName", currentClient.getFirstName());
            values.put("address", currentClient.getAddress());
            values.put("company", currentClient.getCompany());
            values.put("phoneNumber", currentClient.getPhoneNumber());
            values.put("mail", currentClient.getMail());
            values.put("age", currentClient.getAge());
            values.put("website", currentClient.getWebSite());
            values.put("premium", premium);

            db.insert(TABLE_NAME_CLIENT, null, values);
            Log.d("ERR", currentClient.getFirstName());
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
