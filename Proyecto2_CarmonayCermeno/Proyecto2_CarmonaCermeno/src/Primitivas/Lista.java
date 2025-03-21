/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Primitivas;

/**
 *
 * @author valery
 */
public class Lista {
    private Nodo cabeza;

    public Lista(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
    

    // Constructor 
    public Lista() {
        this.cabeza = null;
    }


    public void crear_lista() {
        cabeza = null;
        System.out.println("Lista creada correctamente.");
    }
    
      public void agregar_nodo(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
        System.out.println("Nodo con valor " + dato + " agregado.");
    }


    public void eliminar_nodo_porindice(int indice) {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        if (indice == 0) {
            cabeza = cabeza.siguiente;
            System.out.println("Nodo en el índice " + indice + " eliminado.");
            return;
        }

        Nodo temp = cabeza;
        Nodo previo = null;
        int contador = 0;

        while (temp != null && contador < indice) {
            previo = temp;
            temp = temp.siguiente;
            contador++;
        }

        if (temp == null) {
            System.out.println("Índice fuera de rango.");
            return;
        }

        previo.siguiente = temp.siguiente;
        System.out.println("Nodo en el índice " + indice + " eliminado.");
    }

 
    public void mostrar_lista() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo temp = cabeza;
        System.out.print("Lista: ");
        while (temp != null) {
            System.out.print(temp.dato + " -> ");
            temp = temp.siguiente;
        }
        System.out.println("NULL");
    }

}
