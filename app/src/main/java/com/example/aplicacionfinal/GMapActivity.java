package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class GMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmap);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Lista de coordenadas y títulos para los marcadores
        ArrayList<MarkerData> markerDataList = new ArrayList<>();
        markerDataList.add(new MarkerData("Hospital Universitario La Paz", new LatLng(40.48093056, -3.68735833)));
        markerDataList.add(new MarkerData("Hospital Gregorio de Maraño", new LatLng(40.419194444444, -3.6709)));
        markerDataList.add(new MarkerData("Hospital Niño Jesús", new LatLng(40.414357, -3.676186)));
        markerDataList.add(new MarkerData("Hospital de La Princesa", new LatLng(40.434111111111, -3.676)));
        markerDataList.add(new MarkerData("Hospital Universitario Infanta Leonor", new LatLng(40.38641, -3.617849)));
        markerDataList.add(new MarkerData("Hospital Clínico San Carlos", new LatLng(40.434722, -3.717222)));
        markerDataList.add(new MarkerData("Hospital 12 de Octubre", new LatLng(40.389167, -3.697778)));
        markerDataList.add(new MarkerData("Hospital Ramón y Cajal", new LatLng(40.478056, -3.709444)));

        // Agregar marcadores al mapa
        for (MarkerData markerData : markerDataList) {
            mMap.addMarker(new MarkerOptions().position(markerData.getLocation()).title(markerData.getTitle()));
        }

        // Mover la cámara a la primera ubicación en la lista y hacer zoom
        if (!markerDataList.isEmpty()) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerDataList.get(0).getLocation(), 12));
        }
    }

    // Clase auxiliar para almacenar datos de marcadores (ubicación y título)
    private static class MarkerData {
        private String title;
        private LatLng location;

        MarkerData(String title, LatLng location) {
            this.title = title;
            this.location = location;
        }

        String getTitle() {
            return title;
        }

        LatLng getLocation() {
            return location;
        }
    }
}
