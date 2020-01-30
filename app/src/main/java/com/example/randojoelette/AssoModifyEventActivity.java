package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AssoModifyEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asso_modify_event_activity);

        TextView textViewNomRando = (TextView) findViewById(R.id.label_nom_rando);
        EditText editTextDateRando = (EditText) findViewById(R.id.saisie_date);
        EditText editTextLieuRando = (EditText) findViewById(R.id.saisie_lieu);
        EditText editTextParticipantMin = (EditText) findViewById(R.id.saisie_nb_participant_min);
        EditText editTextEcheanceReponse = (EditText) findViewById(R.id.saisie_echeance_reponse);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        String libelle = extra.getString("libelle");
        String date = extra.getString("date");
        String lieu = extra.getString("lieu");
        String participantRequis = extra.getString("participantRequis");
        String dateEcheance = extra.getString("dataEcheance");

        textViewNomRando.setText(libelle);
        editTextDateRando.setText(date);
        editTextLieuRando.setText(lieu);
        editTextParticipantMin.setText(participantRequis);
        editTextEcheanceReponse.setText(dateEcheance);
    }
}
