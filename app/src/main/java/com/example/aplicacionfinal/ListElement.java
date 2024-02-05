package com.example.aplicacionfinal;

import java.io.Serializable;

public class ListElement implements Serializable {

    public String color;
    public String nombre;
    public String dosis;
    public String sintomas;

    public ListElement(String color, String nombre, String dosis, String sintomas) {
        this.color = color;
        this.nombre = nombre;
        this.dosis = dosis;
        this.sintomas = sintomas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre){
        this.nombre= Nombre;
    }


    public String getDosis() {
        return dosis;
    }

    public void setDosis (String dosis) {
        this.dosis = dosis;
    }
    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas (String Sintomas){
        this.sintomas= Sintomas;
    }
}