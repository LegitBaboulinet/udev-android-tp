package com.example.udevAndroidTp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.udevAndroidTp.R;
import com.example.udevAndroidTp.classes.Client;

public class ClientActivity extends AppCompatActivity {

    TextView identityTextView;
    TextView companyTextView;
    TextView addressTextView;
    TextView webSiteTextView;
    TextView phoneNumberTextView;
    TextView mailTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        initiateLayouts();
        behaviorViews();
    }

    private void initiateLayouts() {
        identityTextView = findViewById(R.id.indentityTextView);
        companyTextView = findViewById(R.id.companyTextView);
        addressTextView = findViewById(R.id.addressTextView);
        webSiteTextView = findViewById(R.id.webSiteTextView);
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView);
        mailTextView = findViewById(R.id.mailTextView);
    }

    private void behaviorViews() {
        /*
        On récupére la valeur envoyé par l'intent (Voir ListPersonneActivity.java)
         */
        //Client ClientByIntent = (Client) getIntent().getExtras().getSerializable("clientActivityIntent");
        Client ClientByIntent = new Client(
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
        );
        identityTextView.setText(ClientByIntent.getCivility() + " " + ClientByIntent.getName() + " "
                + ClientByIntent.getFirstName() + " " + String.valueOf(ClientByIntent.getAge()) + " ans");
        companyTextView.setText(ClientByIntent.getCompany());
        addressTextView.setText(ClientByIntent.getAddress());
        webSiteTextView.setText(ClientByIntent.getWebSite());
        phoneNumberTextView.setText(ClientByIntent.getPhoneNumber());
        mailTextView.setText(ClientByIntent.getMail());


    }
}
