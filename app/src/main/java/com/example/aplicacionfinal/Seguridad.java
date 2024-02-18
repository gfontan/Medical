package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Seguridad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad);

        ImageView btnbackpage = findViewById(R.id.back);
        btnbackpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seguridad.this, Settings.class);
                startActivity(intent);
            }
        });

        ImageView RateUs = findViewById(R.id.calificanos);
        RateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seguridad.this, Calificanos.class);
                startActivity(intent);
            }
        });

        ImageView ContactosEmergencia = findViewById(R.id.contactos);
        ContactosEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seguridad.this, ContactosEmergenciaActivity.class);
                startActivity(intent);
            }
        });


        ImageView ContactUs = findViewById(R.id.contactUs);
        ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seguridad.this, ContactUs.class);
                startActivity(intent);
            }
        });

        ImageView UbicacionEmergencia = findViewById(R.id.UbicacionEmergencia);
        UbicacionEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seguridad.this, Ser_ubicacion_emergencias.class);
                startActivity(intent);
            }
        });

        SwitchCompat permisos = findViewById(R.id.permisos);
        permisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seguridad.this, Permisos.class);
                startActivity(intent);
            }
        });
    }
}