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
public class Detect_loop_in_a_linked_list {
    static class Node {
        int data;
        Node next;
        public Node(int ele) {
            data=ele;
        }
    }
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        Node head=null;
        int n=input.nextInt();
        for(int i=0;i<n;i++) {
            head=add(head,input.nextInt());
        }
        
    }
    public static Node add(Node head,int ele) {
        if(head==null) {
            head=new Node(ele);
            return head;
        }
        Node tmp=head;
        while(tmp.next!=null) {
            tmp=tmp.next;
        }
        Node last=new Node(ele);
        tmp.next=last;
        return head;
    }
    public static boolean detect_cycle(Node head) {
        Node slow_p=head,fast_p=head;
        while(true) {
            if(slow_p!=null) {
                slow_p=slow_p.next;
            }
            if(fast_p!=null) {
                fast_p=fast_p.next;
            }
            if(fast_p!=null) {
                fast_p=fast_p.next;
            }
            if(slow_p==fast_p) {
                break;
            }
        }
        if(slow_p==null) {
            return false;
        }
        return true;
    }
}
