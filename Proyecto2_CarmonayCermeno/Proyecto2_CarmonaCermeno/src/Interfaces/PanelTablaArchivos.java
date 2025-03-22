/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import FuncionesProyecto.FileSystemState;
import java.awt.*;
import Primitivas.Nodo;
/**
 *
 * @author valery
 */
public class PanelTablaArchivos extends JPanel {
    
    private JTable tablaArchivos;
    private DefaultTableModel modeloTabla;
    private FileSystemState sistema;
    
    public PanelTablaArchivos(FileSystemState sistema) {
         this.sistema = sistema;
         
         
         // Configurar el modelo de la tabla con tres columnas
        String[] columnas = { "Nombre", "Bloque Inicial", "Tamaño" };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaArchivos = new JTable(modeloTabla);
         
        
        // Agregar la tabla dentro de un JScrollPane para permitir desplazamiento
        setLayout(new BorderLayout());
        add(new JScrollPane(tablaArchivos), BorderLayout.CENTER);
    }
    
    public void actualizarTabla() {
        // Limpiar tabla antes de actualizar
        modeloTabla.setRowCount(0);

        // Recorrer la lista de archivos en FileSystemState y agregarlos a la tabla
        Nodo temp = sistema.getArchivos().getCabeza();
        while (temp != null) {
            FileSystemState.ArchivoInfo archivo = (FileSystemState.ArchivoInfo) temp.getDato();
            modeloTabla.addRow(new Object[]{ archivo.getNombre(), archivo.getPrimerBloque(), archivo.getTamanio() });
            temp = temp.getSiguiente();
        }
    }

 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Bloque Inicial", "Tamaño"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
