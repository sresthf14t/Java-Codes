/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trees;

/**
 *
 * @author User
 */
class Node {
    int data;
    Node right,left;
}
public class BST {
    static void insert(Node root,Node np) {
        if(root==null) {
            root=np;
            return;
        }
        if(np.data>root.data) {
            insert(root.right,np);
        }
        else {
            insert(root.left,np);
        }
    }
    public static void  main(String args[]) {
        
    }
}
