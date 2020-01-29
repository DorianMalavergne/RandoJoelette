package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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


public class AssoMainActivity extends AppCompatActivity {

    private ListView listeRando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asso_main_activity);

        Button btn_creerRando = (Button) findViewById(R.id.btn_creer_rando);
        listeRando = (ListView) findViewById(R.id.list_rando_active);

        final TextView label_identite = (TextView) findViewById(R.id.label_identite);

        final List<String> listeRandonneesActives = new ArrayList<String>();
        label_identite.setText("Bienvenue Admnistrateur");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://185.224.139.170:8080/afficheRandonneeActive";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++) {
                        listeRandonneesActives.add(response.getJSONObject(i).getString("libelle"));
                        afficherListeRandoActive(listeRandonneesActives);
                    }
                } catch (Exception e) {
                    label_identite.setText("Erreur lors du traitement de l'affichage des listes des randonnées actives");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                label_identite.setText("Aucunes randonnees actives pour le moment");
            }
        });

        queue.add(jsonArrayRequest);

        btn_creerRando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(AssoMainActivity.this, AssoAddEventActivity.class);
                startActivity(intent);
            }
        });

        listeRando.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AssoMainActivity.this, AssoReadActiveEventActivity.class);
                startActivity(intent);
            }
        });
    }

    public void afficherListeRandoActive(List<String> listeRandonneesActives) {

        listeRando = (ListView) findViewById(R.id.list_rando_active);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeRandonneesActives);

        listeRando.setAdapter(arrayAdapter);
    }
}
