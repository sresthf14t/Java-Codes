/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messaging_Ques;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String args[]) {
        Messaging msg=new Messaging();
        Thread t1=new Thread(new Runnable() {
            public void run() {
                msg.m1();
            }
        });
        Thread t2=new Thread(new Runnable() {
            public void run() {
                msg.m2();
            }
        });
        t1.start();
        t2.start();
    }
}
