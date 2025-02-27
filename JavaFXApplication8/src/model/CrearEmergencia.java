/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import data.Emergencia;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import cola.Cola;
/**
 *
 * @author Pc
 */

public class CrearEmergencia implements Runnable{
   
  private final Cola<Emergencia> cola;
    private Random rand = new Random();
    private String[] tipos = { "Accidente", "Incendio", "Infarto" };
    private int[] prioridades = {1, 5, 10}; // Solo 1, 5 y 10
    
    public CrearEmergencia(Cola<Emergencia> cola) {
        this.cola = cola;
    
}
@Override
    public void run() {
        while (true) {
            int prioridad = prioridades[rand.nextInt(prioridades.length)]; // Selección aleatoria entre 1, 5 y 10
            String tipo = tipos[rand.nextInt(tipos.length)];
            int tiempoAtencion = rand.nextInt(6) + 5; // Tiempo entre 5 y 10 segundos
            int id = generarIdUnico();
            
            Emergencia e = new Emergencia(tipo, prioridad, tiempoAtencion, generarIdUnico());
            cola.encolar(e);

            System.out.println("Nueva emergencia: " + tipo + " con prioridad " + prioridad + " un tiempo de: " + tiempoAtencion + " ID: " + id);

            try {
                Thread.sleep(1000); // Esperar 1 segundo antes de generar otra emergencia
            } catch (InterruptedException ignored) {}
        }
    }
    
   private int generarIdUnico() {
        return rand.nextInt(10000); // ID aleatorio entre 0 y 9999
    } 
    
    
}
