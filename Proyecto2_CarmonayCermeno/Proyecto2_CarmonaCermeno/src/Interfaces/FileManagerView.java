package Interfaces;

import Componentes.CompositeTree;
import Componentes.Observer;
import Componentes.TreeObject;
import FuncionesProyecto.FileSystemState;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sebastian Cermeno
 */
public class FileManagerView extends JPanel{
    GridBagLayout viewLayout = new GridBagLayout();
    private FileSystemState systemState;
    
    final Color MAIN_BLUE = new Color(42, 12, 78);
    final Color MAIN_PURPLE = new Color(81,53,90);
    final Color MAIN_PINK = new Color(255, 130, 169);
    final Color MAIN_TEAL = new Color(80,137,145);
    final Color MAIN_BEIGE = new Color(245, 248, 222);
    final Color WHITE = new Color(255, 255, 255);
    final Color TRANSPARENT = new Color(0,0,0,0);
    
    MainDisplay mainDisplay;
    ModeSwitchDisplay controlDisplay;
    BlockUsageDisplay blockDisplay;
    SettingsDisplay settingsDisplay;
    public FileManagerView(JFrame container, FileSystemState system) {
        this.systemState = system;
        controlDisplay = new ModeSwitchDisplay();
        mainDisplay = new MainDisplay(container);
        blockDisplay = new BlockUsageDisplay();
        settingsDisplay = new SettingsDisplay();
        
        this.setLayout(viewLayout);
        GridBagConstraints localConstraints = new GridBagConstraints();
        GridBagConstraints specificConstraints = new GridBagConstraints();
        
        JPanel leftDisplay = new JPanel();
        JPanel rightDisplay = new JPanel();
        
        leftDisplay.setLayout(new GridBagLayout());
        specificConstraints.fill = GridBagConstraints.BOTH;
        specificConstraints.gridy = 0;
        specificConstraints.weightx = 1;
        specificConstraints.weighty = 0.8333;
        leftDisplay.add(mainDisplay, specificConstraints);
        
        specificConstraints.gridy = 1;
        specificConstraints.weightx = 1;
        specificConstraints.weighty = 0.1667;
        leftDisplay.add(controlDisplay, specificConstraints);
        
        rightDisplay.setLayout(new GridBagLayout());
        specificConstraints.gridy = 0;
        specificConstraints.weightx = 1;
        specificConstraints.weighty = 0.3333;
        rightDisplay.add(blockDisplay, specificConstraints);
        
        specificConstraints.gridy = 1;
        specificConstraints.weightx = 1;
        specificConstraints.weighty = 0.6667;
        rightDisplay.add(settingsDisplay, specificConstraints);
        
        localConstraints.fill = GridBagConstraints.BOTH;
        localConstraints.weighty = 1;
        localConstraints.weightx = 0.75;
        this.add(leftDisplay, localConstraints);
        
        localConstraints.weighty = 1;
        localConstraints.weightx = 0.25;
        this.add(rightDisplay, localConstraints);
    }
    private class MainDisplay extends JPanel {
        JPanel treeHolder = new JPanel();
        GridBagLayout localLayout = new GridBagLayout();
        public MainDisplay(JFrame container) {
            treeHolder.setBackground(WHITE);
            JPanel top = new JPanel();
            top.setBackground(TRANSPARENT);
            JPanel bottom = new JPanel();
            bottom.setBackground(TRANSPARENT);
            JPanel left = new JPanel();
            left.setBackground(TRANSPARENT);
            JPanel right = new JPanel();
            right.setBackground(TRANSPARENT);
            
            this.setBackground(MAIN_BLUE);
            this.setLayout(localLayout);
            GridBagConstraints internalConstraints = new GridBagConstraints();
            internalConstraints.fill = GridBagConstraints.BOTH;
            
            internalConstraints.gridx = 0;
            internalConstraints.gridy = 0;
            internalConstraints.weightx = 1;
            internalConstraints.weighty = 0.1;
            internalConstraints.gridheight = 1;
            internalConstraints.gridwidth = 10;
            this.add(top, internalConstraints);
            
            internalConstraints.gridx = 0;
            internalConstraints.gridy = 1;
            internalConstraints.weightx = 0.05;
            internalConstraints.weighty = 0.8;
            internalConstraints.gridheight = 8;
            internalConstraints.gridwidth = 1;
            this.add(left, internalConstraints);
            
            internalConstraints.gridx = 1;
            internalConstraints.gridy = 1;
            internalConstraints.weightx = 0.9;
            internalConstraints.weighty = 0.8;
            internalConstraints.gridheight = 8;
            internalConstraints.gridwidth = 8;
            this.add(treeHolder, internalConstraints);
            
            internalConstraints.gridx = 9;
            internalConstraints.gridy = 1;
            internalConstraints.weightx = 0.05;
            internalConstraints.weighty = 0.8;
            internalConstraints.gridheight = 8;
            internalConstraints.gridwidth = 1;
            this.add(right, internalConstraints);
            
            internalConstraints.gridx = 0;
            internalConstraints.gridy = 9;
            internalConstraints.weightx = 1;
            internalConstraints.weighty = 0.1;
            internalConstraints.gridheight = 1;
            internalConstraints.gridwidth = 10;
            this.add(bottom, internalConstraints);
            
            treeHolder.setLayout(new BorderLayout());
            treeHolder.add(new CompositeTree(new TreeObject("(Raiz del Sistema)", true, TreeObject.IS_FOLDER), treeHolder, systemState));
        }
    }
    private class ModeSwitchDisplay extends JPanel {
        JButton adminMode = new JButton("Modo Administrador");
        JButton userMode = new JButton("Modo Usuario");
        public ModeSwitchDisplay() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints frameConstraints = new GridBagConstraints();
            GridBagConstraints itemConstraints = new GridBagConstraints();
            this.setBackground(MAIN_BLUE);
            
