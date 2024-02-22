package com.example.aplicacionfinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListAdapterCitas extends RecyclerView.Adapter<ListAdapterCitas.ViewHolder> {

    private Context context;
    private ArrayList<String> dniList, nombreList, apellidosList, razonList, disponibilidadList, fechaList, citaList, medicoList, historialList;


    // Interfaz para manejar clics en elementos del RecyclerView
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    // Método para configurar el OnItemClickListener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ListAdapterCitas(Context context, ArrayList<String> dniList, ArrayList<String> nombreList, ArrayList<String> apellidosList,
                            ArrayList<String> razonList, ArrayList<String> disponibilidadList, ArrayList <String> fechaList, ArrayList<String> citaList, ArrayList <String> medicoList,
                            ArrayList<String> historialList) {
        this.context = context;
        this.dniList = dniList;
        this.nombreList = nombreList;
        this.apellidosList = apellidosList;
        this.razonList = razonList;
        this.disponibilidadList = disponibilidadList;
        this.fechaList = fechaList;
        this.citaList = citaList;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textDNI.setText("DNI: " + dniList.get(position));
        holder.textNombre.setText("Nombre: " + nombreList.get(position));
        holder.textApellidos.setText("Apellidos: " + apellidosList.get(position));
        holder.textRazon.setText("Razón: " + razonList.get(position));
        holder.textDisponibilidad.setText("Hora: " + disponibilidadList.get(position));
        holder.textFecha.setText("Fecha: " + fechaList.get(position));
        if (holder.textCita != null) {
            String cita = citaList.get(position);
            holder.textCita.setText("Cita: " + (cita != null ? cita : "Por asignar")); // Si cita es nulo, establece el texto como cadena vacía
        }

        holder.textMedico.setText("Medico: " + medicoList.get(position));
        holder.textHistorial.setText("Historial: " + historialList.get(position));

        String cita = citaList.get(position);
        if ("Pendiente".equals(cita)) {
            holder.imageViewClock.setVisibility(View.VISIBLE);
        } else {
            holder.imageViewClock.setVisibility(View.GONE);
        }

        cita = citaList.get(position);
        if ("Pendiente".equals(cita)) {
            holder.imageViewClock.setVisibility(View.VISIBLE);
        } else {
            holder.imageViewClock.setVisibility(View.GONE);
        }
        // Configurar el listener para marcar como pendiente
        holder.imageViewClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar el estado de disponibilidad a "Pendiente"
                citaList.set(position, "Pendiente");
                // Notificar al adaptador sobre el cambio en los datos
                notifyDataSetChanged();
                // Aquí puedes realizar cualquier otra acción necesaria, como guardar el estado en la base de datos, etc.
            }
        });
        // Configuración del OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dniList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDNI, textNombre, textApellidos, textRazon, textDisponibilidad, textFecha, textCita, textMedico, textHistorial;

        ImageView imageViewClock;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textDNI = itemView.findViewById(R.id.dni);
            textNombre = itemView.findViewById(R.id.nombreCompleto);
            textApellidos = itemView.findViewById(R.id.apellidos);
            textRazon = itemView.findViewById(R.id.razon);
            textDisponibilidad = itemView.findViewById(R.id.disponibilidad);
            textFecha = itemView.findViewById(R.id.fecha);
            textMedico = itemView.findViewById(R.id.medico);
            textHistorial = itemView.findViewById(R.id.historial);
            imageViewClock = itemView.findViewById(R.id.imageViewClock);
        }
    }
}
