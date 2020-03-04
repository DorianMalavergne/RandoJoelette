package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Assomember_read_active_event_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assomember_read_active_event_activity);

        Button btn_modifier = findViewById(R.id.btn_modifier);

        btn_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Assomember_read_active_event_activity.this, Assomember_modify_event_activity.class);
                startActivity(intent);
            }
        });
    }
}
