package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class handiassomember_read_active_event_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handiassomember_read_active_event_activity);

        final Button btn_modifier = findViewById(R.id.btn_modifier);

        Intent intent = getIntent();
        final Bundle extra = intent.getExtras();

        TextView textViewNomRando = (TextView) findViewById(R.id.label_nom_rando);
        EditText editTextDate = (EditText) findViewById(R.id.saisie_date_rando);
        EditText editTextLieu = (EditText) findViewById(R.id.saisie_postale_adresse);
        EditText editTextParticipant = (EditText) findViewById(R.id.saisie_nombre);
        final EditText editTextDateEcheance = (EditText) findViewById(R.id.saisie_date);

        final String libelle = extra.getString("libelle");
        final String date = extra.getString("date");
        final String lieu = extra.getString("lieu");
        final String participant = extra.getString("participantAccepte");
        final String dataEcheance = extra.getString("dataEcheance");
        final int idRandonneur = extra.getInt("idRandonneur");
        final int idRandonnee = extra.getInt("idRandonnee");

        textViewNomRando.setText(libelle);
        editTextDate.setText(date);
        editTextLieu.setText(lieu);
        editTextParticipant.setText(participant);
        editTextDateEcheance.setText(dataEcheance);

        btn_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_modifier.setEnabled(false);
                Intent intentModify = new Intent(handiassomember_read_active_event_activity.this,Handiassomember_modify_event_activity.class);
                try {
                    extra.putString("libelle", libelle);
                    extra.putString("date", date);
                    extra.putString("lieu", lieu);
                    extra.putString("participantRequis", participant);
                    extra.putString("dataEcheance", dataEcheance);
                    extra.putInt("idRandonneur", idRandonneur);
                    extra.putInt("idRandonnee", idRandonnee);
                    intentModify.putExtras(extra);
                    btn_modifier.setEnabled(true);
                    startActivity(intentModify);
                } catch (Exception e) {
                    btn_modifier.setEnabled(true);
                    e.printStackTrace();
                }
            }
        });
    }
}
