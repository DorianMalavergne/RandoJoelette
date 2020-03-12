package com.example.randojoelette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
        final Button btn_inscription = (Button) findViewById(R.id.btn_inscription);
        final EditText editText_identifiant = (EditText) findViewById(R.id.saisie_identifiant);
        final EditText editText_mdp = (EditText) findViewById(R.id.saisie_mdp);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://192.168.43.92:8080/connexionUtilisateur?login";

        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                btn_connexion.setEnabled(false);
                final String id = editText_identifiant.getText().toString().trim();
                final String mdp = editText_mdp.getText().toString().trim();

                String url = "http://185.224.139.170:8080/connexionUtilisateur?login=" + id + "&password=" + mdp;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                btn_connexion.setEnabled(true);
                                try {
                                    if (!Patterns.EMAIL_ADDRESS.matcher(id).matches()) {
                                        requete.setText("L'adresse email saisie n'est pas valide");
                                        requete.setBackgroundColor(getResources().getColor(R.color.colorErreur));
                                        editText_identifiant.setText("");
                                    } else if(mdp.length() < 3) {
                                        requete.setText("Le mot de passe doit contenir au moins trois caractères");
                                        requete.setBackgroundColor(getResources().getColor(R.color.colorErreur));
                                        editText_mdp.setText("");
                                    } else if(response.getInt("idRandonneur") == 0) {
                                        requete.setText("Identifiant et/ou mot de passe incorrect");
                                        requete.setBackgroundColor(getResources().getColor(R.color.colorErreur));
                                        editText_identifiant.setText("");
                                        editText_mdp.setText("");
                                    } else {
                                        Bundle bundle = new Bundle();
                                        Intent intent = new Intent(MainActivity.this, AssomemberMainActivity.class);

                                        bundle.putString("nom", response.getString("nom"));
                                        bundle.putString("prenom", response.getString("prenom"));
                                        bundle.putInt("idRandonneur", response.getInt("idRandonneur"));
                                        intent.putExtras(bundle);

                                        if(response.getString("statut").equals("asso")) {
                                            intent = new Intent(MainActivity.this, AssoMainActivity.class);
                                            bundle.putInt("idRandonneur", response.getInt("idRandonneur"));
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        } else if (response.getString("statut").equals("HANDICAPE")) {
                                            intent = new Intent(MainActivity.this, HandiassomemberMainActivity.class);

                                            bundle.putString("nom", response.getString("nom"));
                                            bundle.putString("prenom", response.getString("prenom"));
                                            bundle.putInt("idRandonneur", response.getInt("idRandonneur"));
                                            intent.putExtras(bundle);

                                            startActivity(intent);
                                        }

                                        startActivity(intent);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btn_connexion.setEnabled(true);
                        requete.setText("Merci d'activer votre connexion internet");
                        requete.setBackgroundColor(getResources().getColor(R.color.colorErreur));
                    }
                });

                queue.add(jsonObjectRequest);
            }
        });

        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_inscription.setEnabled(false);
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
                btn_inscription.setEnabled(true);
            }
        });
    }
}
