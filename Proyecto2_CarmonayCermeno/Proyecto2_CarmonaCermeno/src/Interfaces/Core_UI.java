/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Sebastian Cermeno
 */
public class Core_UI extends JFrame {
    SelectorView viewSelector;
    JPanel viewContainer = new JPanel();
    GridBagConstraints globalConstraints = new GridBagConstraints();
    final Color MAIN_BLUE = new Color(42, 12, 78);
    final Color MAIN_PURPLE = new Color(81,53,90);
    final Color MAIN_PINK = new Color(255, 130, 169);
    final Color MAIN_TEAL = new Color(80,137,145);
    final Color WHITE = new Color(255, 255, 255);
    public Core_UI() {
        viewSelector = new SelectorView();
        viewContainer.setLayout(new CardLayout());
        
        JPanel fakePanel1 = new JPanel();
        fakePanel1.setBackground(WHITE);
        fakePanel1.add(new JLabel("VistaGeneral"));
        
        JPanel fakePanel2 = new JPanel();
        fakePanel2.setBackground(WHITE);
        fakePanel2.add(new JLabel("VistaTablas"));
        
        JPanel fakePanel3 = new JPanel();
        fakePanel3.setBackground(WHITE);
        fakePanel3.add(new JLabel("VistaBloques"));
        
        viewContainer.add("Principal", fakePanel1);
        viewContainer.add("Tablas", fakePanel2);
        viewContainer.add("Bloques", fakePanel3);
        
        this.setBounds(0, 0, 800, 800);
        this.setLayout(new GridBagLayout());
        
        globalConstraints.fill = GridBagConstraints.BOTH;
        globalConstraints.weightx = 1;
        globalConstraints.weighty = 1;
        
        globalConstraints.gridx = 0;
        globalConstraints.gridy = 0;
        globalConstraints.gridwidth = 1;
        globalConstraints.gridheight = 4;
        viewContainer.setBackground(Color.BLUE);
        this.add(viewContainer, globalConstraints);
        
        globalConstraints.weighty = 0.1;
        globalConstraints.gridx = 0;
        globalConstraints.gridy = 5;
        globalConstraints.gridheight = 1;
        this.add(viewSelector, globalConstraints);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewSelector.swapSelection(0);
        this.setVisible(true);
    }
    
    private class SelectorView extends JPanel {
        GridBagLayout viewLayout = new GridBagLayout();
        SelectorButton toMainMenu = new SelectorButton("Menú Principal", 0);
        SelectorButton toTableView = new SelectorButton("Vista de Tablas", 1);
        SelectorButton toBlockView = new SelectorButton("Vista de Bloques", 2);
        public SelectorView(){
            this.setLayout(viewLayout);
            
            GridBagConstraints localConstraints = new GridBagConstraints();
            localConstraints.fill = GridBagConstraints.BOTH;
            localConstraints.weightx = 1;
            localConstraints.weighty = 1;
            
            toMainMenu.setBackground(MAIN_BLUE);
            localConstraints.gridx = 0;
            this.add(toMainMenu, localConstraints);
            
            toTableView.setBackground(MAIN_BLUE);
            localConstraints.gridx = 1;
            this.add(toTableView, localConstraints);
            
            toBlockView.setBackground(MAIN_BLUE);
            localConstraints.gridx = 2;
            this.add(toBlockView, localConstraints);
        }
        public void swapSelection(int indexToLock) {
            for (int i = 0; i < this.getComponentCount(); i++) {
                SelectorButton object = (SelectorButton)(this.getComponent(i));
                if (i == indexToLock) {
                    object.lock();
                }
                else {
                    object.unlock();
                }
            }
            CardLayout cl = (CardLayout)(viewContainer.getLayout());
            switch (indexToLock) {
                case 0:
                    cl.show(viewContainer, "Principal");
                    break;
                case 1:
                    cl.show(viewContainer, "Tablas");
                    break;
                case 2:
                    cl.show(viewContainer, "Bloques");
                    break;
                default:
                    break;
            }
        }
        private class SelectorButton extends JPanel {
            GridBagLayout buttonLayout = new GridBagLayout();
            JButton mainButton = new JButton();
            JPanel filler = new JPanel();
            final Color TRANSPARENT = new Color(0,0,0,0);
            private int selfIndex;
            public SelectorButton(String title, int index) {
                this.selfIndex = index;
                this.setLayout(buttonLayout);
                GridBagConstraints internalConstraints = new GridBagConstraints();
                internalConstraints.anchor = GridBagConstraints.PAGE_END;
                internalConstraints.fill = GridBagConstraints.BOTH;
                internalConstraints.weightx = 1;
                internalConstraints.weighty = 0.5;
                internalConstraints.gridx = 0;
                
                filler.setBackground(TRANSPARENT);
                internalConstraints.gridy = 0;
                this.add(filler, internalConstraints);
                
                mainButton.setText(title);
                mainButton.setForeground(WHITE);
                mainButton.setBackground(MAIN_PURPLE);
                mainButton.setFont(new Font("Calibri", Font.BOLD, 17));
                mainButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        swapSelection(selfIndex);
                    }
                });
                /*mainButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseClicked(MouseEvent e) {}
                 });
                */
                
                internalConstraints.gridy = 1;
                this.add(mainButton, internalConstraints);
            }
            public void lock() {
                this.filler.setVisible(false);
                this.mainButton.setEnabled(false);
                this.mainButton.setBackground(MAIN_TEAL);
            }
            public void unlock() {
                 this.filler.setVisible(true);
                 this.mainButton.setEnabled(true);
                 this.mainButton.setBackground(MAIN_PURPLE);
                 this.mainButton.setForeground(WHITE);
            }
        }
    }
}
