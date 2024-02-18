package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class InsertDataCitas extends AppCompatActivity {

    EditText t1, t2, t3, t4,t5, t6, t7;
    DbHelperCitas db;
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_citas);



        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);
        t3 = (EditText) findViewById(R.id.t3);
        t4 = (EditText) findViewById(R.id.t4);
        t5 = (EditText) findViewById(R.id.t5);
        t6 = (EditText) findViewById(R.id.t6);
        t7 = (EditText) findViewById(R.id.t7);
        insert = (Button) findViewById(R.id.btnInsert);

        db = new DbHelperCitas(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DNI = t1.getText().toString();
                String Nombre = t2.getText().toString();
                String  Apellidos = t3.getText().toString();
                String Razon = t4.getText().toString();
                String Disponibilidad = t6.getText().toString();
                String Medico = t5.getText().toString();
                String Historial = t7.getText().toString();
                String Cita = " ";

                Boolean checkinsertdata = db.insertUserData(DNI, Nombre, Apellidos, Razon, Disponibilidad, Cita, Medico, Historial);
                if(checkinsertdata == true){
                    Toast.makeText(InsertDataCitas.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    t7.setText("");

                }else {
                    Toast.makeText(InsertDataCitas.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });




        ImageView btnbackpage = findViewById(R.id.back);
        btnbackpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertDataCitas.this, SegundaPagina.class);
                startActivity(intent);
            }
        });




    }






}