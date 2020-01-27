package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AssoMainActivity extends AppCompatActivity {

    ListView listeRandoActive;
    private String[] randoActive = new String[]{"Randonnée 1", "Randonnée 2", "Randonnée 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttoncreaterando = findViewById(R.id.btn_creer_rando);

        listeRandoActive = (ListView) findViewById(R.id.list_rando_active);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AssoMainActivity.this,
                android.R.layout.simple_list_item_1, randoActive);
        listeRandoActive.setAdapter(adapter);

        buttoncreaterando.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(AssoMainActivity.this,asso_add_event_activity.class);
                startActivity(intent);
            }
        });
    }
}
