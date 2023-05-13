package com.mx.dva.test;

import com.mx.dva.models.Producto;
import com.mx.dva.notaciones.JsonNotacion;
import com.mx.dva.utilities.ObjetToJson;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDos {

    public static void main(String[] args) {
        Producto p = new Producto();
        p.setFecha(LocalDate.now());
        p.setNombre("asd");
        p.setPrecio(35.4);

        String productoJson = ObjetToJson.jsonResult(p);
        System.out.println(productoJson);
    }
}
