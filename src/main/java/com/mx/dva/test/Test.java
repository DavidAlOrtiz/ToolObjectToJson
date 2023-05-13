package com.mx.dva.test;

import com.mx.dva.models.Producto;
import com.mx.dva.notaciones.JsonNotacion;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

    public static void main(String[] args) {
        Producto producto = new Producto();
        producto.setFecha(LocalDate.now());
        producto.setNombre("TV LG");
        producto.setPrecio(99.3);

        Field[] fiels = producto.getClass().getDeclaredFields();

        String valoresJson = Arrays.stream(fiels)
                .filter(p -> p.isAnnotationPresent(JsonNotacion.class))
                .map(p -> {
                    p.setAccessible(true);
                    String valor = "";
                    String nombre = p.getAnnotation(JsonNotacion.class).nombre().equals("")
                            ? p.getName() : p.getAnnotation(JsonNotacion.class).nombre();
                    try {
                        valor = "\"" + nombre + "\":\"" + p.get(producto) + "\"";
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return valor;
                }).reduce("{", (a,b)->{
                    if("{".equals(a)){
                        return a + b;
                    }
                    return a + "," + b;
                }).concat("}");
        System.out.println("valoresJson = " + valoresJson);
    }
}
