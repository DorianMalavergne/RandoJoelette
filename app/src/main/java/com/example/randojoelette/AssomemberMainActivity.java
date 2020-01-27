package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AssomemberMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Bundle extra = intent.getExtras();
        String statut = extra.getString("statut");

        if(statut.equals("asso")) {
            setContentView(R.layout.asso_main_activity);
            TextView label_identite = (TextView) findViewById(R.id.label_identite);
            label_identite.setText("Bienvenue Admnistrateur");

        } else {
            setContentView(R.layout.assomember_main_activity);

            String nom = extra.getString("nom");
            String prenom = extra.getString("prenom");
            TextView label_identite = (TextView) findViewById(R.id.label_identite);
            label_identite.setText("Bienvenue " + prenom + " " + nom);
        }
    }
}
