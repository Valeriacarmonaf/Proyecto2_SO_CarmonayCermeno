/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FuncionesProyecto;

import Componentes.Emitter;
import Componentes.Observer;
import Primitivas.Lista;
import Primitivas.Nodo;
import Interfaces.PanelTablaArchivos;

//Clase que contiene el estado actual del sistema de archivos.

public class FileSystemState implements Emitter{
    private boolean modoAdministrador;
    private int totalBloques;
    private int bloquesUsados;
    private Lista archivos;
    private PanelTablaArchivos panelTabla;
    
    public final static int DEFAULT = -1;
    
    private final Lista subscribers = new Lista();
    public int pySize = 8;
    public int datSize = 1;
    public int csvSize = 6;
    public int binSize = 2;
    
    @Override
    public void addObserver(Observer observer) {
        subscribers.agregar_nodo(observer);
    }
    @Override 
    public void removeObserver (Observer observer) {
    }
    @Override
    public void notifyGroup() {
        Nodo currentItem = subscribers.getCabeza();
        while (currentItem != null) {
            Observer localObs = (Observer)(currentItem.getDato());
            localObs.onUpdate();
            currentItem = currentItem.getSiguiente();
        }
    }

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
    
    public void agregarArchivo(String nombre, int fileSize, int primerBloque) {
        if (bloquesUsados + fileSize <= totalBloques) {
            if (primerBloque == DEFAULT) {
                archivos.agregar_nodo(new ArchivoInfo(nombre, fileSize, bloquesUsados));
            }
            else {
                archivos.agregar_nodo(new ArchivoInfo(nombre, fileSize, primerBloque));
            }
            bloquesUsados += fileSize;
            if (panelTabla != null) panelTabla.actualizarTabla(); // ACTUALIZAR TABLA
        } else {
            System.out.println("No hay suficiente espacio para agregar el archivo: " + nombre);
        }
        notifyGroup();
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
        notifyGroup();
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
