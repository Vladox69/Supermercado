/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supermercado;

import complementos.validacion;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class GestorSupermercado {

    Supermercado supermercado;
    private Dependiente auxDep;
    private Cliente auxCli;
    private Factura auxFac;
    private Productos auxProd;
    Scanner entrada = new Scanner(System.in);

    GestorSupermercado() {
        supermercado = new Supermercado();
    }
                            //*************MÉTODOS DEL GESTOR*****************//
    //menú del sistema 
    public void ingresoGestor() {
        int opcion;
        do {
            System.out.println("SISTEMA DE HELDER BARRERA");
            System.out.println("1.-INGRESAR AL SISTEMA");
            System.out.println("2.-SALIR");
            System.out.println("Elije una opción: ");
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    ingresoSistema();
                    break;
                default:
                    System.out.println("*******GRACIAS POR USASR EL SISTEMA********");
            }
        } while (opcion != 2);
    }
    
    //M ingresar al sistema
    public void ingresoSistema() {
        System.out.println("Usuario: ");
        String usuario = entrada.next();
        System.out.println("Contraseña: ");
        String contra = entrada.next();
        if (supermercado.administrador.getAdmUsuario().equals(usuario)
                && supermercado.administrador.getAdmContrasegna().equals(contra)) {
            System.out.println("BIENVENIDO ADMIN");
            opcionesAdmin();
        } else {
            auxDep = (Dependiente) supermercado.buscarDependiente(usuario);
            if (auxDep != null && auxDep.contraDep.equals(contra)) {
                System.out.println("Usuario " + auxDep.nomPer + " " + auxDep.apePer);
                System.out.println("Estas logueado");
                opcionesDependiente();
            } else {
                System.out.println("Usuario inexistente o contraseña incorrecta");
            }
        }
    }
    
                            //*******************CLIENTE*******************//
    
    //Menú del cliente
    public void mestadFacClientes() {
        System.out.println("FACTURAS POR CLIENTE");
        System.out.println("1.-PROMEDIO CONSUMO");
        System.out.println("2.-FACTURA DE MAYOR CONSUMO");
        System.out.println("3.-TOTAL DE CONSUMO");
        System.out.println("4.-SALIR");
        System.out.println("Escoge una opción: ");
    }
    
    //M registrar cliente
    public void ingresarCliente() {
        String cedula;
        String nombre;
        String apellido;
        char sexo;
        int edad;
        boolean aux = true;
        do {
            System.out.println("Cédula: ");
            cedula = entrada.next();
            if (supermercado.clientes.buscar(cedula) != null) {
                System.out.println("Existe un cliente registrado con este dato");
                System.out.println("Introduce un dato valido");
            } else {
                aux = false;
            }
        } while (aux);

        do {
            System.out.println("Nombre: ");
            nombre = entrada.next();
        } while (validacion.validarTexto(nombre));

        do {
            System.out.println("Apellido: ");
            apellido = entrada.next();
        } while (validacion.validarTexto(apellido));

        do {
            System.out.println("Edad: ");
            edad = entrada.nextInt();
        } while (validacion.validarEdad(edad));

        do {
            System.out.println("Sexo: ");
            sexo = entrada.next().toUpperCase().charAt(0);
        } while (validacion.validarGenero(sexo));

        System.out.println("Teléfono: ");
        String telefono = entrada.next();

        System.out.println("Dirección: ");
        String direccion = entrada.next();

        supermercado.ingresarCliente(new Cliente(cedula, nombre, apellido, edad, sexo, telefono, direccion));
        System.out.println("Se agrego un nuevo cliente");
    }
    

    //Método de las estadísticas del cliente
    public void estadFactClientes() {
        int opcion;
        do {
            mestadFacClientes();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    promCliente();
                    break;
                case 2:
                    mayorFactCli();
                    break;
                case 3:
                    totalConsumoCli();
                    break;
            }
        } while (opcion != 4);
    }

    //Promedio facturado del cliente
    public void promCliente() {
        System.out.println("PROMEDIO DE CONSUMO");
        System.out.println("Cédula: ");
        String cedula = entrada.next();
        double promFact = 0;
        auxCli = supermercado.buscarCliente(cedula);
        if (auxCli != null) {
            int cantFact = auxCli.facturas.contar();
            if (cantFact != 0) {
                auxFac = (Factura) auxCli.facturas.primero.dato;
                Nodo aux = auxCli.facturas.primero;
                while (aux != null) {
                    promFact = promFact + auxFac.totFac;
                    aux = aux.siguiente;
                    if (aux != null) {
                        auxFac = (Factura) aux.dato;
                    }
                }
                auxCli.toString();
                System.out.println("Promedio de consumo: " + promFact / cantFact);
            } else {
                System.out.println("**********No tienes consumo aún**************");
            }
        } else {
            System.out.println("Cédula erronea o cliente inexistente");
        }
    }

    //mayor factura del cliente
    public void mayorFactCli() {
        System.out.println("FACTURA MAYOR CONSUMO");
        System.out.println("Cédula: ");
        String cedula = entrada.next();
        auxCli = supermercado.buscarCliente(cedula);
        if (auxCli != null) {
            Nodo aux = auxCli.facturas.primero;
            int numFacturas = auxCli.facturas.contar();
            int iterador = 0;
            Factura[] factOrdenadas = new Factura[numFacturas];
            while (aux != null) {
                factOrdenadas[iterador] = (Factura) (aux.dato);
                iterador++;
                aux = aux.siguiente;
            }
            if (factOrdenadas.length != 0) {
                Arrays.sort(factOrdenadas, Comparator.reverseOrder());
                auxCli.toString();
                System.out.println("****************Factura de mayor consumo***************");
                System.out.println(factOrdenadas[0]);

            } else {
                System.out.println("***************No tienes consumo aún*****************");
            }
        } else {
            System.out.println("Cédula erronea o cliente inexistente");
        }
    }

    //total de consumo del cliente
    public void totalConsumoCli() {
        System.out.println("TOTAL CONSUMO");
        System.out.println("Cédula: ");
        String cedula = entrada.next();
        auxCli = supermercado.buscarCliente(cedula);
        if (auxCli != null) {
            double total = 0;
            auxFac = (Factura) auxCli.facturas.primero.dato;
            Nodo aux = auxCli.facturas.primero;
            while (aux != null) {
                total = total + auxFac.totFac;
                aux = aux.siguiente;
                if (aux != null) {
                    auxFac = (Factura) aux.dato;
                }
            }
            if (total != 0) {
                auxCli.toString();
                System.out.println("Total de consumo: " + total);
            } else {
                System.out.println("***************No tienes consumo aún***************");
                auxCli.toString();
            }

        } else {
            System.out.println("Cédula erronea o cliente inexistente");
        }

    }

                            //******************INVENTARIO******************//
    
    //registrar productos en el inventario
    public void ingresarInventario() {
        String nomArticulo;
        String codArticulo;
        double precArticulo;
        int cantArticulo;
        boolean aux = true;
        do {
            System.out.println("Nombre: ");
            nomArticulo = entrada.next();
        } while (validacion.validarTexto(nomArticulo));

        do {
            System.out.println("Código artículo: ");
            codArticulo = entrada.next();
            if (Inventario.productos.buscar(codArticulo) != null) {
                System.out.println("Ya este un producto con este código");
                System.out.println("Ingresa un código valido");
            } else {
                aux = false;
            }
        } while (aux);

        do {
            System.out.println("Precio: ");
            precArticulo = entrada.nextDouble();
        } while (validacion.validarDouble(precArticulo));

        do {
            System.out.println("Cantidad: ");
            cantArticulo = entrada.nextInt();
        } while (validacion.validarEntero(cantArticulo));

        supermercado.ingresarInventario(new Productos(new Articulo(codArticulo, nomArticulo, precArticulo), cantArticulo));
    }
    
    //Método de revisar inventario
    public void imprimirInventario() {
        supermercado.inventario.listaProductos();
    }
    
    //****************FACTURAS*******************//
    
    //método factura mayor monto supermercado
    public void montoMayorFac() {
        if (supermercado.facturas.primero != null) {
            Nodo aux = supermercado.facturas.primero;
            int numFacturas = supermercado.facturas.contar();
            int iterador = 0;
            Factura[] factOrdenadas = new Factura[numFacturas];
            while (aux != null) {
                factOrdenadas[iterador] = (Factura) (aux.dato);
                iterador++;
                aux = aux.siguiente;
            }
            Arrays.sort(factOrdenadas, Comparator.reverseOrder());
            System.out.println("Mayor monto facturado");
            System.out.println(factOrdenadas[0]);
        } else {
            System.out.println("No se a vendido nada");
        }
    }

    //método listado facturas ordenadas
    public void facturasOrdenadas() {
        Nodo aux = supermercado.facturas.primero;
        int numFacturas = supermercado.facturas.contar();
        int iterador = 0;
        Factura[] factOrdenadas = new Factura[numFacturas];

        while (aux != null) {
            factOrdenadas[iterador] = (Factura) (aux.dato);
            iterador++;
            aux = aux.siguiente;
        }
        if (factOrdenadas.length != 0) {
            Arrays.sort(factOrdenadas, Comparator.reverseOrder());
            for (int i = 0; i < numFacturas; i++) {
                System.out.println(factOrdenadas[i]);
            }
        } else {
            System.out.println("************No tenemos ventas aún**************");
        }

    }

    //método total de ventas
    public void totalVentas() {
        System.out.println("TOTAL VENTA");
        if (supermercado.facturas.primero != null) {
            double total = 0;
            auxFac = (Factura) supermercado.facturas.primero.dato;
            Nodo aux = supermercado.facturas.primero;
            while (aux != null) {
                total = total + auxFac.totFac;
                aux = aux.siguiente;
                if (aux != null) {
                    auxFac = (Factura) aux.dato;
                }
            }
            if (total != 0) {
                System.out.println("Total de vendido: " + total);
            } else {
                System.out.println("***************No tenemos ventas aún************");
            }
        } else {
            System.out.println("No se a vendido nada");
        }

    }

                            //****************DEPENDIENTES******************//
    
    //menu dependientes
    public void menuDependiente() {
        System.out.println("SISTEMA DE HELDER BARRERA");
        System.out.println("1.-REALIZAR VENTA");
        System.out.println("2.-REVISAR INVENTARIO");
        System.out.println("3.-ANULAR FACTURA");
        System.out.println("4.-SALIR");
        System.out.println("Elije una opción: ");
    }

    //opciones funcionales dependiente
    public void opcionesDependiente() {
        int opcion;
        do {
            menuDependiente();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    registrarVenta();
                    break;
                case 2:
                    imprimirInventario();
                    break;
                case 3:
                    System.out.println("ANULAR FACTURA");
                    quitarFactura();
                    break;
            }
        } while (opcion != 4);
    }
    
    //M registrar vendedor
    public void ingresarDependientes() {
        String cedula;
        String nombre;
        String apellido;
        int edad;
        char sexo;
        String codDependiente;
        boolean aux = true;

        do {
            System.out.println("Código de dependiente: ");
            codDependiente = entrada.next();
            if (supermercado.dependientes.buscar(codDependiente) != null) {
                System.out.println("El código de este dependiente ya está en uso");
                System.out.println("Poner un código valido");
            } else {
                aux = false;
            }
        } while (aux);

        System.out.println("Cédula: ");
        cedula = entrada.next();

        do {
            System.out.println("Nombre: ");
            nombre = entrada.next();
        } while (validacion.validarTexto(nombre));

        do {
            System.out.println("Apellido: ");
            apellido = entrada.next();
        } while (validacion.validarTexto(apellido));

        do {
            System.out.println("Edad: ");
            edad = entrada.nextInt();
        } while (validacion.validarEdadDependiente(edad));

        do {
            System.out.println("Sexo: ");
            sexo = entrada.next().toUpperCase().charAt(0);
        } while (validacion.validarGenero(sexo));

        System.out.println("Contraseña de dependiente: ");
        String contDependiente = entrada.next();
        supermercado.ingresarDependiente(new Dependiente(cedula, nombre, apellido, edad, sexo,
                contDependiente, codDependiente));
        System.out.println("Se agregó un nuevo dependiente");
    }

    //método para imprimir la mayor venta de un dependiente
    public void mfactDependientes() {
        System.out.println("FACTURAS POR DEPENDITE");
        System.out.println("1.-PROMEDIO VENTAS");
        System.out.println("2.-MAYOR MONTO FACTURADO");
        System.out.println("3.-TOTAL VENTA");
        System.out.println("4.-SALIR");
        System.out.println("Escoge una opción: ");
    }
    
    //todos para las estadisticas del dependinte
    public void estadFactDependientes() {
        int opcion;
        do {
            mfactDependientes();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    promDependiente();
                    break;
                case 2:
                    mayorFactDep();
                    break;
                case 3:
                    totalVentaDep();
                    break;
            }
        } while (opcion != 4);
    }

    //método para el calculo del promedio de ventas de un dependiente
    public void promDependiente() {
        System.out.println("PROMEDIO DE VENTA");
        System.out.println("Código dependiente: ");
        String codigo = entrada.next();
        double promFact = 0;
        auxDep = supermercado.buscarDependiente(codigo);
        if (auxDep != null) {
            int cantFact = auxDep.ventas.contar();
            if (cantFact > 0) {
                auxFac = (Factura) auxDep.ventas.primero.dato;
                Nodo aux = auxDep.ventas.primero;
                while (aux != null) {
                    promFact = promFact + auxFac.totFac;
                    aux = aux.siguiente;
                    if (aux != null) {
                        auxFac = (Factura) aux.dato;
                    }
                }
                auxDep.toString();
                System.out.println("Promedio de consumo: " + promFact / cantFact);
            } else {
                System.out.println("*********No tienes ventas aún************");
            }
        } else {
            System.out.println("Código o dependiente inexistente");
        }
    }

    //método para sacar la mayor factura del dependiente
    public void mayorFactDep() {
        System.out.println(" MAYOR MONTO FACTURADO");
        System.out.println("Código dependiente: ");
        String codigo = entrada.next();
        auxDep = supermercado.buscarDependiente(codigo);
        if (auxDep != null) {
            Nodo aux = auxDep.ventas.primero;
            int numFacturas = auxDep.ventas.contar();
            if (numFacturas > 0) {
                int iterador = 0;
                Factura[] factOrdenadas = new Factura[numFacturas];
                while (aux != null) {
                    factOrdenadas[iterador] = (Factura) (aux.dato);
                    iterador++;
                    aux = aux.siguiente;
                }
                Arrays.sort(factOrdenadas, Comparator.reverseOrder());
                auxDep.toString();
                System.out.println("Mayor monto facturado");
                System.out.println(factOrdenadas[0]);
            } else {
                System.out.println("*******************No tienes ventas aún*****************");
            }
        } else {
            System.out.println("Código erróneo o dependiente inexistente");
        }
    }

    //método para calcular el total de ventas de un dependiente    
    public void totalVentaDep() {
        System.out.println("TOTAL VENTA");
        System.out.println("Código dependiente: ");
        String codigo = entrada.next();
        auxDep = supermercado.buscarDependiente(codigo);
        if (auxDep != null) {
            double total = 0;
            if (auxDep.ventas.primero != null) {
                auxFac = (Factura) auxDep.ventas.primero.dato;
                Nodo aux = auxDep.ventas.primero;
                while (aux != null) {
                    total = total + auxFac.totFac;
                    aux = aux.siguiente;
                    if (aux != null) {
                        auxFac = (Factura) aux.dato;
                    }
                }
            }
            if (total != 0) {
                auxDep.toString();
                System.out.println("Total de vendido: " + total);
            } else {
                System.out.println("************No tienes ventas aún************");
            }

        } else {
            System.out.println("Código erroneo o dependiente inexistente");
        }

    }
    
    //método para registrar venta
    public void registrarVenta() {
        System.out.println("Cédula cliente: ");
        String cliente = entrada.next();
        auxCli = (Cliente) supermercado.clientes.buscar(cliente);
        System.out.println("Fecha: ");
        String fecha = entrada.next();
        if (auxCli != null) {
            Factura factura = new Factura(auxDep, auxCli, fecha);
            auxDep.registrarFactura(factura);
            auxCli.guardarFactura(factura);
            supermercado.ingresarFactura(factura);
        } else {
            System.out.println("************No existes en nuestro sistema*******************");
            System.out.println("********************Debes registrarte**********************");
            ingresarCliente();
            auxCli = (Cliente) supermercado.clientes.buscar(cliente);
            Factura factura = new Factura(auxDep, auxCli, fecha);
            auxDep.registrarFactura(factura);
            auxCli.guardarFactura(factura);
            supermercado.ingresarFactura(factura);
        }
    }

    //método para quitar una factura
    public void quitarFactura() {

        if (auxDep.ventas.primero == null) {
            System.out.println("No tienes ventas aún");
        } else {
            System.out.println("************Tus facturas***************");
            auxDep.imprimirVentas();
            System.out.println("Número de factura: ");
            String numFac = entrada.next();
            auxFac = (Factura) auxDep.ventas.buscar(numFac);
            if (auxFac != null) {
                auxDep.quitarFactura(numFac);
                supermercado.facturas.borrar(numFac);
                auxCli = (Cliente) supermercado.clientes.buscar(auxFac.cliente.cedPer);
                auxCli.facturas.borrar(numFac);
                System.out.println("La factura fue eliminada");
            } else {
                System.out.println("Número de factura inexistente");
            }
        }
    }

                                 //ADMIN
    
    public void imprimirMenuAdmin() {
        System.out.println("ADMIN");
        System.out.println("1.-DEPENDIENTE");
        System.out.println("2.-CLIENTE");
        System.out.println("3.-INVENTARIO");
        System.out.println("4.-FACTURAS");
        System.out.println("5.-SALIR");
        System.out.println("Elije una opcion: ");
    }
    
    //opciones funcionales del administrador
    public void opcionesAdmin() {
        int opcion;
        do {
            imprimirMenuAdmin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    opcion1Admin();
                    break;
                case 2:
                    opcion2Admin();
                    break;
                case 3:
                    opcion3Admin();
                    break;
                case 4:
                    opcion4Admin();
                    break;
            }
        } while (opcion != 5);
    }
   
    //Menu opciones dependientes
    public void mopcion1Admin() {
        System.out.println("DEPENDIENTE");
        System.out.println("1.-INGRESAR NUEVO");
        System.out.println("2.-MODIFICAR");
        System.out.println("3.-ELIMINAR");
        System.out.println("4.-LISTAR");
        System.out.println("5.-SALIR");
        System.out.println("Elije una opción: ");
    }
    
    //M opciones funcionales del admin
    public void opcion1Admin() {
        int opcion;
        String codDep;
        do {
            mopcion1Admin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("INGRESAR NUEVO DEPENDIENTE");
                    ingresarDependientes();
                    break;
                case 2:
                    System.out.println("MODIFICAR DEPENDIENTE");
                    System.out.println("Código de dependiente: ");
                    codDep = entrada.next();
                    auxDep = supermercado.buscarDependiente(codDep);
                    if (auxDep != null) {
                        String nombre;
                        String apellido;
                        int edad;
                        char sexo;
                        do {
                            System.out.println("Nombre: ");
                            nombre = entrada.next();
                        } while (validacion.validarTexto(nombre));
                        do {
                            System.out.println("Apellido: ");
                            apellido = entrada.next();
                        } while (validacion.validarTexto(apellido));
                        do {
                            System.out.println("Edad: ");
                            edad = entrada.nextInt();
                        } while (validacion.validarEdad(edad));
                        do {
                            System.out.println("Sexo: ");
                            sexo = entrada.next().toUpperCase().charAt(0);
                        } while (validacion.validarGenero(sexo));
                        System.out.println("Contraseña de dependiente: ");
                        String contDependiente = entrada.next();
                        auxDep.nomPer = nombre;
                        auxDep.apePer = apellido;
                        auxDep.edadPer = edad;
                        auxDep.sexPer = sexo;
                        auxDep.contraDep = contDependiente;
                        System.out.println("Los datos han sido actualizadas");
                    } else {
                        System.out.println("Identificador erroneo o dependiente inexistente");
                    }
                    break;
                case 3:
                    System.out.println("ELMINAR DEPENDIENTE");
                    System.out.println("Código de dependiente: ");
                    codDep = entrada.next();
                    auxDep = supermercado.buscarDependiente(codDep);
                    if (auxDep != null) {
                        supermercado.dependientes.borrar(codDep);
                        System.out.println("Dependiente eliminado");
                    } else {
                        System.out.println("Identificador erroneo o dependiente inexistente");
                    }
                    break;
                case 4:
                    System.out.println("LISTADO DE DEPENDIENTES");
                    supermercado.imprimirDependientes();
                    break;

            }
        } while (opcion != 5);
    }
    
    //menu opciones clientes
    public void mopcion2Admin() {
        System.out.println("CLIENTE");
        System.out.println("1.-MODIFICAR");
        System.out.println("2.-ELIMINAR");
        System.out.println("3.-LISTAR");
        System.out.println("4.-SALIR");
        System.out.println("Elije una opción: ");
    }

    //opciones del cliente
    public void opcion2Admin() {
        int opcion;
        String cedCli;
        do {
            mopcion2Admin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("MODIFICAR CLIENTE");
                    System.out.println("Cédula cliente: ");
                    cedCli = entrada.next();
                    auxCli = supermercado.buscarCliente(cedCli);
                    if (auxCli != null) {
                        String nombre;
                        String apellido;
                        int edad;
                        char sexo;
                        do {
                            System.out.println("Nombre: ");
                            nombre = entrada.next();
                        } while (validacion.validarTexto(nombre));

                        do {
                            System.out.println("Apellido: ");
                            apellido = entrada.next();
                        } while (validacion.validarTexto(apellido));

                        do {
                            System.out.println("Edad: ");
                            edad = entrada.nextInt();
                        } while (validacion.validarEdad(edad));

                        do {
                            System.out.println("Sexo: ");
                            sexo = entrada.next().toUpperCase().charAt(0);
                        } while (validacion.validarGenero(sexo));

                        System.out.println("Teléfono: ");
                        String telefono = entrada.next();
                        System.out.println("Dirección");
                        String direccion = entrada.next();
                        auxCli.nomPer = nombre;
                        auxCli.apePer = apellido;
                        auxCli.edadPer = edad;
                        auxCli.sexPer = sexo;
                        auxCli.telfCli = telefono;
                        auxCli.dirCli = direccion;
                        System.out.println("Los datos han sido actualizadas");
                    } else {
                        System.out.println("Cédula erronea o cliente inexistente");
                    }
                    break;
                case 2:
                    System.out.println("ELMINAR CLIENTE");
                    System.out.println("Cédula cliente: ");
                    cedCli = entrada.next();
                    auxCli = supermercado.buscarCliente(cedCli);
                    if (auxCli != null) {
                        supermercado.clientes.borrar(cedCli);
                        System.out.println("Cliente eliminado");
                    } else {
                        System.out.println("Cédula erronea o Cliente inexistente");
                    }
                    break;
                case 3:
                    System.out.println("LISTADO DE CLIENTES");
                    supermercado.imprimirClientes();
                    break;
            }
        } while (opcion != 4);
    }
    
    //menu opciones Inventario
    public void mopcion3Admin() {
        System.out.println("INVENTARIO");
        System.out.println("1.-INGRESAR NUEVO");
        System.out.println("2.-MODIFICAR");
        System.out.println("3.-ELIMINAR");
        System.out.println("4.-LISTAR");
        System.out.println("5.-SALIR");
        System.out.println("Elije una opción: ");
    }

    // Todos los métodos del inventario
    public void opcion3Admin() {
        int opcion;
        String codProd;
        do {
            mopcion3Admin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("INGRESAR NUEVO PRODUCTO");
                    ingresarInventario();
                    break;
                case 2:
                    System.out.println("MODIFICAR PRODUCTO");
                    System.out.println("Código de producto: ");
                    codProd = entrada.next();
                    auxProd = (Productos) Inventario.productos.buscar(codProd);
                    if (auxProd != null) {
                        String nombre;
                        double precio;
                        int cantidad;
                        do {
                            System.out.println("Nombre: ");
                            nombre = entrada.next();
                        } while (validacion.validarTexto(nombre));

                        do {
                            System.out.println("Precio: ");
                            precio = entrada.nextDouble();
                        } while (validacion.validarDouble(precio));

                        do {
                            System.out.println("Cantidad: ");
                            cantidad = entrada.nextInt();
                        } while (validacion.validarEntero(cantidad));

                        auxProd.articulo.nomArt = nombre;
                        auxProd.articulo.precArt = precio;
                        auxProd.cantidad = cantidad;
                        auxProd.articulo.calculoModificado();
                        System.out.println("Los datos han sido actualizadas");
                    } else {
                        System.out.println("Identificador erroneo o producto inexistente");
                    }
                    break;
                case 3:
                    System.out.println("ELMINAR PRODUCTO");
                    System.out.println("Código de producto: ");
                    codProd = entrada.next();
                    auxProd = (Productos) Inventario.productos.buscar(codProd);
                    if (auxProd != null) {
                        Inventario.productos.borrar(codProd);
                        System.out.println("Producto eliminado");
                    } else {
                        System.out.println("Identificador erroneo o producto inexistente");
                    }
                    break;
                case 4:
                    System.out.println("LISTADO DE PRODUCTOS");
                    Inventario.productos.imprimir();
                    break;

            }
        } while (opcion != 5);
    }
    
    //menu opciones facturado
    public void mopcion4Admin() {
        System.out.println("FACTURAS");
        System.out.println("1.-LISTAR TODAS");
        System.out.println("2.-POR CLIENTES");
        System.out.println("3.-POR DEPENDIENTES");
        System.out.println("4.-FACTURA MAYOR CONSUMO");
        System.out.println("5.-TOTAL FACTURADO");
        System.out.println("6.-SALIR");
        System.out.println("Elije una opción: ");
    }

    //todos los métodos de las facturas en un switch
    public void opcion4Admin() {
        int opcion;
        do {
            mopcion4Admin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("TODAS LAS FACTURAS");
                    facturasOrdenadas();
                    break;
                case 2:
                    estadFactClientes();
                    break;
                case 3:
                    estadFactDependientes();
                    break;
                case 4:
                    montoMayorFac();
                    break;
                case 5:
                    totalVentas();
                    break;
            }
        } while (opcion != 6);
    }

    //imprimir todas las facturas
    public void imprimirFacturas() {
        supermercado.facturas.imprimir();
    }

    //imprimir todos los clientes
    public void imprimirClientes() {
        supermercado.clientes.imprimir();
    }
    
    //imprimir todos los dependientes
    public void imprimirDependientes() {
        supermercado.dependientes.imprimir();
    }

    
    public static void main(String[] args) {
        GestorSupermercado x = new GestorSupermercado();
        x.ingresoGestor();
    }
}
