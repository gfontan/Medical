package com.example.aplicacionfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.gms.location.LocationRequest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Ser_ubicacion_emergencias extends AppCompatActivity {

    private SwitchCompat switchCompat;

    private SharedPreferences sharedPreferences;
    private LocationRequest locationRequest;
    public static final int REQUEST_CHECK_SETTING = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser_ubicacion_emergencias);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);


        switchCompat = findViewById(R.id.SwitchCompatUbicacion);
        switchCompat.setChecked(sharedPreferences.getBoolean("Ubicacion", false));

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Guarda el estado actual del switch en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("Ubicacion", isChecked);
                editor.apply();
            }
        });

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().
                        addLocationRequest(locationRequest);
                builder.setAlwaysShow(true);

                Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                        .checkLocationSettings(builder.build());

                result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                        try {
                            LocationSettingsResponse response = task.getResult(ApiException.class);
                            Toast.makeText(Ser_ubicacion_emergencias.this, "GPS encendido", Toast.LENGTH_SHORT).show();
                        } catch (ApiException e) {
                            switch (e.getStatusCode()) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    try {
                                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                        resolvableApiException.startResolutionForResult(Ser_ubicacion_emergencias.this, REQUEST_CHECK_SETTING);
                                    } catch (IntentSender.SendIntentException ex) {
                                        ex.printStackTrace();
                                    }
                                    break;

                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    break;
                            }
                        }
                    }
                });
            }
        });

        ImageView btnbackpage = findViewById(R.id.back);
        btnbackpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ser_ubicacion_emergencias.this, Seguridad.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHECK_SETTING) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    Toast.makeText(Ser_ubicacion_emergencias.this, "GPS apagado", Toast.LENGTH_SHORT).show();
                    break;

                case Activity.RESULT_CANCELED:
                    Toast.makeText(Ser_ubicacion_emergencias.this, "GPS es necesario para encenderlo", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
