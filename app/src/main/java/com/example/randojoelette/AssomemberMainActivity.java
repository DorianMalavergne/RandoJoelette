package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AssomemberMainActivity extends AppCompatActivity {

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
        int idRandonneur = extra.getInt("idRandonneur");

        TextView label_identite = (TextView) findViewById(R.id.label_identite);
        label_identite.setText("Bienvenue " + prenom + " " + nom);

        final RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://185.224.139.170:8080/afficheRandonneeActive";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
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
