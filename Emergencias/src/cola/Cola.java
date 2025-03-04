/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cola;

import data.Emergencia;
import java.util.LinkedList;

/**
 *
 * @author David Castro
 */
public class Cola<T extends Base> {

    private LinkedList<T> cola;

    public Cola() {
        cola = new LinkedList<>();
    }

    public void encolar(T elemento) {
        cola.addLast(elemento);
    }

    public T desencolar() {
        return cola.removeFirst();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (T e : cola) {
            sb.append(" ").append(e.toString());
        }
        sb.append("]");

        return sb.toString();
    }

    <T extends Object & Base> T desapilar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    <T extends Object & Base> void aEmer(T elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    <T extends Object & Base> void aPilar(T elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void agregar(Emergencia e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
  
}
