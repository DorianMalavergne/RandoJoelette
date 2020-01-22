package com.example.randojoelette;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AssoMainActivity extends AppCompatActivity {

    ListView listeRandoActive;
    private String[] randoActive = new String[]{"Randonnée 1", "Randonnée 2", "Randonnée 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeRandoActive = (ListView) findViewById(R.id.list_rando_active);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AssoMainActivity.this,
                android.R.layout.simple_list_item_1, randoActive);
        listeRandoActive.setAdapter(adapter);


    }
}
