package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HandiassomemberMainActivity extends AppCompatActivity {

    private final Bundle bundle = new Bundle();

    private ListView randoDispo;
    private ListView mesRando;
    private List<String> listeRandoDispo = new ArrayList<String>();
    private List<String> listeMesRando = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handiassomember_main_activity);

        TextView identite = findViewById(R.id.label_identite);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        String nom = extra.getString("nom");
        String prenom = extra.getString("prenom");
        final int idRandonneur = extra.getInt("idRandonneur");

        identite.setText("Bienvenue " + prenom + " " + nom);

        randoDispo = findViewById(R.id.list_rando_dispo);
        mesRando = findViewById(R.id.list_mes_rando);

        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://185.224.139.170:8080/afficheRandonneeActive";

        JsonArrayRequest randoDisponible = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++) {
                        listeRandoDispo.add(response.getJSONObject(i).getString("libelle"));
                    }
                    afficheRandoDispo();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(randoDisponible);

        url = "http://185.224.139.170:8080/getRandonneurRandonnee?idRandonneur=" + idRandonneur;

        JsonArrayRequest mesRandonnees = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                TextView pasDeRando = findViewById(R.id.label_aucune_rando);
                if(response.length() == 0) {
                    pasDeRando.setText("Vous avez aucunes randonnées en prévision");
                } else {
                    try {
                        for(int i = 0; i < response.length(); i++) {
                            listeMesRando.add(response.getJSONObject(i).getString("libelle"));
                        }
                        afficheMesRando();
                    } catch(Exception e) {
                        pasDeRando.setText("Erreur de traitement de mes randonnées");
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(mesRandonnees);

        randoDispo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listeRandoDispo.get(position);
                String url = "http://185.224.139.170:8080/getRandonnee?name=" + name;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(HandiassomemberMainActivity.this, handiassomember_valid_event_activity.class);
                        try {
                            bundle.putString("libelle", response.getString("libelle"));
                            bundle.putString("date", response.getString("date"));
                            bundle.putString("lieu", response.getString("lieu"));
                            bundle.putString("participantRequis", response.getString("participantMin"));
                            bundle.putString("participantAccepte", response.getString("participantInscrit"));
                            bundle.putString("dataEcheance", response.getString("dateEcheance"));
                            bundle.putInt("idRandonneur", idRandonneur);
                            bundle.putInt("idRandonnee", response.getInt("idRando"));
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

        mesRando.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listeMesRando.get(position);
                String url = "http://185.224.139.170:8080/getRandonnee?name=" + name;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(HandiassomemberMainActivity.this, handiassomember_read_active_event_activity.class);
                        try {
                            bundle.putString("libelle", response.getString("libelle"));
                            bundle.putString("date", response.getString("date"));
                            bundle.putString("lieu", response.getString("lieu"));
                            bundle.putString("participantRequis", response.getString("participantMin"));
                            bundle.putString("participantAccepte", response.getString("participantInscrit"));
                            bundle.putString("dataEcheance", response.getString("dateEcheance"));
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

    public void afficheRandoDispo() {

        randoDispo = findViewById(R.id.list_rando_dispo);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeRandoDispo);

        randoDispo.setAdapter(arrayAdapter);
    }

    public void afficheMesRando() {

        mesRando = findViewById(R.id.list_mes_rando);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeMesRando);

        mesRando.setAdapter(arrayAdapter);
    }
}
