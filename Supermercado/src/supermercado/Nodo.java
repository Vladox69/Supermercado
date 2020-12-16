/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

/**
 *
 * @author ASUS
 */
public class Nodo {
    Object dato;
    Nodo siguiente;
    
    Nodo(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
