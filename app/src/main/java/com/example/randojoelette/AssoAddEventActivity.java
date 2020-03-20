package com.example.randojoelette;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AssoAddEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private boolean dateEcheance = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asso_add_event_activity);

        final Button boutton = (Button) findViewById(R.id.btn_valider);
        Button btnDate = (Button) findViewById(R.id.btn_date);
        Button btnDateEcheance = (Button) findViewById(R.id.btn_date_echeance);

        final EditText libelle = (EditText) findViewById (R.id.saisie_libelle_randonnee);
        final EditText date = (EditText) findViewById (R.id.saisie_date_rando);
        final EditText lieu = (EditText) findViewById (R.id.saisie_postale_adresse);
        final EditText nb_part_min = (EditText) findViewById (R.id.saisie_nombre_min);
        final EditText echeance = (EditText) findViewById (R.id.saisie_date_echeance);

        final RequestQueue queue = Volley.newRequestQueue(this);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateEcheance = false;
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        btnDateEcheance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateEcheance = true;
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        boutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boutton.setEnabled(false);
                String nomRando = libelle.getText().toString();
                String dateRando = date.getText().toString();
                String lieuRando = lieu.getText().toString();
                String nb_part_minRando = nb_part_min.getText().toString();
                String echeanceRando = echeance.getText().toString();

                String url = "http://185.224.139.170:8080/ajoutRandonnee?libelle=" + nomRando + "&date=" + dateRando + "&lieu=" + lieuRando +
                        "&date_echeance=" + echeanceRando + "&participants_min=" + nb_part_minRando + "&participants_inscrits=0&participants_handicapes=0&active=1&valider=0";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boutton.setEnabled(true);
                        Intent intent = new Intent(AssoAddEventActivity.this, AssoMainActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        boutton.setEnabled(true);
                        error.printStackTrace();
                    }
                });
                queue.add(jsonObjectRequest);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String dateCourante = DateFormat.getDateInstance().format(calendar.getTime());

        if(dateEcheance) {
            EditText dateEcheance = (EditText) findViewById(R.id.saisie_date_echeance);
            dateEcheance.setText(dateCourante);
        } else {
            EditText dateRando = (EditText) findViewById(R.id.saisie_date_rando);
            dateRando.setText(dateCourante);
        }
    }
}
