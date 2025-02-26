/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import Data.Emergencia;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Pc
 */

public class CrearEmergencia {
   
   private int id = 0; 

   public Emergencia generarEmergencia(String tEmergencia, int prioridad) {
       int tiempoR = ThreadLocalRandom.current().nextInt(5, 11); 
       id++;
       return new Emergencia(tEmergencia, prioridad, tiempoR, id); 
   }
}