            adminMode.addActionListener(amL -> {swapLock(0);});
            userMode.addActionListener(umL -> {swapLock(1);});
            
            JPanel filler = new JPanel();
            filler.setBackground(TRANSPARENT);
            JPanel filler2 = new JPanel();
            filler2.setBackground(TRANSPARENT);
            JPanel buttonHolder = new JPanel();
            buttonHolder.setBackground(TRANSPARENT);
            
            frameConstraints.fill = GridBagConstraints.BOTH;
            frameConstraints.gridx = 0;
            frameConstraints.gridy = 0;
            frameConstraints.weighty = 1;
            frameConstraints.weightx = 0.15;
            this.add(filler, frameConstraints);
            
            buttonHolder.setLayout(new GridBagLayout());
            itemConstraints.fill = GridBagConstraints.HORIZONTAL;
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;
            itemConstraints.weighty = 1;
            itemConstraints.ipady = 20;
            itemConstraints.weightx = 0.5;
            buttonHolder.add(adminMode, itemConstraints);
            itemConstraints.gridx = 1;
            itemConstraints.gridy = 0;
            itemConstraints.weighty = 1;
            itemConstraints.weightx = 0.5;
            buttonHolder.add(userMode, itemConstraints);
            
            frameConstraints.gridx = 1;
            frameConstraints.gridy = 0;
            frameConstraints.weighty = 1;
            frameConstraints.weightx = 0.7;
            this.add(buttonHolder, frameConstraints);
            
            frameConstraints.gridx = 2;
            frameConstraints.gridy = 0;
            frameConstraints.weighty = 1;
            frameConstraints.weightx = 0.15;
            this.add(filler2, frameConstraints);
            
