package com.example.aplicacionfinal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class TarjetaSanitaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta_sanitaria);

        // Inicializa los TextViews con los datos de la tarjeta sanitaria
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewEdad = findViewById(R.id.textViewEdad);
        TextView textViewNumeroSeguro = findViewById(R.id.textViewNumeroSeguro);
        TextView textViewGrupoSanguineo = findViewById(R.id.textViewGrupoSanguineo);
        TextView textViewAlergias = findViewById(R.id.textViewAlergias);
        TextView textViewMedicamentos = findViewById(R.id.textViewMedicamentos);
        TextView textViewContactoEmergencia = findViewById(R.id.textViewContactoEmergencia);

        // Datos de ejemplo de la tarjeta sanitaria
        String nombre = "Max GoodWin";
        int edad = 35;
        String numeroSeguro = "123456789";
        String grupoSanguineo = "O+";
        String alergias = "N/A";
        String medicamentos = "Paracetamol";
        String contactoEmergencia = "112";

        // Establece los datos en los TextViews
        textViewNombre.setText("🌺 Nombre: " + nombre);
        textViewEdad.setText("\uD83C\uDF88 Edad: " + edad + " años");
        textViewNumeroSeguro.setText("🚑 Número de Seguro: " + numeroSeguro);
        textViewGrupoSanguineo.setText("💉 Grupo Sanguíneo: " + grupoSanguineo);
        textViewAlergias.setText("🌼 Alergias: " + alergias);
        textViewMedicamentos.setText("💊 Medicamentos: " + medicamentos);
        textViewContactoEmergencia.setText("🆘Contacto de Emergencia: " + contactoEmergencia);
    }
}
