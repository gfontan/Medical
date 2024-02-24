package com.example.aplicacionfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class Settings extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SwitchCompat nightModeSwitch;
    private SwitchCompat notificationsSwitch;
    private SwitchCompat twoFactorAuthSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inicializa SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Obtiene referencias a los switches
        nightModeSwitch = findViewById(R.id.noMolestar);
        notificationsSwitch = findViewById(R.id.notificaciones);
        twoFactorAuthSwitch = findViewById(R.id.doblefactor);

        // Establece el estado de los switches basado en los valores almacenados en SharedPreferences
        nightModeSwitch.setChecked(sharedPreferences.getBoolean("nightModeSwitch", false));
        notificationsSwitch.setChecked(sharedPreferences.getBoolean("notificationsSwitch", false));
        twoFactorAuthSwitch.setChecked(sharedPreferences.getBoolean("twoFactorAuthSwitch", false));

        // Listener para el cambio de estado de los switches
        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Guarda el estado actual del switch en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("nightModeSwitch", isChecked);
                editor.apply();
            }
        });

        notificationsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Guarda el estado actual del switch en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("notificationsSwitch", isChecked);
                editor.apply();
            }
        });

        twoFactorAuthSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Guarda el estado actual del switch en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("twoFactorAuthSwitch", isChecked);
                editor.apply();
            }
        });

        // Listener para el clic en el botón de Cerrar Sesión
        ImageView btnNextPage = findViewById(R.id.CerrarSesion);
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Login.class);
                startActivity(intent);
            }
        });

        ImageView btnTarjeta = findViewById(R.id.tarjetaSanitaria);
        btnTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, TarjetaSanitaria.class);
                startActivity(intent);
            }
        });

        ImageView btnAtras = findViewById(R.id.back);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, SegundaPagina.class);
                startActivity(intent);
            }
        });

        ImageView RateUs = findViewById(R.id.calificanos);
        RateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Calificanos.class);
                startActivity(intent);
            }
        });

        ImageView ContactUs = findViewById(R.id.contactUs);
        ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, ContactUs.class);
                startActivity(intent);
            }
        });

        ImageView Seguridad = findViewById(R.id.seguridad);
        Seguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Seguridad.class);
                startActivity(intent);
            }
        });

        ImageView preguntas = findViewById(R.id.preguntas);
        preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.aldomedina.com/foro-preguntas/");
            }
        });


    }

    private void gotoUrl(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }


}


