package com.mx.dva.models;

import com.mx.dva.notaciones.JsonNotacion;
import java.time.LocalDate;

public class Producto {
    @JsonNotacion(letraMayuscula = false, desString = "a")
    private String nombre;
    @JsonNotacion
    private Double precio;
    private LocalDate fecha;

    public Producto() {
    }

    public Producto(String nombre, Double precio, LocalDate fecha) {
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
}
