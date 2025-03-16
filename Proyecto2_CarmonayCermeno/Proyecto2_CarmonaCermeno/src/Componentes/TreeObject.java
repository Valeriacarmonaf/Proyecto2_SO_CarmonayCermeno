package Componentes;

import java.util.zip.DataFormatException;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sebastian Cermeno
 */
public class TreeObject extends DefaultMutableTreeNode{
    public static final int IS_FILE = 0;
    public static final int IS_FOLDER = 1;
    public final boolean IS_SYSTEM_ROOT;
    public final int ObjectType;
    public TreeObject(String descriptor, boolean sysRoot, int objectType) throws Exception{
        super(descriptor);
        IS_SYSTEM_ROOT = sysRoot;
        switch(objectType) {
            case IS_FILE:
                this.allowsChildren = false;
                this.ObjectType = objectType;
                break;
            case IS_FOLDER:
                this.allowsChildren = true;
                this.ObjectType = objectType;
                break;
            default:
                throw new Exception("Tipo de objeto no reconocido.");
        }
    }
}
