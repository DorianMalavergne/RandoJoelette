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

public class handiassomember_valid_event_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handiassomember_valid_event_activity);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        final RequestQueue queue = Volley.newRequestQueue(this);

        final Button btnOui = findViewById(R.id.btn_oui);
        final Button btnNon = findViewById(R.id.btn_non);

        TextView textViewNomRando = (TextView) findViewById(R.id.label_nom_rando);
        EditText editTextDate = (EditText) findViewById(R.id.saisie_date_rando);
        EditText editTextLieu = (EditText) findViewById(R.id.saisie_postale_adresse);
        EditText editTextParticipantMin = (EditText) findViewById(R.id.saisie_nombre);
        final EditText editTextDateEcheance = (EditText) findViewById(R.id.saisie_date);

        String libelle = extra.getString("libelle");
        String date = extra.getString("date");
        String lieu = extra.getString("lieu");
        String participantRequis = extra.getString("participantRequis");
        String dataEcheance = extra.getString("dataEcheance");
        final int idRandonneur = extra.getInt("idRandonneur");
        final int idRandonnee = extra.getInt("idRandonnee");

        textViewNomRando.setText(libelle);
        editTextDate.setText(date);
        editTextLieu.setText(lieu);
        editTextParticipantMin.setText(participantRequis);
        editTextDateEcheance.setText(dataEcheance);

        btnOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOui.setEnabled(false);
                String url = "http://185.224.139.170:8080/valideParticipation?idRandonneur=" + idRandonneur + "&idRandonnee=" + idRandonnee;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        btnOui.setEnabled(true);
                        Intent intent1 = new Intent(handiassomember_valid_event_activity.this, HandiassomemberMainActivity.class);
                        startActivity(intent1);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btnOui.setEnabled(true);
                        error.printStackTrace();
                    }
                });
                queue.add(jsonObjectRequest);
            }
        });

        btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNon.setEnabled(false);
                Intent intent = new Intent(handiassomember_valid_event_activity.this, HandiassomemberMainActivity.class);
                btnNon.setEnabled(true);
                startActivity(intent);
            }
        });
    }
}
