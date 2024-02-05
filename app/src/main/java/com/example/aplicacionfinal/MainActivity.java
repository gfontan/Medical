package com.example.aplicacionfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionfinal.R;
import com.example.aplicacionfinal.SegundaPagina;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SegundaPagina.class);
                startActivity(intent);
                finish();
                mostrarToast();
            }
        }, 3000);

    }
    private void mostrarToast()

    {
        Toast.makeText(getApplicationContext(), "Entrando...", Toast.LENGTH_SHORT).show();

    }
}
