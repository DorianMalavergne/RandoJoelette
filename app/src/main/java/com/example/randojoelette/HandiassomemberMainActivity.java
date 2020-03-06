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

public class HandiassomemberMainActivity extends AppCompatActivity {

    private ListView randoDispo;
    private List<String> listeRandoDispo = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handiassomember_main_activity);

        TextView identite = findViewById(R.id.label_identite);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        String nom = extra.getString("nom");
        String prenom = extra.getString("prenom");
        identite.setText("Bienvenue " + prenom + " " + nom);

        randoDispo = findViewById(R.id.list_rando_dispo);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://185.224.139.170:8080/afficheRandonneeActive";

        JsonArrayRequest randoDispo = new JsonArrayRequest(
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

        queue.add(randoDispo);
    }

    public void afficheRandoDispo() {

        randoDispo = findViewById(R.id.list_rando_dispo);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeRandoDispo);

        randoDispo.setAdapter(arrayAdapter);
    }
}
