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

        Button btn_modifier = findViewById(R.id.btn_modifier);

        Intent intent = getIntent();
        final Bundle extra = intent.getExtras();

        TextView textViewNomRando = (TextView) findViewById(R.id.label_nom_rando);
        EditText editTextDate = (EditText) findViewById(R.id.saisie_date_rando);
        EditText editTextLieu = (EditText) findViewById(R.id.saisie_postale_adresse);
        EditText editTextParticipantMin = (EditText) findViewById(R.id.saisie_nombre);
        final EditText editTextDateEcheance = (EditText) findViewById(R.id.saisie_date);

        final String libelle = extra.getString("libelle");
        final String date = extra.getString("date");
        final String lieu = extra.getString("lieu");
        final String participantRequis = extra.getString("participantRequis");
        final String dataEcheance = extra.getString("dataEcheance");

        textViewNomRando.setText(libelle);
        editTextDate.setText(date);
        editTextLieu.setText(lieu);
        editTextParticipantMin.setText(participantRequis);
        editTextDateEcheance.setText(dataEcheance);

        btn_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentModify = new Intent(handiassomember_read_active_event_activity.this,Handiassomember_modify_event_activity.class);
                try {
                    extra.putString("libelle", libelle);
                    extra.putString("date", date);
                    extra.putString("lieu", lieu);
                    extra.putString("participantRequis", participantRequis);
                    extra.putString("dataEcheance", dataEcheance);
                    intentModify.putExtras(extra);
                    startActivity(intentModify);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
