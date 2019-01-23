package com.example.udevAndroidTp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.udevAndroidTp.activities.ClientActivity;
import com.example.udevAndroidTp.adapters.ClientAdapter;
import com.example.udevAndroidTp.classes.Client;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private Retrofit retroFit;

    private ListView clientsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de RetroFit
        retroFit = new Retrofit.Builder()
                .baseUrl("http://20.188.38.133/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        initiateLayouts();
        behaviorViews();
    }

    private void initiateLayouts() {
        clientsListView = findViewById(R.id.clientsListView);
    }

    private void behaviorViews() {

        // Récupération des clients depuis la base de données
        callWebServiceForClientsActivity();

        // Séléction des clients et affichage dans la liste
        DatabaseHelper databaseHelper = new DatabaseHelper(this, false);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Client[] arrayOfClients = databaseHelper.selectAllClients(database);
        ClientAdapter clientAdapter = new ClientAdapter(this, android.R.layout.simple_list_item_1, arrayOfClients);
        clientsListView.setAdapter(clientAdapter);

        // Gestion du click d'élément de la liste
        clientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client clientClicked = (Client) parent.getItemAtPosition(position);
                Intent clickedPersonneIntent = new Intent(clientsListView.getContext(), ClientActivity.class);
                clickedPersonneIntent.putExtra("clientActivityIntent", clientClicked);
                startActivity(clickedPersonneIntent);
            }
        });
    }

    private void callWebServiceForClientsActivity() {

        // Appel du webservice à la route '/Clients'
        WebServiceCallBackInterface callbackInterface = retroFit.create(WebServiceCallBackInterface.class);

        // Récupération de l'appel
        Call<List<Client>> getClientsCall = callbackInterface.getClients();

        getClientsCall.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {

                // Création du database helper
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext(), true);

                // Récupération de la base de donneés
                SQLiteDatabase database = databaseHelper.getWritableDatabase();

                try {
                    // Insertion des personnes dans la base de données
                    databaseHelper.insertListeClient(new ArrayList<Client>(response.body()), database);
                } catch (NullPointerException e) {
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Impossible de contacter le serveur", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Impossible de contacter le serveur", Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface WebServiceCallBackInterface {
        @GET("Clients")
        Call<List<Client>> getClients();
    }
}