package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleMedicamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_medicamento);

        ImageView btnbackpage = findViewById(R.id.back);
        btnbackpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleMedicamento.this, MedicamentoList.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String nombreMedicamento = intent.getStringExtra("nombreMedicamento");
            String prospecto = intent.getStringExtra("prospecto");

            // Mostrar el nombre del medicamento en un TextView
            TextView nombreTextView = findViewById(R.id.nombreMedicamentoTextView);
            nombreTextView.setText(nombreMedicamento);

            // Mostrar el prospecto del medicamento en otro TextView
            TextView prospectoTextView = findViewById(R.id.prospectoTextView);
            prospectoTextView.setText(prospecto);
        }
    }
}