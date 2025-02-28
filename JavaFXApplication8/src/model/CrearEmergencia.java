/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package model;

import data.Emergencia;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import cola.Cola;

/**
 *
 
@author Pc*/
public class CrearEmergencia implements Runnable {

    private final Cola<Emergencia> cola;
    private Random rand = new Random();
    private String[] tipos = {"Accidente", "Incendio", "Infarto"};
    private int[] prioridades = {1, 5, 10}; // Solo 1, 5 y 10
    private static int contador = 1;  // Inicia desde 1

    public CrearEmergencia(Cola<Emergencia> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while (true) {
            int prioridad = prioridades[rand.nextInt(prioridades.length)]; // Selección aleatoria entre 1, 5 y 10
            String tipo = tipos[rand.nextInt(tipos.length)];
            int tiempoAtencion = rand.nextInt(6) + 5; // Tiempo entre 5 y 10 segundos
            int id = generarIdUnico();  // Generar ID solo una vez

            Emergencia e = new Emergencia(tipo, prioridad, tiempoAtencion, id);  // Usa ese mismo ID
            cola.encolar(e);

            System.out.printf("Nueva emergencia: %s con prioridad %d un tiempo de: %d ID: %05d\n",
                    tipo, prioridad, tiempoAtencion, id);  // Mostrar el mismo ID

            try {
                Thread.sleep(1000); // Esperar 1 segundo antes de generar otra emergencia
            } catch (InterruptedException ignored) {
            }
        }
    }

    private int generarIdUnico() {
        return contador++;  // Devuelve el número actual y lo incrementa
    }

}