/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Microsoft_Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class Create_BST {
    static class Node {
        int data;
        Node left,right;
        public Node(int ele) {
            data=ele;
            left=right=null;
        }
    }
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        Node root=null;
        int n=input.nextInt();
        for(int i=0;i<n;i++) {
            root=insert(root,input.nextInt());
        }
        bottom_view(root);
    }
    public static Node insert(Node root,int ele) {
        if(root==null) {
            root=new Node(ele);
            return root;
        }
        if(ele<root.data) {
            root.left=insert(root.left,ele);
        }
        else {
            root.right=insert(root.right,ele);
        }
        return root;
    }
    public static void preorder(Node root) {
        if(root==null) {
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }
    
    public static void inorder(Node root) {
        if(root==null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    
    public static void postorder(Node root) {
        if(root==null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data+" ");
    }
    
    public static Node delete(Node root,int ele) {
        if(ele<root.data) {
            root.left=delete(root.left,ele);
            return root;
        }
        if(ele>root.data) {
            root.right=delete(root.right,ele);
            return root;
        }
        //If the node to be deleted has no child
        if(root.left==null && root.right==null) {
            root=null;
            return root;
        }
        //If the node to be deleted has 1 child only right
        if(root.left==null) {
            return root.right;
        }
        //If the node to be deleted has 1 child only left
        if(root.right==null) {
            return root.left;
        }
        //Getting min of the right subtree i.e Inorder sucessor
        int min=get_min_value(root.right);
        System.out.println(min);
        //Deleting the Inorder sucessor
        root=delete(root,min);
        //Setting the value of current node to that
        root.data=min;
        
        return root;
    }
    //Getting min of the right subtree i.e Inorder sucessor
    public static int get_min_value(Node root) {
        int min=-1;
        while(root!=null) {
            min=root.data;
            root=root.left;
        }
        return min;
    }
    
    public static void top_view(Node root) {
        TreeMap<Integer,Integer> map=new TreeMap<>();
        Queue<Node> que=new LinkedList<>();
        Queue<Integer> horiz=new LinkedList<>();
        que.add(root);
        horiz.add(0);
        while(!que.isEmpty()) {
            root=que.poll();
            int h=horiz.poll();
            if(root==null) {
                continue;
            }
            if(!map.containsKey(h)) {
                map.put(h, root.data);
            }
            que.add(root.left);
            horiz.add(h-1);
            que.add(root.right);
            horiz.add(h+1);
        }
        while(!map.isEmpty()) {
            int key=map.firstKey();
            System.out.print(map.get(key)+" ");
            map.remove(key);
        }
        System.out.println();
    }
    static int max_level;
    public static void left_view(Node root,int level) {
        if(root==null) {
            return;
        }
        if(level>max_level) {
            System.out.print(root.data+" ");
            max_level=level;
        }
        left_view(root.left,level+1);
        left_view(root.right,level+1);
    }
    
    public static void right_view(Node root,int level) {
        if(root==null) {
            return;
        }
        if(level>max_level) {
            System.out.print(root.data+" ");
            max_level=level;
        }
        right_view(root.right,level+1);
        right_view(root.left,level+1);
    }
    
    public static void bottom_view(Node root) {
        TreeMap<Integer,Integer> map=new TreeMap<>();
        Queue<Node> que=new LinkedList<>();
        Queue<Integer> horiz=new LinkedList<>();
        que.add(root);
        horiz.add(0);
        while(!que.isEmpty()) {
            root=que.poll();
            int h=horiz.poll();
            if(root==null) {
                continue;
            }
            if(!map.containsKey(h)) {
                map.put(h, root.data);
            }
            else {
                map.replace(h, root.data);
            }
            que.add(root.left);
            horiz.add(h-1);
            que.add(root.right);
            horiz.add(h+1);
        }
        while(!map.isEmpty()) {
            int key=map.firstKey();
            System.out.print(map.get(key)+" ");
            map.remove(key);
        }
        System.out.println();
    }
}

