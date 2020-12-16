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

public class Factura implements Comparable<Factura> {

    Lista productosFac;
    String fecFac;
    String codFac;
    Cliente cliente;
    Dependiente dependiente;
    static int numFac=0;
    double subTotFac;
    double IVATotFac;
    double totFac;

    Factura(Dependiente dependiente,Cliente cliente, String fecVen) {
        this.cliente = cliente;
        this.dependiente=dependiente;
        this.fecFac = fecVen;
        Factura.numFac++;
        this.codFac="FT"+Factura.numFac;
        productosFac = new Lista();
    }

    public boolean añadirProducto(String codArt, int cantArt) {
        Productos producto = (Productos) Inventario.productos.buscar(codArt);
        if ((producto != null && producto.cantidad != 0) && producto.cantidad >= cantArt) {
            Inventario.quitarProductosInventario(codArt, cantArt);
            productosFac.insertar(new Productos(producto.articulo, cantArt));
            return true;
        }
        return false;
    }

    public void precioSubFactura() {
        Nodo aux = productosFac.primero;
        double acumulador = 0;
        while (aux != null) {
            acumulador = acumulador + (((Productos) (aux.dato)).articulo.precArt * ((Productos) (aux.dato)).cantidad);
            aux = aux.siguiente;
        }
        this.subTotFac = acumulador;
    }

    public void precioIVA() {
        Nodo aux = productosFac.primero;
        double acumulador = 0;
        while (aux != null) {
            acumulador = acumulador + (((Productos) (aux.dato)).articulo.IVA * ((Productos) (aux.dato)).cantidad);
            aux = aux.siguiente;
        }
        this.IVATotFac = acumulador;
    }

    public void totalFactura() {
        precioSubFactura();
        precioIVA();
        this.totFac = this.IVATotFac + this.subTotFac;
    }
    
    @Override
    public boolean equals(Object codFac) {
        return (this.codFac.equals(codFac));
    }
    
    @Override
    public String toString() {
        System.out.println("Productos");
        productosFac.imprimir();
        return this.codFac+" Fecha :" + this.fecFac + "      Cliente :" + this.cliente.apePer+" "+this.cliente.nomPer +"    "+this.cliente.cedPer+ "    Dependiente:" +this.dependiente.codDep +"     "+this.dependiente.apePer +"     "+this.dependiente.nomPer +"      Subtotal :" + this.subTotFac
                + "      IVA total: " + this.IVATotFac + "      Total :" + this.totFac;
    }

 
    @Override
    public int compareTo(Factura factura) {
    if (this.totFac < factura.totFac) {
            return -1;
        }
        if (this.totFac > factura.totFac) {
            return 1;
        }
        return 0;    
    }
}
