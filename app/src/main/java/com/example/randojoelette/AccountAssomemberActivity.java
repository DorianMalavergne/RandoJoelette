package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AccountAssomemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_assomember_activity);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        final RequestQueue queue = Volley.newRequestQueue(this);

        TextView labelInscription = findViewById(R.id.label_inscription);
        final String statut = extra.getString("inscription");

        Button button = findViewById(R.id.btn_valider);

        labelInscription.setText("INSCRIPTION RANDONNEUR " + statut);

        final EditText nom = (EditText) findViewById (R.id.saisie_nomrandonneur);
        final EditText prenom = (EditText) findViewById (R.id.saisie_prenom);
        final EditText adresse = (EditText) findViewById (R.id.saisie_adresse);
        final EditText tel = (EditText) findViewById (R.id.saisie_tel);
        final EditText login = (EditText) findViewById (R.id.saisie_log);
        final EditText mdp = (EditText) findViewById (R.id.saisie_motdp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomRandoneur = nom.getText().toString();
                String prenomRandonneur = prenom.getText().toString();
                String adresseRandonneur = adresse.getText().toString();
                String telRandonneur = tel.getText().toString();
                String loginRandonneur = login.getText().toString().trim();
                String mdpRandonneur = mdp.getText().toString().trim();

                String url = "http://185.224.139.170:8080/inscriptionRandonneur?login=" + loginRandonneur + "&password=" + mdpRandonneur + "&nom=" + nomRandoneur
                            + "&prenom=" + prenomRandonneur + "&add=" + adresseRandonneur + "&tel=" + telRandonneur + "&stat=" + statut;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(AccountAssomemberActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                queue.add(jsonObjectRequest);

            }
        });



    }
}
