package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class InsertData extends AppCompatActivity {

    EditText t1, t2, t3, t4, t5;
    DbManager db;
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);
        t3 = (EditText) findViewById(R.id.t3);
        t4 = (EditText) findViewById(R.id.t4);
        t5 = (EditText) findViewById(R.id.t5);
        insert = (Button) findViewById(R.id.btnInsert);

        db = new DbManager(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod_nacional = t1.getText().toString();
                String nombre = t2.getText().toString();
                String  dosis = t3.getText().toString();
                String sintomas = t4.getText().toString();
                String prospecto = t5.getText().toString();

                Boolean checkinsertdata = db.insertUserData(cod_nacional, nombre, dosis, sintomas, prospecto);
                if(checkinsertdata == true){
                    Toast.makeText(InsertData.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                }else {
                    Toast.makeText(InsertData.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ImageView btnbackpage = findViewById(R.id.back);
        btnbackpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertData.this, SegundaPagina.class);
                startActivity(intent);
            }
        });

    }
}