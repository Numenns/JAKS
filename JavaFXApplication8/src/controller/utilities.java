/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import data.Emergencia;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import cola.Cola;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author josep
 */
public class utilities {
    public static void guardarjson(String ruta, cola.Cola<Emergencia> colaAuxiliar) {
    Gson gson = new Gson();
    Cola<Emergencia> colaOriginal = new Cola<>();

    try (FileReader reader = new FileReader(ruta)) {
        Type tipoCola = new TypeToken<Cola<Emergencia>>(){}.getType();
        colaOriginal = gson.fromJson(reader, tipoCola);
    } catch (IOException e) {
        // 
        e.printStackTrace();
    }

    while (!colaAuxiliar.estaVacia()) {
        colaOriginal.encolar(colaAuxiliar.desencolar());
    }

    try (FileWriter writer = new FileWriter(ruta)) {
        gson.toJson(colaOriginal, writer);
    } catch (IOException e) {
        e.printStackTrace();
    }
          
    }

    public static Cola<Emergencia> leerjson (String ruta){
        Cola<Emergencia> LeerC = new Cola<>();
        Gson gson = new Gson();
        try(FileReader LeerC2 = new FileReader (ruta)) {
            Type nombre = new TypeToken<Cola<Emergencia>>(){}.getType();
            LeerC = gson.fromJson(LeerC2, nombre);
        } catch (Exception e) {
            
        }
    return LeerC;
    
    }
}
}
