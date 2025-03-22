/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Componentes;

/**
 *
 * @author Sebastian Cermeno
 */
public interface Emitter {
    void addObserver(Observer observer);
    void removeObserver (Observer observer);
    void notifyGroup();
}
