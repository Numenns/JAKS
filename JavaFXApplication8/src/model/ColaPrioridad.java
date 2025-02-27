/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.PriorityQueue;
/**
 *
 * @author Karol
 */
class ColaPrioridad<T extends Comparable <T>> {
    
     private PriorityQueue<T> cola = new PriorityQueue<>();

    public void agregar(T item) { cola.add(item); }
    public T procesar() { return cola.poll(); }
    public boolean estaVacia() { return cola.isEmpty();
    }
}
