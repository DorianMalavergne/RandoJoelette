package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AssoReadActiveEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asso_read_active_event_activity);

        final Button btnValiderRando = (Button) findViewById(R.id.btn_valider_rando);

        Intent intent = getIntent();
        final Bundle extra = intent.getExtras();

        final RequestQueue queue = Volley.newRequestQueue(this);

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

        btnValiderRando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValiderRando.setEnabled(false);
                final Intent intent = new Intent(AssoReadActiveEventActivity.this, AssoMainActivity.class);

                String url = "http://185.224.139.170:8080/validerRandonnee?idRandonnee=" + idRandonnee;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //DO NOTHING
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                queue.add(jsonObjectRequest);

                intent.putExtras(extra);
                btnValiderRando.setEnabled(true);
                startActivity(intent);
            }
        });

    }
}
