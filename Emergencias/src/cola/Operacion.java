/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cola;

/**
 *
 * @author Karol
 */
public class Operacion  {
    
   
     public static <T extends Base> Cola<T> EmerDuplicada(Cola<T> EmerOriginal){
        Cola<T> EmerDuplicada=new Cola<>();
        Cola<T> EmerAuxiliar=new Cola<>();
       
        while (!EmerOriginal.estaVacia()) {            
         T elemento = EmerOriginal.desapilar(); 
          EmerAuxiliar.aEmer(elemento);
        }

        while (!EmerAuxiliar.estaVacia()) {    
            
            T elemento=EmerAuxiliar.desapilar();
            T elementoCopiado= (T) elemento.copy();

            EmerDuplicada.aPilar(elemento);
            
            EmerOriginal.aPilar(elementoCopiado);
        }
        
        
        return EmerDuplicada;
    }
        public static <T extends Base> int contarElementos(Cola<T> pilaOriginal){
            int cantidadElementos=0;
            Cola<T> EmerDuplicada=EmerDuplicada(pilaOriginal);
            
            while (!EmerDuplicada.estaVacia()) {                
                cantidadElementos++;
                EmerDuplicada.desapilar();
            }
                       
            return cantidadElementos;
        } 
}
