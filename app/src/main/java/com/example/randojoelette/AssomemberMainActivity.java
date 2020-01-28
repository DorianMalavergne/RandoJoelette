package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AssomemberMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Bundle extra = intent.getExtras();
        String statut = extra.getString("statut");

        if(statut.equals("asso")) {
            ListView listeRandoActive;
            setContentView(R.layout.asso_main_activity);
            final List<String> randonneesActives = new ArrayList<String>();
            listeRandoActive = (ListView) findViewById(R.id.list_rando_active);
            final TextView listeTest = (TextView) findViewById(R.id.label_identite);

            String url = "http://192.168.43.116:8080/afficheRandonneeActive";

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    listeTest.setText(response.toString());
                    if(response != null) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                randonneesActives.add(response.getString(i));
                            }
                        } catch (Exception e) {
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

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    randonneesActives);

            listeRandoActive.setAdapter(arrayAdapter);


            TextView label_identite = (TextView) findViewById(R.id.label_identite);
            label_identite.setText("Bienvenue Admnistrateur");

        } else {
            setContentView(R.layout.assomember_main_activity);

            String nom = extra.getString("nom");
            String prenom = extra.getString("prenom");
            TextView label_identite = (TextView) findViewById(R.id.label_identite);
            label_identite.setText("Bienvenue " + prenom + " " + nom);
        }
    }
}
