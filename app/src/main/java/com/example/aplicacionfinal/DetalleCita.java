package com.example.aplicacionfinal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleCita extends AppCompatActivity {

    private CheckBox checkboxPendiente, checkboxEliminar;
    private DbHelperCitas dbHelper;
    private ListAdapterCitas adapter;
    private String dni;
    private TextView textViewNombre, textViewApellidos, textViewRazon, textViewDisponibilidad, textViewCita, textViewMedico, textViewHistorial, textViewDNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cita);


        ImageView btnNextPage = findViewById(R.id.atrasCita);
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleCita.this, CitasList.class);
                startActivity(intent);
            }
        });


        dbHelper = new DbHelperCitas(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dni = extras.getString("DNI");

            textViewDNI = findViewById(R.id.textDNI);
            textViewNombre = findViewById(R.id.textNombre);
            textViewApellidos = findViewById(R.id.textApellidos);
            textViewRazon = findViewById(R.id.textRazon);
            textViewDisponibilidad = findViewById(R.id.textDisponibilidad);
            textViewCita = findViewById(R.id.textCita);
            textViewMedico = findViewById(R.id.textMedico);
            textViewHistorial = findViewById(R.id.textHistorial);
        }

        llenarDatosCita();

        checkboxPendiente = findViewById(R.id.checkbox_pendiente);
        checkboxEliminar = findViewById(R.id.checkbox_eliminar);

        checkboxPendiente.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mostrarConfirmacionMarcarPendiente();
            }
        });

        checkboxEliminar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mostrarConfirmacionEliminarCita();
            }
        });


    }

    private void llenarDatosCita() {
        // Obtener los detalles de la cita del paciente usando el DNI
        Cursor cursor = dbHelper.getCitaByDNI(dni);

        if (cursor != null && cursor.moveToFirst()) {
            // Obtener los datos de la cita del cursor
            @SuppressLint("Range") String dni = cursor.getString(cursor.getColumnIndex("DNI"));
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
            @SuppressLint("Range") String apellidos = cursor.getString(cursor.getColumnIndex("Apellidos"));
            @SuppressLint("Range") String razon = cursor.getString(cursor.getColumnIndex("Razon"));
            @SuppressLint("Range") String disponibilidad = cursor.getString(cursor.getColumnIndex("Disponibilidad"));
            @SuppressLint("Range") String cita = cursor.getString(cursor.getColumnIndex("Cita"));
            @SuppressLint("Range") String medico = cursor.getString(cursor.getColumnIndex("Médico"));
            @SuppressLint("Range") String historial = cursor.getString(cursor.getColumnIndex("Historial"));

            // Mostrar los datos en los TextView correspondientes

            textViewDNI.setText("-DNI: " + dni);
            textViewNombre.setText("-Nombre: " + nombre);
            textViewApellidos.setText("-Apellidos: " + apellidos);
            textViewRazon.setText("-Razon: " + razon);
            textViewDisponibilidad.setText("-Disponibilidad: " + disponibilidad);
            textViewCita.setText("-Cita: "  + cita);
            textViewMedico.setText("-Médico: " + medico);
            textViewHistorial.setText("-Historial: " + historial);

            // Cerrar el cursor después de usarlo para evitar posibles fugas de memoria
            cursor.close();
        } else {
            // Si no se encontraron datos para el DNI proporcionado, mostrar un mensaje o tomar alguna acción apropiada
            Toast.makeText(this, "No se encontraron detalles de la cita para el paciente", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarEstadoCheckBox(boolean estado) {
        SharedPreferences preferences = getSharedPreferences("checkbox_state", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("estado_checkbox", estado);
        editor.apply();
    }

    private void cargarEstadoCheckBox() {
        SharedPreferences preferences = getSharedPreferences("checkbox_state", MODE_PRIVATE);
        boolean estado = preferences.getBoolean("estado_checkbox", false);
        checkboxPendiente.setChecked(estado);
    }

    private void mostrarConfirmacionMarcarPendiente() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro de que deseas marcar esta cita como pendiente?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        marcarComoPendiente();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        checkboxPendiente.setChecked(false);
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void mostrarConfirmacionEliminarCita() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro de que deseas eliminar esta cita?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        eliminarCita();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        checkboxEliminar.setChecked(false);
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void marcarComoPendiente() {
        // Lógica para marcar la cita como pendiente
        boolean marcada = dbHelper.marcarComoPendiente(dni);
        if (marcada) {
            Toast.makeText(this, "Cita marcada como pendiente", Toast.LENGTH_SHORT).show();
            checkboxPendiente.setChecked(true);
            guardarEstadoCheckBox(true);// Marcar el checkbox como pendiente
        } else {
            Toast.makeText(this, "Error al marcar la cita como pendiente", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarCita() {
        // Lógica para eliminar la cita
        boolean eliminada = dbHelper.eliminarCita(dni);
        if (eliminada) {
            Toast.makeText(this, "Cita eliminada", Toast.LENGTH_SHORT).show();
            // Aquí puedes realizar alguna acción adicional, si es necesario
            finish(); // Finalizar la actividad después de eliminar la cita
        } else {
            Toast.makeText(this, "Error al eliminar la cita", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarRelojEnCita() {
        // Obtener el estado actual de la cita
        boolean citaPendiente = checkboxPendiente.isChecked();

        // Si la cita está pendiente, actualizar su estado y volver al RecyclerView
        if (citaPendiente) {
            // Marcar la cita como pendiente en la base de datos
            boolean marcada = dbHelper.marcarComoPendiente(dni);
            if (marcada) {
                // Mostrar un mensaje de éxito
                Toast.makeText(this, "Cita marcada como pendiente", Toast.LENGTH_SHORT).show();
                // Actualizar la vista del RecyclerView después de marcar la cita como pendiente
                adapter.notifyDataSetChanged();
                // Volver a la actividad del RecyclerView
                onBackPressed();
            } else {
                // Mostrar un mensaje de error si no se pudo marcar la cita como pendiente
                Toast.makeText(this, "Error al marcar la cita como pendiente", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Mostrar un mensaje si la cita no está marcada como pendiente
            Toast.makeText(this, "La cita no está pendiente", Toast.LENGTH_SHORT).show();
        }
    }
}
