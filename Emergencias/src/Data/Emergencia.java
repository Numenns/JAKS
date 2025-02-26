/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Cola.Base;

/**
 *
 * @author Pc
 */
public class Emergencia implements Base{
    
    private String tEmergencia;
    
    private int prioridad;
    
    private int tiempo;
    
    private int id;

    public Emergencia() {
    }

    public Emergencia(String tEmergencia, int prioridad, int tiempo, int id) {
        this.tEmergencia = tEmergencia;
        this.prioridad = prioridad;
        this.tiempo = tiempo;
        this.id = id;
    }
    
    

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Get the value of tiempo
     *
     * @return the value of tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * Set the value of tiempo
     *
     * @param tiempo new value of tiempo
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }


    /**
     * Get the value of prioridad
     *
     * @return the value of prioridad
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Set the value of prioridad
     *
     * @param prioridad new value of prioridad
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }


    /**
     * Get the value of tEmergencia
     *
     * @return the value of tEmergencia
     */
    public String gettEmergencia() {
        return tEmergencia;
    }

    /**
     * Set the value of tEmergencia
     *
     * @param tEmergencia new value of tEmergencia
     */
    public void settEmergencia(String tEmergencia) {
        this.tEmergencia = tEmergencia;
    }

    @Override
    public Base copy() {
        return new Emergencia(tEmergencia, prioridad, tiempo, id);
    }
    
    @Override
    public String toString() {
        return "Emergencia{" + "" + tEmergencia + "" + prioridad + "" + tiempo + "%05d" + id + '}';
    }
    
}
