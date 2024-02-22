package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InsertDataCitas extends AppCompatActivity {

    EditText t1, t2, t3, t4, t5, t6, t7, t8;
    DbHelperCitas db;
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_citas);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        insert = findViewById(R.id.btnInsert);

        db = new DbHelperCitas(this);

        // Configurar el selector de fecha para el campo de texto t6 (Disponibilidad horaria)
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(t7);
            }
        });

        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DNI = t1.getText().toString();
                String Nombre = t2.getText().toString();
                String Apellidos = t3.getText().toString();
                String Razon = t4.getText().toString();
                String Medico = t5.getText().toString();
                String Disponibilidad = t6.getText().toString();
                String Fecha = t7.getText().toString();
                String Historial = t8.getText().toString();
                String Cita = "";

                Boolean checkinsertdata = db.insertUserData(DNI, Nombre, Apellidos, Razon, Disponibilidad, Fecha, Cita, Medico, Historial);
                if (checkinsertdata == true) {
                    Toast.makeText(InsertDataCitas.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    t7.setText("");
                    t8.setText("");

                } else {
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

    // Método para mostrar el selector de fecha
    private void showDatePickerDialog(final EditText editText) {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Selecciona una fecha");

        // Restringir la selección de fechas a partir de hoy
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setStart(Calendar.getInstance().getTimeInMillis());

        builder.setCalendarConstraints(constraintsBuilder.build());

        final MaterialDatePicker<Long> materialDatePicker = builder.build();
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                // La fecha ha sido seleccionada
                // `selection` contiene la fecha seleccionada en milisegundos desde la época
                // Aquí puedes establecer la fecha seleccionada en el EditText
                editText.setText(materialDatePicker.getHeaderText());
            }
        });

        materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }


    // Método para mostrar el TimePickerDialog
    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        updateTimeInView(calendar);
                    }
                }, hour, minute, true);

        timePickerDialog.show();
    }

    private void updateTimeInView(Calendar calendar) {
        String myFormat = "HH:mm"; // Formato de la hora
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        t6.setText(sdf.format(calendar.getTime())); // Actualizar el texto del EditText con la hora seleccionada
    }
}


