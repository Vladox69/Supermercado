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
public class Productos {
    Articulo articulo;
    int cantidad;
    
    Productos(Articulo articulo, int cantidad){
        this.articulo=articulo;
        this.cantidad=cantidad;
    }
    
     @Override
    public String toString() {
        return articulo.toString() + "     "+this.cantidad;
    }
    
    
    @Override
    public boolean equals(Object codArt) {
        return (this.articulo.codArt.equals(codArt));
    }
    
    
    
    
}
