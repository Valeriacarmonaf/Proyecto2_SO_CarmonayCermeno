/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author Sebastian Cermeno
 */
public class CreationWindow {
    final static int IS_FOLDER = 0;
    final static int IS_FILE = 1;
    
    final Color MAIN_BLUE = new Color(42, 12, 78);
    final Color MAIN_PURPLE = new Color(81,53,90);
    final Color MAIN_TEAL = new Color(80,137,145);
    final Color WHITE = new Color(255, 255, 255);
    
    JFrame windowContainer = new JFrame();
    
    JLabel ObjectNameIndicator = new JLabel();
    HintTextField ObjectNameInput;
    JButton ObjectNameSubmit = new JButton();
    
    JLabel FileTypeIndicator = new JLabel();
    JRadioButton pyTypeFile = new JRadioButton("'.py'");
    JRadioButton datTypeFile = new JRadioButton("'.dat'");
    JRadioButton csvTypeFile = new JRadioButton("'.csv'");
    JRadioButton binTypeFile = new JRadioButton("'.bin'");
    JRadioButton customTypeFile = new JRadioButton("Personalizado:");
    
    public CreationWindow(int objectToCreate, TreeObject recipient, CompositeTree origin) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        windowContainer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel globalContainer = new JPanel();
        globalContainer.setLayout(new BoxLayout(globalContainer, BoxLayout.Y_AXIS));
        windowContainer.add(globalContainer);
        
        ObjectNameIndicator.setForeground(this.MAIN_BLUE);
        
