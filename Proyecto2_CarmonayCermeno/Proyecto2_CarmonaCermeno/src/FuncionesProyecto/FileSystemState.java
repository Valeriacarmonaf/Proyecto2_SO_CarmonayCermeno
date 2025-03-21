/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FuncionesProyecto;

import Primitivas.Lista;
import Primitivas.Nodo;
import Interfaces.PanelTablaArchivos;

//Clase que contiene el estado actual del sistema de archivos.

public class FileSystemState {
    private boolean modoAdministrador;
    private int totalBloques;
    private int bloquesUsados;
    private Lista archivos;
    private PanelTablaArchivos panelTabla;
    


    public FileSystemState(int totalBloques) {
        this.modoAdministrador = false; // Por defecto inicia en modo usuario
        this.totalBloques = totalBloques;
        this.bloquesUsados = 0;
        this.archivos = new Lista();
    }
    
    public void setPanelTabla(PanelTablaArchivos panelTabla) {
        this.panelTabla = panelTabla;
    }

    
    public void setModoAdministrador(boolean modo) {
        this.modoAdministrador = modo;
    }


    public boolean isModoAdministrador() {
        return modoAdministrador;
    }


    public int getTotalBloques() {
        return totalBloques;
    }

  
    public int getBloquesUsados() {
        return bloquesUsados;
    }


   //AÑADIR ARCHIVO AL SISTEMA Y ACTUALIZAR LA TABLA
    
    public void agregarArchivo(String nombre, int tamanio, int primerBloque) {
        if (bloquesUsados + tamanio <= totalBloques) {
            archivos.agregar_nodo(new ArchivoInfo(nombre, tamanio, primerBloque));
            bloquesUsados += tamanio;
            if (panelTabla != null) panelTabla.actualizarTabla(); // ACTUALIZAR TABLA
        } else {
            System.out.println("No hay suficiente espacio para agregar el archivo: " + nombre);
        }
    }


//ELIMINA UN ARCHIVO Y ACTUALIZA LA TABLA
    
    public void eliminarArchivo(String nombre) {
        Nodo temp = archivos.getCabeza();
        Nodo previo = null;

        while (temp != null) {
            ArchivoInfo archivo = (ArchivoInfo) temp.getDato();
            if (archivo.getNombre().equals(nombre)) {
                bloquesUsados -= archivo.getTamanio();
                if (previo == null) {
                    archivos.setCabeza(temp.getSiguiente());
                } else {
                    previo.setSiguiente(temp.getSiguiente());
                }
                if (panelTabla != null) panelTabla.actualizarTabla(); // ACTUALIZAR TABLA
                return;
            }
            previo = temp;
            temp = temp.getSiguiente();
        }
    }


    public Lista getArchivos() {
        return archivos;
    }


    public static class ArchivoInfo {
        private String nombre;
        private int tamanio;
        private int primerBloque;

        public ArchivoInfo(String nombre, int tamanio, int primerBloque) {
            this.nombre = nombre;
            this.tamanio = tamanio;
            this.primerBloque = primerBloque;
        }

        public String getNombre() {
            return nombre;
        }

        public int getTamanio() {
            return tamanio;
        }

        public int getPrimerBloque() {
            return primerBloque;
        }
        
         @Override
        public String toString() {
            return "Archivo: " + nombre + ", Tamaño: " + tamanio + " bloques, Inicio en bloque: " + primerBloque;
        }
    }
}
