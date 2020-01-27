package com.example.randojoelette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        Spinner spinnerStatut = (Spinner) findViewById(R.id.spinner_statut);

        spinnerStatut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch(position) {
                    case 1:
                        intent = new Intent(AccountActivity.this, AccountAssoActivity.class);
                        startActivity(intent);
                        break;
                    case 2: /* Falls throught */
                    case 3: /* Falls throught */
                    case 4:
                        intent = new Intent(AccountActivity.this, AccountAssomemberActivity.class);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}