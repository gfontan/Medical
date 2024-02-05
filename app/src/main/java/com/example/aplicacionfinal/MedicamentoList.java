package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MedicamentoList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> nombre, dosis, sintomas;
    DbManager db;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento_list);
        db = new DbManager(this);
        nombre = new ArrayList<>();
        dosis = new ArrayList<>();
        sintomas = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ListAdapter(this, nombre, dosis, sintomas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

        ImageView btnbackpage = findViewById(R.id.back);
        btnbackpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicamentoList.this, SegundaPagina.class);
                startActivity(intent);
            }
        });

    }

    private void displayData() {

        Cursor cursor = db.getdata();

        if(cursor.getCount() == 0) {
            Toast.makeText(MedicamentoList.this, "No hay datos disponibles", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            nombre.add(cursor.getString(1)); // El índice 0 es Cod_Nacional, usa 1 para Nombre
            dosis.add(cursor.getString(2)); // El índice 1 es Nombre, usa 2 para Dosis
            sintomas.add(cursor.getString(3)); // El índice 2 es Dosis, usa 3 para Síntomas
        }

        // Notifica al adaptador sobre los cambios en los datos
        adapter.notifyDataSetChanged();
    }

}