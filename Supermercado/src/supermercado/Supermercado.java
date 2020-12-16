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
public class Supermercado {

    Lista dependientes;
    Lista clientes;
    Lista facturas;
    Inventario inventario;
    Administrador administrador;

    Supermercado() {
        dependientes = new Lista();
        clientes = new Lista();
        inventario = new Inventario();
        facturas=new Lista();
        this.administrador = new Administrador("1819", "Helder", "Barrera", 20, 'M');
    }

    public boolean ingresarDependiente(Dependiente dependiente) {
        return dependientes.insertar(dependiente);
    }
    
    public Dependiente buscarDependiente(String codDep){
        return (Dependiente) dependientes.buscar(codDep);
    }
    
    public boolean ingresarCliente(Cliente cliente) {
        return clientes.insertar(cliente);
    }
    
    public Cliente buscarCliente(String cedCli){
        return (Cliente) clientes.buscar(cedCli);
    }
    
    public boolean ingresarInventario(Productos producto) {
        return inventario.registrarProductos(producto);
    }
    
    public boolean ingresarFactura(Factura factura){
        return facturas.insertar(factura);
    }
    
    public void imprimirDependientes() {
        this.dependientes.imprimir();
    }

    public void imprimirClientes() {
        this.clientes.imprimir();
    }

    public void imprimirIventario() {
        this.inventario.listaProductos();
    }

    
    
}
