package com.example.udevAndroidTp.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        final Client ClientByIntent = new Client(
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

        phoneNumberTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + ClientByIntent.getPhoneNumber()));
                smsIntent.putExtra("sms_body", "Test");
                try {
                    startActivity(smsIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(phoneNumberTextView.getContext(), "Erreur lors de la création du SMS", Toast.LENGTH_LONG).show();
                }
            }
        });

        mailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ClientByIntent.getMail()});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Objet");
                mailIntent.putExtra(Intent.EXTRA_TEXT, "Contenu du mail");

                try {
                    startActivity(Intent.createChooser(mailIntent, "Envoi d'un mail"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(mailTextView.getContext(), "Aucun client mail installé", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
