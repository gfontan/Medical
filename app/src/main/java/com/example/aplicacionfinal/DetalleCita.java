package com.example.aplicacionfinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleCita extends AppCompatActivity {

    private CheckBox checkboxPendiente, checkboxEliminar;
    private DbHelperCitas dbHelper;
    ListAdapterCitas adapter;
    private String dni;
    private TextView textViewNombre, textViewApellidos, textViewRazon, textViewDisponibilidad, textViewMedico, textViewHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cita);

        dbHelper = new DbHelperCitas(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dni = extras.getString("DNI");
            String nombre = extras.getString("Nombre");
            String apellidos = extras.getString("Apellidos");
            String razon = extras.getString("Razon");
            String disponibilidad = extras.getString("Disponibilidad");
            String medico = extras.getString("Medico");
            String historial = extras.getString("Historial");


            textViewNombre = findViewById(R.id.textNombre);
            textViewNombre.setText(nombre);

            textViewApellidos = findViewById(R.id.textApellidos);
            textViewApellidos.setText(apellidos);

            textViewRazon = findViewById(R.id.textRazon);
            textViewRazon.setText(razon);

            textViewDisponibilidad = findViewById(R.id.textDisponibilidad);
            textViewDisponibilidad.setText(disponibilidad);

            textViewMedico = findViewById(R.id.textMedico);
            textViewMedico.setText(medico);

            textViewHistorial = findViewById(R.id.textHistorial);
            textViewHistorial.setText(historial);

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

        Button buttonConfirmar = findViewById(R.id.button_confirmar);
        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad del RecyclerView
                onBackPressed();
                actualizarListaYVolver();
            }
        });
    }

    private void llenarDatosCita() {
        // Lógica para llenar los datos de la cita en los TextViews
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
            // Aquí puedes realizar alguna acción adicional, si es necesario
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
        } else {
            Toast.makeText(this, "Error al eliminar la cita", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarRelojEnCita() {
        // Obtener el estado actual de la cita
        boolean citaPendiente = checkboxPendiente.isChecked();

        // Si la cita no está marcada como pendiente, actualiza su estado
        if (!citaPendiente) {
            // Marcar la cita como pendiente en la base de datos
            boolean marcada = dbHelper.marcarComoPendiente(dni);
            if (marcada) {
                // Mostrar un mensaje de éxito
                Toast.makeText(this, "Cita marcada como pendiente", Toast.LENGTH_SHORT).show();

                // Actualizar la vista del RecyclerView después de marcar la cita como pendiente
                adapter.notifyDataSetChanged();
            } else {
                // Mostrar un mensaje de error si no se pudo marcar la cita como pendiente
                Toast.makeText(this, "Error al marcar la cita como pendiente", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void actualizarListaYVolver() {
        // Lógica para actualizar la lista y volver a la actividad anterior
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}

