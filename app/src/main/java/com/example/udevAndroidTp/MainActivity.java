package com.example.udevAndroidTp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.udevAndroidTp.classes.Client;

import retrofit2.Call;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private ListView clientsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateLayouts();
        behaviorViews();
    }

    private void initiateLayouts() {
        clientsListView = findViewById(R.id.clientsListView);
    }

    private void behaviorViews() {

        // Séléction des clients et affichage dans la liste
        DatabaseHelper databaseHelper = new DatabaseHelper(this, false);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Client[] arrayOfClients = databaseHelper.selectAllClients(database);
    }

    public interface WebServiceCallBackInterface {
        @GET("Clients")
        Call<Client> getClients();
    }
}
