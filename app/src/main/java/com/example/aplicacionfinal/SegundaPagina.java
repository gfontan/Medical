package com.example.aplicacionfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class SegundaPagina extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_pagina);

        ImageButton btnNextPage = findViewById(R.id.Ajustes);
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaPagina.this, Settings.class));
            }
        });

        ImageView lista = findViewById(R.id.lista);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaPagina.this, MedicamentoList.class));
            }
        });

        ImageView btnStardbapp = findViewById(R.id.AccesoBase);
        btnStardbapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaPagina.this, InsertData.class));
            }
        });

        ImageView citas = findViewById(R.id.citas);
        citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaPagina.this, CitasList.class));

            }
        });

        ImageView PedirCita = findViewById(R.id.PedirCita);
        PedirCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaPagina.this, InsertDataCitas.class));
            }
        });

        ImageView localizacion = findViewById(R.id.mapa);
        localizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaPagina.this, GMapActivity.class));
            }
        });
    }
}
