package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class forgot_password extends AppCompatActivity {

    private EditText correoEditText;
    private EditText contraseñaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        correoEditText = findViewById(R.id.correo);
        contraseñaEditText = findViewById(R.id.contraseña);

        Button btnNextPage = findViewById(R.id.cambiar);
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo = correoEditText.getText().toString().trim();
                String contraseña = contraseñaEditText.getText().toString().trim();

                if (correo.isEmpty() || contraseña.isEmpty()) {

                    Toast.makeText(forgot_password.this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(forgot_password.this, Login.class);
                    startActivity(intent);
                }
            }
        });

        Button inicarSesion = findViewById(R.id.iniciar);
        inicarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgot_password.this, Login.class);
                startActivity(intent);

            }
        });

    }
}