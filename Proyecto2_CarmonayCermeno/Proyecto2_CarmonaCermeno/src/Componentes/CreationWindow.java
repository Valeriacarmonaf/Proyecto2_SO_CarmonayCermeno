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
    JRadioButton pyTypeFile = new JRadioButton();
    JRadioButton datTypeFile = new JRadioButton();
    JRadioButton csvTypeFile = new JRadioButton();
    JRadioButton binTypeFile = new JRadioButton();
    JRadioButton customTypeFile = new JRadioButton();
    
    public CreationWindow(int objectToCreate, TreeObject recipient) {
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
                                TreeObject newInsert = new TreeObject(folderName, false, TreeObject.IS_FOLDER);
                                recipient.add(newInsert);
                                submitAction();
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
                    (int)(screenSize.height * 0.35),
                    (int)(screenSize.width * 0.4), 
                    (int)(screenSize.height * 0.3)
                );
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
