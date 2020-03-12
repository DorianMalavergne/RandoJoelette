package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AssoReadActiveEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asso_read_active_event_activity);

        final Button btnModifierRando = (Button) findViewById(R.id.btn_modifier);

        Intent intent = getIntent();
        final Bundle extra = intent.getExtras();

        TextView textViewNomRando = (TextView) findViewById(R.id.label_nom_rando);
        EditText editTextDate = (EditText) findViewById(R.id.date_randonnee);
        EditText editTextLieu = (EditText) findViewById(R.id.lieu_randonnee);
        EditText editTextParticipantMin = (EditText) findViewById(R.id.nombre_requis);
        EditText editTextParticipantAccepte = (EditText) findViewById(R.id.nombre_accepte);
        final EditText editTextDateEcheance = (EditText) findViewById(R.id.date_echeance);

        String libelle = extra.getString("libelle");
        String date = extra.getString("date");
        String lieu = extra.getString("lieu");
        String participantRequis = extra.getString("participantRequis");
        String participantAccepte = extra.getString("participantAccepte");
        String dataEcheance = extra.getString("dataEcheance");
        final int idRandonneur = extra.getInt("idRandonneur");
        final int idRandonnee = extra.getInt("idRandonnee");

        textViewNomRando.setText(libelle);
        editTextDate.setText(date);
        editTextLieu.setText(lieu);
        editTextParticipantMin.setText(participantRequis);
        editTextParticipantAccepte.setText(participantAccepte);
        editTextDateEcheance.setText(dataEcheance);

        btnModifierRando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnModifierRando.setEnabled(false);
                Intent intent = new Intent(AssoReadActiveEventActivity.this, AssoModifyEventActivity.class);
                extra.putInt("idRandonneur", idRandonneur);
                extra.putInt("idRandonnee", idRandonnee);
                intent.putExtras(extra);
                btnModifierRando.setEnabled(true);
                startActivity(intent);
            }
        });

    }
}
