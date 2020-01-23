package com.example.randojoelette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView requete = (TextView) findViewById(R.id.requete);

        final Button btn_connexion = (Button) findViewById(R.id.btn_connexion);
        Button btn_inscription = (Button) findViewById(R.id.btn_inscription);
        final EditText editText_identifiant = (EditText) findViewById(R.id.saisie_identifiant);
        final EditText editText_mdp = (EditText) findViewById(R.id.saisie_mdp);

        final RequestQueue queue = Volley.newRequestQueue(this);

        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String id = editText_identifiant.getText().toString();
                String mdp = editText_mdp.getText().toString();
                String url = "http://192.168.43.92:8080/connexionUtilisateur?login=" + id + "&password=" + mdp;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                requete.setText(response.toString());
                                Intent intent = new Intent(MainActivity.this, AssomemberMainActivity.class);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            requete.setText(error.getMessage());
                        }
                });

                queue.add(jsonObjectRequest);
            }
        });

        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountAssomemberActivity.class);
                startActivity(intent);
            }
        });
    }
}
