package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


public class AssoMainActivity extends AppCompatActivity {

    private ListView listeRando;
    private List<String> listeRandonneesActives = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asso_main_activity);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        final int idRandonneur = extra.getInt("idRandonneur");

        final Button btn_creerRando = (Button) findViewById(R.id.btn_creer_rando);
        listeRando = (ListView) findViewById(R.id.list_rando_active);

        final RequestQueue queue = Volley.newRequestQueue(this);

        final TextView label_identite = (TextView) findViewById(R.id.label_identite);
        label_identite.setText("Bienvenue Admnistrateur");



        String url = "http://185.224.139.170:8080/afficheRandonneeActive";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++) {
                        listeRandonneesActives.add(response.getJSONObject(i).getString("libelle"));
                    }
                    afficherListeRandoActive();
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
                btn_creerRando.setEnabled(false);
                Intent intent =  new Intent(AssoMainActivity.this, AssoAddEventActivity.class);
                btn_creerRando.setEnabled(true);
                startActivity(intent);
            }
        });

        listeRando.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Bundle bundle = new Bundle();
                final Intent intent = new Intent(AssoMainActivity.this, AssoReadActiveEventActivity.class);

                String name = listeRandonneesActives.get(position);
                String url = "http://185.224.139.170:8080/getRandonnee?name=" + name;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
    }

    public void afficherListeRandoActive() {

        listeRando = (ListView) findViewById(R.id.list_rando_active);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeRandonneesActives);

        listeRando.setAdapter(arrayAdapter);
    }
}
