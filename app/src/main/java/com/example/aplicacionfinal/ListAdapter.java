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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private ArrayList nombre, dosis, sintomas;
    private int[] colores = {
            R.color.pastel1, R.color.pastel2, R.color.pastel3, R.color.pastel4, R.color.pastel5,
            R.color.pastel6, R.color.pastel7, R.color.pastel8, R.color.pastel9, R.color.pastel10,
            R.color.pastel11, R.color.pastel12, R.color.pastel13, R.color.pastel14, R.color.pastel15
    };

    public ListAdapter(Context context, ArrayList nombre, ArrayList dosis, ArrayList sintomas) {
        this.context = context;
        this.nombre = nombre;
        this.dosis = dosis;
        this.sintomas = sintomas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_element, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(String.valueOf(nombre.get(position)));
        holder.dosis.setText(String.valueOf(dosis.get(position)));
        holder.sintomas.setText(String.valueOf(sintomas.get(position)));


        // Setea el color del texto a negro
        holder.nombre.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        holder.dosis.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        holder.sintomas.setTextColor(ContextCompat.getColor(context, android.R.color.black));

        // Asigna colores pastel aleatorios al fondo del icono (logo)
        int randomLogoColor = colores[new Random().nextInt(colores.length)];
        holder.iconImageView.setBackgroundColor(ContextCompat.getColor(context, randomLogoColor));
    }

    @Override
    public int getItemCount() {
        return nombre.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, dosis, sintomas;
        ImageView iconImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            dosis = itemView.findViewById(R.id.dosis);
            sintomas = itemView.findViewById(R.id.sintomas);
            iconImageView = itemView.findViewById(R.id.iconImageView);
        }
    }
}
