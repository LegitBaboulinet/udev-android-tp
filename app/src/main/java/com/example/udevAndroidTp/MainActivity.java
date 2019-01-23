package com.example.udevAndroidTp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.udevAndroidTp.adapters.ClientAdapter;
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

        // Jeu de test
        /*Client[] arrayOfClients = new Client[] {
                new Client(
                        "Monsieur",
                        "Jean-Claude",
                        "Van Damme",
                        "142 rue des arbres",
                        "EuroCorp",
                        "0606060606",
                        "jcvd@gmail.com",
                        60,
                        "https://www.jcvd.com/",
                        true
                ),
                new Client(
                        "Madame",
                        "Jeanne-Claudette",
                        "Van Damme",
                        "142 rue des arbres",
                        "MondialCorp",
                        "0707070707",
                        "jcvd@outlook.com",
                        60,
                        "https://www.jcvd-official.com/",
                        false
                )
        };*/

        ClientAdapter clientAdapter = new ClientAdapter(this, android.R.layout.simple_list_item_1, arrayOfClients);
        clientsListView.setAdapter(clientAdapter);
    }

    public interface WebServiceCallBackInterface {
        @GET("Clients")
        Call<Client> getClients();
    }
}
