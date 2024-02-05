package com.example.aplicacionfinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CitasList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> dniList, nombreList, apellidosList, razonList, disponibilidadList, medicoList, historialList;
    DbHelperCitas db;
    ListAdapterCitas adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_list);
        db = new DbHelperCitas(this);
        dniList = new ArrayList<>();
        nombreList = new ArrayList<>();
        apellidosList = new ArrayList<>();
        razonList = new ArrayList<>();
        disponibilidadList = new ArrayList<>();
        medicoList = new ArrayList<>();
        historialList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ListAdapterCitas(this, dniList, nombreList, apellidosList, razonList, disponibilidadList, medicoList, historialList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

        ImageView btnbackpage = findViewById(R.id.back);
        btnbackpage.setOnClickListener(v -> {
            Intent intent = new Intent(CitasList.this, SegundaPagina.class);
            startActivity(intent);
        });

    }

    private void displayData() {

        Cursor cursor = db.getdata();

        if(cursor.getCount() == 0) {
            Toast.makeText(CitasList.this, "No hay datos disponibles", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            dniList.add(cursor.getString(0));
            nombreList.add(cursor.getString(1));
            apellidosList.add(cursor.getString(2));
            razonList.add(cursor.getString(3));
            disponibilidadList.add(cursor.getString(4));
            medicoList.add(cursor.getString(5));
            historialList.add(cursor.getString(6));
        }

        // Notifica al adaptador sobre los cambios en los datos
        adapter.notifyDataSetChanged();
    }

}