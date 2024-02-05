package com.example.aplicacionfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListAdapterCitas extends RecyclerView.Adapter<ListAdapterCitas.ViewHolder> {

    private Context context;
    private ArrayList<String> dniList, nombreList, apellidosList, razonList, disponibilidadList, medicoList, historialList;


    public ListAdapterCitas(Context context, ArrayList<String> dniList, ArrayList<String> nombreList, ArrayList<String> apellidosList,
                       ArrayList<String> razonList, ArrayList<String> disponibilidadList, ArrayList<String> medicoList,
                       ArrayList<String> historialList) {
        this.context = context;
        this.dniList = dniList;
        this.nombreList = nombreList;
        this.apellidosList = apellidosList;
        this.razonList = razonList;
        this.disponibilidadList = disponibilidadList;
        this.medicoList = medicoList;
        this.historialList = historialList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_element_citas, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textDNI.setText(dniList.get(position));
        holder.textNombre.setText(nombreList.get(position));
        holder.textApellidos.setText(apellidosList.get(position));
        holder.textRazon.setText(razonList.get(position));
        holder.textDisponibilidad.setText(disponibilidadList.get(position));
        holder.textMedico.setText(medicoList.get(position));
        holder.textHistorial.setText(historialList.get(position));
    }

    @Override
    public int getItemCount() {
        return dniList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDNI, textNombre, textApellidos, textRazon, textDisponibilidad, textMedico, textHistorial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textDNI = itemView.findViewById(R.id.dni);
            textNombre = itemView.findViewById(R.id.nombreCompleto);
            textApellidos = itemView.findViewById(R.id.apellidos);
            textRazon = itemView.findViewById(R.id.razon);
            textDisponibilidad = itemView.findViewById(R.id.disponibilidad);
            textMedico = itemView.findViewById(R.id.medico);
            textHistorial = itemView.findViewById(R.id.historial);

        }
    }
}
