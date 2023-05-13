package com.mx.dva.utilities;


import com.mx.dva.excepciones.PersonalizadoE;
import com.mx.dva.notaciones.JsonNotacion;
import com.mx.dva.test.TestDos;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjetToJson {

    public static String jsonResult(Object object) {
        //Colocamos el refector
        if(object == null){
            throw new PersonalizadoE("a"); 
        }
        Field[] archivos = object.getClass().getDeclaredFields();
        return Arrays.stream(archivos)
                .filter(f -> f.isAnnotationPresent(JsonNotacion.class))
                .map(f -> {
                    f.setAccessible(true);
                    String valores = "";
                    String nombre = f.getAnnotation(JsonNotacion.class).nombre().equals("")
                            ? f.getName()
                            : f.getAnnotation(JsonNotacion.class).nombre();
                    try {
                        Object valor = f.get(object);
                        if( f.getAnnotation(JsonNotacion.class).letraMayuscula() && 
                                valor instanceof String){
                            String nuevoValor = (String) valor;
                            nuevoValor = String.valueOf(nuevoValor.charAt(0)).toUpperCase()+
                                       nuevoValor.substring(1).toLowerCase();
                            f.set(object, nuevoValor);
                        }
                        valores = "\"" + nombre + "\":" + "\"" + f.get(object) + "\"";
                    } catch (IllegalArgumentException ex) {
                       throw new PersonalizadoE("a"); 
                    } catch (IllegalAccessException ex) {
                        throw new PersonalizadoE("a"); 
                    }
                    return valores;
                }).reduce("{", (a, b) -> {
            if ("{".equals(a)) {
                return a + b;
            }
            return a + "," + b;
        }).concat("}");
    }
;
}
