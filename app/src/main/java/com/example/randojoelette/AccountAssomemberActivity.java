package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountAssomemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_assomember_activity);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        TextView labelInscription = findViewById(R.id.label_inscription);
        String statut = extra.getString("inscription");

        labelInscription.setText("INSCRIPTION RANDONNEUR " + statut);
    }
}
