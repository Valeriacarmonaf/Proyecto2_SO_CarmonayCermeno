/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import FuncionesProyecto.FileSystemState;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;
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
    Container holder;
    static FileSystemState systemRef;
    public static final int ADDED_FILE = 0;
    public static final int ADDED_FOLDER = 1;
    public static final int REMOVED_ITEM = 2;
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
            if (holder != null){
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
    
    public void onUpdate(){
        DefaultTreeModel myModel = (DefaultTreeModel)(this.getModel());
        myModel.reload();
    }
    public void onUpdate(String objectName, int blockSize, int operationCode) {
        DefaultTreeModel myModel = (DefaultTreeModel)(this.getModel());
        myModel.reload();
        switch (operationCode) {
            case ADDED_FILE:
                systemRef.agregarArchivo(objectName, blockSize, FileSystemState.DEFAULT);
                break;
            case ADDED_FOLDER:
                break;
            case REMOVED_ITEM:
                break;
            default:
                break;
        }
    }
    
    public CompositeTree(DefaultMutableTreeNode element, Container holder, FileSystemState sysRef) {
        super(element);
        this.systemRef = sysRef;
        this.holder = holder;
        this.addMouseListener(eventCapture);
    }
    
    private static class actionMenu extends JPopupMenu{
        JMenuItem folderOption = new JMenuItem("Añadir Carpeta...");
        JMenuItem fileOption = new JMenuItem("Añadir Archivo...");
        JMenuItem deleteOption = new JMenuItem("Eliminar Elemento");
        
        static CompositeTree summoner;
        TreeObject subject = null;
        
        private static actionMenu INSTANCE;
        
        public static synchronized actionMenu getInstance(CompositeTree invoker) {
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
                    CreationWindow folderCreateScreen = new CreationWindow(CreationWindow.IS_FOLDER, subject, summoner, systemRef);
                }
            });
            this.fileOption.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CreationWindow fileCreateScreen = new CreationWindow(CreationWindow.IS_FILE, subject, summoner, systemRef);
                }
            });
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
            if (subject.ObjectType == TreeObject.IS_FILE) {
                folderOption.setVisible(false);
                fileOption.setVisible(false);
            }
            else if (subject.ObjectType == TreeObject.IS_FOLDER) {
                folderOption.setVisible(true);
                fileOption.setVisible(true);
            }
            super.show(invoker, x, y);
        }
    }
}
