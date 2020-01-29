package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AssoReadActiveEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asso_read_active_event_activity);

        TextView textView = (TextView) findViewById(R.id.label_nom_rando);
        TextView textView1 = (TextView) findViewById(R.id.label_date_randonne);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        String libelle = extra.getString("libelle");
        textView.setText(libelle);

        String date = extra.getString("date");
        textView1.setText(date);


        Button btnModifierRando = (Button) findViewById(R.id.btn_modifier_rando);

        btnModifierRando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssoReadActiveEventActivity.this, AssoModifyEventActivity.class);
                startActivity(intent);
            }
        });

    }
}
