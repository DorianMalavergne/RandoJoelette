package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Assomember_read_active_event_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assomember_read_active_event_activity);

        Intent intent = getIntent();
        final Bundle extra = intent.getExtras();

        Button btn_modifier = findViewById(R.id.btn_modifier);
        TextView textViewNomRando = (TextView) findViewById(R.id.label_nom_rando);
        EditText editTextDate = (EditText) findViewById(R.id.saisie_date_rando);
        EditText editTextLieu = (EditText) findViewById(R.id.saisie_postale_adresse);
        EditText editTextParticipantMin = (EditText) findViewById(R.id.saisie_nombre_requis);
        final EditText editTextDateEcheance = (EditText) findViewById(R.id.saisie_date);

        String libelle = extra.getString("libelle");
        String date = extra.getString("date");
        String lieu = extra.getString("lieu");
        String participantRequis = extra.getString("participantRequis");
        String dataEcheance = extra.getString("dataEcheance");

        textViewNomRando.setText(libelle);
        editTextDate.setText(date);
        editTextLieu.setText(lieu);
        editTextParticipantMin.setText(participantRequis);
        editTextDateEcheance.setText(dataEcheance);

        btn_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Assomember_read_active_event_activity.this, Assomember_modify_event_activity.class);
                startActivity(intent);
            }
        });
    }
}
