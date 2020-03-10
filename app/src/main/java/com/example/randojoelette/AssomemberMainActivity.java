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

public class AssomemberMainActivity extends AppCompatActivity {

    private final Bundle bundle = new Bundle();

    private ListView randoDispo;
    private ListView mesRando;
    private List<String> listeRandonneesDisponibles = new ArrayList<String>();
    private List<String> listeMesRandonnees = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assomember_main_activity);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        String nom = extra.getString("nom");
        String prenom = extra.getString("prenom");
        final int idRandonneur = extra.getInt("idRandonneur");

        TextView label_identite = (TextView) findViewById(R.id.label_identite);
        label_identite.setText("Bienvenue " + prenom + " " + nom);

        String url = "http://185.224.139.170:8080/afficheRandonneeActive";

        final RequestQueue queue = Volley.newRequestQueue(this);

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++) {
                        listeRandonneesDisponibles.add(response.getJSONObject(i).getString("libelle"));
                    }
                    afficherListeRandoDispo();
                } catch (Exception e) {
                    //TODO erreur de traitement des randonnees disponibles
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO aucune randonnees disponibles pour le moment
            }
        });

        queue.add(jsonArrayRequest);

        url = "http://185.224.139.170:8080/getRandonneurRandonnee?idRandonneur=" + idRandonneur;

        JsonArrayRequest mesRandoRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++) {
                        listeMesRandonnees.add(response.getJSONObject(i).getString("libelle"));
                    }
                    afficherListeMesRando();
                } catch (Exception e) {
                    //TODO erreur de traitement de mes randonnees
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO bous n'avez aucune randonnees pour le moment
            }
        });

        queue.add(mesRandoRequest);

        randoDispo = (ListView) findViewById(R.id.list_rando_dispo);

        randoDispo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listeRandonneesDisponibles.get(position);
                String url = "http://185.224.139.170:8080/getRandonnee?name=" + name;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(AssomemberMainActivity.this, Assomember_valid_event_activity.class);
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

        mesRando = (ListView) findViewById(R.id.list_mes_rando);

        mesRando.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listeMesRandonnees.get(position);
                String url = "http://185.224.139.170:8080/getRandonnee?name=" + name;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(AssomemberMainActivity.this, Assomember_read_active_event_activity.class);
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

    public void afficherListeRandoDispo() {

        randoDispo = (ListView) findViewById(R.id.list_rando_dispo);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeRandonneesDisponibles);

        randoDispo.setAdapter(arrayAdapter);
    }

    public void afficherListeMesRando() {

        mesRando = (ListView) findViewById(R.id.list_mes_rando);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeMesRandonnees);

        mesRando.setAdapter(arrayAdapter);
    }
}
