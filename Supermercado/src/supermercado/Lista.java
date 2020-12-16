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
public class Lista {

    Nodo primero;

    Lista() {
        this.primero = null;
    }

    boolean insertar(Object dato) {
        try {
            if (this.primero == null) {
                this.primero = new Nodo(dato);
                return true;
            }
            Nodo aux = this.primero;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = new Nodo(dato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertarPorPrimero(Object dato) {
        if (this.primero == null) {
            this.primero = new Nodo(dato);
            return true;
        }
        Nodo aux = new Nodo(dato);
        aux.siguiente = this.primero;
        this.primero = aux;
        return false;
    }

    public boolean insertarPos(Object dato, int pos) {
        if (pos == 1) {
            return insertarPorPrimero(dato);
        }
        int posAct = 1;
        Nodo aux = this.primero;
        while (posAct != pos - 1 && aux.siguiente != null) {
            aux = aux.siguiente;
            posAct++;
        }

        if (posAct == pos - 1) {
            Nodo nuevo = new Nodo(dato);
            nuevo.siguiente = aux.siguiente;
            aux.siguiente = nuevo;
            return true;
        }
        return false;
    }

    boolean borrar(int pos) {
        int posAct = 1;
        if (pos < 1 || this.primero == null) {
            return false;
        }
        if (pos == 1) {
            this.primero = this.primero.siguiente;
            return true;
        }
        Nodo aux = this.primero;
        while (posAct != pos - 1 && aux.siguiente != null) {
            aux = aux.siguiente;
            posAct += 1;
        }
        if (aux.siguiente != null) {
            aux.siguiente = aux.siguiente.siguiente;
            return true;
        }
        return false;
    }

    public boolean borrar(Object dato) {
        boolean borrado = false;
        while (this.primero != null && this.primero.dato.equals(dato)) {
            this.primero = this.primero.siguiente;
            borrado = true;
        }
        if (this.primero != null) {
            Nodo aux = this.primero;
            while (aux != null) {
                if (aux.siguiente != null && aux.siguiente.dato.equals(dato)) {
                    aux.siguiente = aux.siguiente.siguiente;
                    borrado = true;
                } else {
                    aux = aux.siguiente;
                }
            }
        }
        return borrado;
    }

    public Object buscar(Object dato) {
        Nodo aux = this.primero;
        while (aux != null) {
            if (aux.dato.equals(dato)) {
                return aux.dato;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public Object buscarPos(int pos) {
        Nodo aux = this.primero;
        int posAct = 1;
        while (posAct != pos) {
            if (aux == null) {
                return null;
            }
            aux = aux.siguiente;
            posAct++;
        }
        return (aux == null) ? null : aux.dato;
    }

    public int contar() {
        Nodo aux = this.primero;
        int numeroNodos = 0;
        while (aux != null) {
            numeroNodos++;
            aux = aux.siguiente;
        }
        return numeroNodos;
    }

    public void imprimir() {
        Nodo aux = this.primero;
        while (aux != null) {
            System.out.println(aux.dato.toString());
            aux = aux.siguiente;
        }
    }


}
