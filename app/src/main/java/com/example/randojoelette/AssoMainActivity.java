package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class AssoMainActivity extends AppCompatActivity {

    ListView listeRandoActive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream
        setContentView(R.layout.activity_main);
        Button buttoncreaterando = findViewById(R.id.btn_creer_rando);
=======
        setContentView(R.layout.asso_main_activity);
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
        buttoncreaterando.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(AssoMainActivity.this,asso_add_event_activity.class);
                startActivity(intent);
            }
        });
=======
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                randonneesActives);

        listeRandoActive.setAdapter(arrayAdapter);
>>>>>>> Stashed changes
    }
}
