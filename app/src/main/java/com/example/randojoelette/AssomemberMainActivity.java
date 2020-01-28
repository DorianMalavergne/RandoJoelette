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
        setContentView(R.layout.assomember_main_activity);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        String nom = extra.getString("nom");
        String prenom = extra.getString("prenom");

        TextView label_identite = (TextView) findViewById(R.id.label_identite);
        label_identite.setText("Bienvenue " + prenom + " " + nom);
    }
}
