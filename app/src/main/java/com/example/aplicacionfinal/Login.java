package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    private EditText correoEditText;
    private EditText contraseñaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correoEditText = findViewById(R.id.correo);
        contraseñaEditText = findViewById(R.id.contraseña);

        Button btnNextPage = findViewById(R.id.login);
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = correoEditText.getText().toString().trim();
                String contraseña = contraseñaEditText.getText().toString().trim();

                if (correo.isEmpty() || contraseña.isEmpty()) {

                    Toast.makeText(Login.this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        TextView contraseñaOlvidada = findViewById(R.id.contraseñaOlvidada);
        contraseñaOlvidada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, forgot_password.class);
                startActivity(intent);
            }
        });
    }
}