        switch (objectToCreate) {
            case IS_FOLDER:
                windowContainer.setBounds(
                    (int)(screenSize.width * 0.3),
                    (int)(screenSize.height * 0.4),
                    (int)(screenSize.width * 0.4), 
                    (int)(screenSize.height * 0.2)
                );
                windowContainer.setTitle("Creando una carpeta");
                JPanel ObjectNameGroup = new JPanel();
                
                ObjectNameGroup.setLayout(new GridBagLayout());
                GridBagConstraints con = new GridBagConstraints();
                con.fill = GridBagConstraints.BOTH;
                ObjectNameGroup.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                
                ObjectNameIndicator.setText("Introduzca el nombre de la Carpeta");
                ObjectNameIndicator.setFont(new Font("Arial", Font.BOLD, 20));
                ObjectNameIndicator.setForeground(MAIN_BLUE);
                con.weightx = 2;
                con.gridx = 0;
                con.gridy = 0;
                ObjectNameGroup.add(ObjectNameIndicator, con);
                
                ObjectNameInput = new HintTextField("Ejemplo: 'MiCarpeta'");
                con.weightx = 2;
                con.gridx = 0;
                con.gridy = 1;
                ObjectNameGroup.add(ObjectNameInput, con);
                
                ObjectNameSubmit.setText("Hecho");
                ObjectNameSubmit.setBackground(MAIN_BLUE);
                ObjectNameSubmit.setForeground(WHITE);
                ObjectNameSubmit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String folderName = ObjectNameInput.getText();
                        folderName = folderName.trim();
                        folderName = folderName.replace(".", "");
                        folderName = folderName.replace(",", "");
                        if (!folderName.equals("")) {
                            try{
                                folderName += " (Carpeta)";
                                TreeObject newInsert = new TreeObject(folderName, false, TreeObject.IS_FOLDER);
                                recipient.add(newInsert);
                                submitAction();
                                origin.onUpdate();
                            }
                            catch (Exception ex) {}
                        }
                    }
                });
                ObjectNameSubmit.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseExited(MouseEvent me) {
                        ObjectNameSubmit.setBackground(MAIN_BLUE);}
                    @Override
                    public void mouseEntered(MouseEvent me) {
                        ObjectNameSubmit.setBackground(MAIN_TEAL);
                    }
                    @Override
                    public void mouseReleased(MouseEvent me) {}
                    @Override
                    public void mousePressed(MouseEvent me) {}
                    @Override
                    public void mouseClicked(MouseEvent me) {}
                });
                con.fill = GridBagConstraints.EAST;
                con.weightx = 0;
                con.gridx = 0;
                con.gridwidth = 1;
                con.anchor = GridBagConstraints.PAGE_END;
                con.gridy = 2;
                ObjectNameGroup.add(ObjectNameSubmit, con);
                
                windowContainer.add(ObjectNameGroup);
                windowContainer.setVisible(true);
                break;
            case IS_FILE:
                windowContainer.setBounds(
                    (int)(screenSize.width * 0.3),
                    (int)(screenSize.height * 0.3),
                    (int)(screenSize.width * 0.4), 
                    (int)(screenSize.height * 0.4)
                );
                windowContainer.setTitle("Creando un archivo");
                // Grupo de Selección de Nombre
                JPanel fileCreateGroup = new JPanel();
                fileCreateGroup.setSize(0, (int)(screenSize.height * 0.1));
                fileCreateGroup.setLayout(new GridBagLayout());
                GridBagConstraints fileCon = new GridBagConstraints();
                fileCon.fill = GridBagConstraints.HORIZONTAL;
                fileCreateGroup.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                
                ObjectNameIndicator.setText("Introduzca el nombre del Archivo");
                ObjectNameIndicator.setFont(new Font("Arial", Font.BOLD, 20));
                ObjectNameIndicator.setForeground(MAIN_BLUE);
                fileCon.weightx = 1;
                fileCon.gridx = 0;
                fileCon.gridy = 0;
                fileCreateGroup.add(ObjectNameIndicator, fileCon);
                
                ObjectNameInput = new HintTextField("Ejemplo: 'MiArchivo'");
                fileCon.weightx = 1;
                fileCon.gridx = 0;
                fileCon.gridy = 1;
                fileCon.gridwidth = 2;
                fileCon.insets = new Insets(0,0,15,0);
                fileCreateGroup.add(ObjectNameInput, fileCon);
                
                // Grupo de Especificación de Archivo
                FileTypeIndicator.setText("Indique el tipo de archivo");
                FileTypeIndicator.setFont(ObjectNameIndicator.getFont());
                FileTypeIndicator.setForeground(MAIN_BLUE);
                fileCon.weightx = 1;
                fileCon.gridx = 0;
                fileCon.gridy = 2;
                fileCon.gridwidth = 1;
                fileCon.insets = new Insets(0,0,0,0);
                fileCreateGroup.add(FileTypeIndicator, fileCon);
                
                ButtonGroup selectorSet = new ButtonGroup();
                selectorSet.add(pyTypeFile);
                selectorSet.add(datTypeFile);
                selectorSet.add(csvTypeFile);
                selectorSet.add(binTypeFile);
                selectorSet.add(customTypeFile);
                
                fileCon.weightx =  1;
                fileCon.gridx = 0;
                fileCon.gridy = 3;
                fileCon.insets = new Insets(0,10,0,0);
                pyTypeFile.setSelected(true);
                fileCreateGroup.add(pyTypeFile, fileCon);
                
                fileCon.weightx =  1;
                fileCon.gridx = 0;
                fileCon.gridy = 4;
                fileCon.insets = new Insets(0,10,0,0);
                fileCreateGroup.add(datTypeFile, fileCon);
                
                fileCon.weightx =  1;
                fileCon.gridx = 0;
                fileCon.gridy = 5;
                fileCon.insets = new Insets(0,10,0,0);
                fileCreateGroup.add(csvTypeFile, fileCon);
                
                fileCon.weightx =  1;
                fileCon.gridx = 0;
                fileCon.gridy = 6;
                fileCon.insets = new Insets(0,10,0,0);
                fileCreateGroup.add(binTypeFile, fileCon);
                
                fileCon.weightx =  1;
                fileCon.gridx = 0;
                fileCon.gridy = 7;
                fileCon.insets = new Insets(0,10,5,0);
                fileCreateGroup.add(customTypeFile, fileCon);
                
                JLabel fileTypeIndicator = new JLabel("Tipo:");
                fileTypeIndicator.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
                fileTypeIndicator.setForeground(MAIN_BLUE);
                HintTextField fileTypeInput = new HintTextField("Ej: .jsx");
                
                fileCon.weightx = 0.3;
                fileCon.gridx = 0;
                fileCon.gridy = 8;
                fileCon.insets = new Insets(0,0,0,0);
                fileCreateGroup.add(fileTypeIndicator, fileCon);
                fileCon.gridy = 9;
                fileCreateGroup.add(fileTypeInput, fileCon);
                
                JLabel blockCountIndicator = new JLabel("Número de bloques:");
                blockCountIndicator.setFont(fileTypeIndicator.getFont());
                blockCountIndicator.setForeground(MAIN_BLUE);
                HintTextField blockCountInput = new HintTextField("Ej: 8");
                
                fileCon.weightx = 0.3;
                fileCon.gridx = 1;
                fileCon.gridy = 8;
                fileCon.insets = new Insets(0,10,0,0);
                fileCreateGroup.add(blockCountIndicator, fileCon);
                fileCon.gridy = 9;
                fileCreateGroup.add(blockCountInput, fileCon);
                
                JButton fileSubmit = new JButton("Hecho");
                fileSubmit.setBackground(MAIN_BLUE);
                fileSubmit.setForeground(WHITE);
                fileSubmit.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseExited(MouseEvent me) {
                        fileSubmit.setBackground(MAIN_BLUE);}
                    @Override
                    public void mouseEntered(MouseEvent me) {
                        fileSubmit.setBackground(MAIN_TEAL);
                    }
                    @Override
                    public void mouseReleased(MouseEvent me) {}
                    @Override
                    public void mousePressed(MouseEvent me) {}
                    @Override
                    public void mouseClicked(MouseEvent me) {}
                });
                fileSubmit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fileName = ObjectNameInput.getText();
                        fileName = fileName.trim();
                        fileName = fileName.replace(".", "");
                        fileName = fileName.replace(",", "");
                        fileName = fileName.replace("!", "");
                        fileName = fileName.replace("?", "");
                        fileName = fileName.replace("¡", "");
                        fileName = fileName.replace("¿", "");
                        if ((!fileName.equals("")) && (!fileName.equals("Ejemplo: 'MiArchivo'"))) {
                            // Pendiente: Uso del tamaño de bloques de un archivo
                            if (pyTypeFile.isSelected()) {
                                fileName += ".py";
                            }
                            else if (datTypeFile.isSelected()) {
                                fileName += ".dat";
                            }
                            else if (csvTypeFile.isSelected()) {
                                fileName += ".csv";
                            }
                            else if (binTypeFile.isSelected()) {
                                fileName += ".bin";
                            }
                            else if (customTypeFile.isSelected()) {
                                String typeEnd = fileTypeInput.getText();
                                typeEnd = typeEnd.trim();
                                typeEnd = typeEnd.replace(",", "");
                                if (typeEnd.equals("")) {
                                    typeEnd = ".custom";
                                }
                                else {
                                    if (typeEnd.charAt(0) != '.') {
                                        typeEnd = "." + typeEnd;
                                    }
                                }
                                fileName += typeEnd;
                            }
                            recipient.add(new TreeObject(fileName, false, TreeObject.IS_FILE));
                            origin.onUpdate();
                            windowContainer.dispose();
                        }
                    }
                });
                fileCon.weightx = 0.3;
                fileCon.gridwidth = 1;
                fileCon.anchor = GridBagConstraints.CENTER;
                fileCon.gridx = 0;
                fileCon.gridy = 10;
                fileCreateGroup.add(fileSubmit, fileCon);
                
                windowContainer.add(fileCreateGroup);
                windowContainer.setVisible(true);
                break;

            default:
                break;
        }
    }
    
    private void submitAction() {
        this.windowContainer.dispose();
    }
    
    public class HintTextField extends JTextField {  
        public HintTextField(final String hint) {  
          setText(hint); 
          setForeground(Color.GRAY);  

          this.addFocusListener(new FocusAdapter() {  

            @Override  
            public void focusGained(FocusEvent e) {  
              if (getText().equals(hint)) {  
                setText("");  
              } else {  
                setText(getText());  
              }  
            }  

            @Override  
            public void focusLost(FocusEvent e) {  
              if (getText().equals(hint)|| getText().length()==0) {  
                setText(hint);  
                setForeground(Color.GRAY);  
              } else {  
                setText(getText());  
                setForeground(Color.BLACK);  
              }  
            }  
          });
        }  
    }  
}
