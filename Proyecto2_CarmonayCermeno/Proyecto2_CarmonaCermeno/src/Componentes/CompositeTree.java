/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.*;

/**
 *
 * @author Sebastian Cermeno
 */
public class CompositeTree extends JTree{
    actionMenu rightClickActions = actionMenu.getInstance(this);
    TreeObject focusedNode = null;
    JFrame holder;
    MouseAdapter eventCapture = new MouseAdapter() {
        private void summonPopUp(MouseEvent e) {
            int x_coord = e.getX();
            int y_coord = e.getY();
            JTree tree = (JTree)e.getSource();
            TreePath path = tree.getPathForLocation(x_coord, y_coord);
            if (path == null) {
                return;
            }
            tree.setSelectionPath(path);
            TreeObject rightClickedNode = (TreeObject)path.getLastPathComponent();
            if ((holder != null) && (rightClickedNode.ObjectType == TreeObject.IS_FOLDER)){
                focusedNode = rightClickedNode;
                rightClickActions.show(holder, x_coord, y_coord, focusedNode);
            }
        }
        @Override
        public void mouseClicked(MouseEvent e){
            if (SwingUtilities.isRightMouseButton(e)){
                this.summonPopUp(e);
            }
        }
    };
    
    public CompositeTree(DefaultMutableTreeNode element, JFrame holder) {
        super(element);
        this.holder = holder;
        this.addMouseListener(eventCapture);
    }
    
    private static class actionMenu extends JPopupMenu{
        JMenuItem folderOption = new JMenuItem("Añadir Carpeta...");
        JMenuItem fileOption = new JMenuItem("Añadir Archivo...");
        JMenuItem deleteOption = new JMenuItem("Eliminar Elemento");
        
        static JTree summoner;
        TreeObject subject = null;
        
        private static actionMenu INSTANCE;
        
        public static synchronized actionMenu getInstance(JTree invoker) {
            summoner = invoker;
            if (INSTANCE == null) {
                INSTANCE = new actionMenu();
            }
            return INSTANCE;
        }
        
        private actionMenu() {
            this.folderOption.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CreationWindow folderCreateScreen = new CreationWindow(CreationWindow.IS_FOLDER, subject);
                }
            });
            //this.fileOption.addMouseListener(this);
            this.deleteOption.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (subject != null) {
                        if (!subject.IS_SYSTEM_ROOT) {
                            subject.removeFromParent();
                            if (summoner != null) {
                                DefaultTreeModel model = (DefaultTreeModel)summoner.getModel();
                                model.reload();
                            }
                            subject = null;
                        }
                    }
                }
            });
            
            this.folderOption.setBackground(new Color(145,145,145));
            this.folderOption.setForeground(Color.white);
            this.fileOption.setBackground(new Color(217,217,217));
            this.deleteOption.setBackground(new Color(145,145,145));
            this.deleteOption.setForeground(Color.white);
            
            this.add(this.folderOption);
            this.add(this.fileOption);
            this.add(this.deleteOption);
        }
        public void show(Component invoker, int x, int y, TreeObject target){
            subject = target;
            super.show(invoker, x, y);
        }
    }
}