            swapLock(0);
        }
        private void swapLock(int index) {
            switch (index){
                case 0:
                    adminMode.setEnabled(false);
                    userMode.setEnabled(true);
                    adminMode.setBackground(MAIN_TEAL);
                    adminMode.setForeground(WHITE);
                    userMode.setBackground(MAIN_BEIGE);
                    userMode.setForeground(MAIN_BLUE);
                    systemState.setModoAdministrador(true);
                    break;
                case 1:
                    adminMode.setEnabled(true);
                    userMode.setEnabled(false);
                    adminMode.setBackground(MAIN_BEIGE);
                    adminMode.setForeground(MAIN_BLUE);
                    userMode.setBackground(MAIN_TEAL);
                    userMode.setForeground(WHITE);
                    systemState.setModoAdministrador(false);
                    break;
                default:
                    break;
            }
        }
    }
    private class BlockUsageDisplay extends JPanel implements Observer{
        JProgressBar progressShowcase = new JProgressBar(JProgressBar.HORIZONTAL);
        JLabel blockUsage = new JLabel("Bloques Utilizados");
        GridBagLayout localLayout = new GridBagLayout();
        public BlockUsageDisplay() {
            systemState.addObserver(this);
            JPanel bottom = new JPanel();
            JPanel right = new JPanel();
            
            this.setLayout(localLayout);
            this.setBackground(MAIN_BLUE);
            progressShowcase.setValue(0);
            progressShowcase.setStringPainted(true);
            GridBagConstraints localConstraints = new GridBagConstraints();
            localConstraints.fill = GridBagConstraints.BOTH;
            
            localConstraints.gridx = 0;
            localConstraints.gridy = 0;
            localConstraints.weightx = 0.9;
            
            localConstraints.weighty = 0.3;
            blockUsage.setFont(new Font("Calibri", Font.BOLD, 28));
            blockUsage.setForeground(WHITE);
            this.add(blockUsage, localConstraints);
            
            localConstraints.gridy = 2;
            localConstraints.weighty = 0.5;
            this.add(progressShowcase, localConstraints);
            
            localConstraints.gridy = 3;
            localConstraints.weighty = 0.1;
            bottom.setBackground(TRANSPARENT);
            this.add(bottom, localConstraints);
            
            localConstraints.gridx = 1;
            localConstraints.gridy = 0;
            localConstraints.weightx = 0.1;
            localConstraints.weighty = 1;
            localConstraints.gridheight = 4;
            right.setBackground(TRANSPARENT);
            this.add(right, localConstraints);
            
        }
        @Override
        public void onUpdate() {
            int allBlocks = systemState.getTotalBloques();
            int usedBlocks = systemState.getBloquesUsados();
            float currentUsage = (float)usedBlocks / (float)allBlocks;
            currentUsage = currentUsage * 100;
            progressShowcase.setValue((int)currentUsage);
        }
    }
    private class SettingsDisplay extends JPanel {
        JLabel title = new JLabel("Datos Administrativos");
        JPanel blockControlGroup = new JPanel();
        JPanel fileControlGroup = new JPanel();
        JButton submitButton = new JButton();
        
        JTextField blockMaxInput = new JTextField();
        JTextField folderBlocksInput = new JTextField();
        
        JTextField pySize = new JTextField();
        JTextField datSize = new JTextField();
        JTextField csvSize = new JTextField();
        JTextField binSize = new JTextField();
        
        GridBagLayout lastLayout = new GridBagLayout();
        public SettingsDisplay() {
            blockControlGroup.setBackground(TRANSPARENT);
            fileControlGroup.setBackground(TRANSPARENT);
            this.setBackground(MAIN_BLUE);
            
            this.setLayout(lastLayout);
            GridBagConstraints thisConstraints = new GridBagConstraints();
            thisConstraints.fill = GridBagConstraints.BOTH;
            GridBagConstraints itemConstraints = new GridBagConstraints();
            itemConstraints.fill = GridBagConstraints.BOTH;
            
            this.title.setForeground(WHITE);
            this.title.setFont(new Font("Calibri", Font.BOLD, 28));
            thisConstraints.gridx = 0;
            thisConstraints.gridy = 0;
            thisConstraints.weightx = 1;
            thisConstraints.weighty = 0.15;
            this.add(title, thisConstraints);
            
            JPanel blockRight = new JPanel();
            blockRight.setBackground(TRANSPARENT);
            blockControlGroup.setLayout(new GridBagLayout());
            blockMaxInput.setEditable(false);
            blockMaxInput.setText(String.valueOf(systemState.getTotalBloques()));
            SettingsItem blockMaxGroup = new SettingsItem(blockMaxInput, "Bloques del Sistema:", MAIN_TEAL);
            SettingsItem folderBlocksGroup = new SettingsItem(folderBlocksInput, "Bloques de una Carpeta:", MAIN_TEAL);
            
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;
            itemConstraints.insets = new Insets(0,0,10,0);
            itemConstraints.weightx = 0.8;
            itemConstraints.weighty = 0.5;
            itemConstraints.gridheight = 1;
            blockControlGroup.add(blockMaxGroup, itemConstraints);
            
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 1;
            itemConstraints.insets = new Insets(0,0,0,0);
            itemConstraints.weightx = 0.8;
            itemConstraints.weighty = 0.5;
            blockControlGroup.add(folderBlocksGroup, itemConstraints);
            
            itemConstraints.gridx = 1;
            itemConstraints.gridy = 0;
            itemConstraints.weightx = 0.2;
            itemConstraints.weighty = 1;
            itemConstraints.gridheight = 2;
            blockControlGroup.add(blockRight, itemConstraints);
            
            thisConstraints.gridx = 0;
            thisConstraints.gridy = 1;
            thisConstraints.weightx = 1;
            thisConstraints.weighty = 0.15;
            this.add(blockControlGroup, thisConstraints);
            
            pySize.setEditable(false);
            pySize.setText(String.valueOf(systemState.pySize));
            datSize.setEditable(false);
            datSize.setText(String.valueOf(systemState.datSize));
            csvSize.setEditable(false);
            csvSize.setText(String.valueOf(systemState.csvSize));
            binSize.setEditable(false);
            binSize.setText(String.valueOf(systemState.binSize));
            
            SettingsItem pySizeGroup = new SettingsItem (pySize, "'.py'", MAIN_PINK);
            SettingsItem datSizeGroup = new SettingsItem (datSize, "'.dat'", MAIN_PINK);
            SettingsItem csvSizeGroup = new SettingsItem (csvSize, "'.csv'", MAIN_PINK);
            SettingsItem binSizeGroup = new SettingsItem (binSize, "'.bin'", MAIN_PINK);
            fileControlGroup.setLayout(new GridBagLayout());
            
            JPanel dataPanel = new JPanel();
            dataPanel.setBackground(MAIN_TEAL);
            dataPanel.setLayout(new FlowLayout());
            JLabel elementDescr = new JLabel("Bloques de un Archivo:");
            elementDescr.setForeground(WHITE);
            elementDescr.setFont(new Font("Calibri", Font.BOLD, 24));
            dataPanel.add(elementDescr);
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;
            itemConstraints.weightx = 0.6;
            itemConstraints.weighty = 0.2;
            itemConstraints.gridheight = 1;
            fileControlGroup.add(dataPanel, itemConstraints);
            
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 1;
            itemConstraints.weightx = 0.5;
            itemConstraints.weighty = 0.2;
            itemConstraints.gridheight = 1;
            fileControlGroup.add(pySizeGroup, itemConstraints);
            
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 2;
            itemConstraints.weightx = 0.5;
            itemConstraints.weighty = 0.2;
            fileControlGroup.add(datSizeGroup, itemConstraints);
            
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 3;
            itemConstraints.weightx = 0.5;
            itemConstraints.weighty = 0.2;
            fileControlGroup.add(csvSizeGroup, itemConstraints);
            
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 4;
            itemConstraints.weightx = 0.5;
            itemConstraints.weighty = 0.2;
            fileControlGroup.add(binSizeGroup, itemConstraints);
            
            JPanel rightBig = new JPanel();
            rightBig.setBackground(TRANSPARENT);
            
            itemConstraints.gridx = 1;
            itemConstraints.gridy = 0;
            itemConstraints.weightx = 1;
            itemConstraints.weighty = 1;
            itemConstraints.gridwidth = 2;
            itemConstraints.gridheight = 5;
            fileControlGroup.add(rightBig, itemConstraints);
            
            thisConstraints.gridx = 0;
            thisConstraints.gridy = 2;
            thisConstraints.weightx = 1;
            thisConstraints.weighty = 0.55;
            this.add(fileControlGroup, thisConstraints);
        }
        private class SettingsItem extends JPanel{
            JTextField valueHolder;
            JLabel descriptor;
            public SettingsItem(JTextField inputField, String description, Color bgColor) {
                this.setLayout(new GridBagLayout());
                GridBagConstraints smallConstraints = new GridBagConstraints();
                smallConstraints.anchor = GridBagConstraints.CENTER;
                smallConstraints.fill = GridBagConstraints.BOTH;
                this.setBackground(bgColor);
                this.valueHolder = inputField;
                this.descriptor = new JLabel(description);
                
                descriptor.setForeground(WHITE);
                valueHolder.setBackground(MAIN_BEIGE);
                valueHolder.setForeground(MAIN_BLUE);
                
                smallConstraints.weightx = 0.5;
                this.add(descriptor, smallConstraints);
                this.add(valueHolder, smallConstraints);
            }
        }
    }
}
