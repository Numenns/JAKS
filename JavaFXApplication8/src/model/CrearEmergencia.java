/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import data.Emergencia;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;


/**
 *
 * @author Pc
 */

public class CrearEmergencia implements Runnable{
   
  private final ColaPrioridad <Emergencia> cola;
    private Random rand = new Random();
    private String[] tipos = { "Accidente", "Incendio", "Infarto" };

    public CrearEmergenciancias (ColaPrioridad<Emergencia> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while (true) {
            int prioridad = rand.nextInt(10) + 1;
            String tipo = tipos[rand.nextInt(tipos.length)];
            int tiempoAtencion = rand.nextInt(6) + 5;
            Emergencia e = new Emergencia(prioridad, tipo, tiempoAtencion);
            cola.agregar(e);
            System.out.println("Nueva emergencia: " + tipo + " con prioridad " + prioridad);
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
    }
}
