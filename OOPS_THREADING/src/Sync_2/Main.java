/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sync_2;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String args[]) {
        Calc C=new Calc();
        Thread MainThread=Thread.currentThread();
        Thread t1=new Thread(new Runnable() {
           public void run() {
               C.inc();
           } 
        });
        Thread t2=new Thread(new Runnable() {
           public void run() {
               C.dec();
           } 
        });
        t1.start();
        t2.start();
        System.out.println("t1 "+t1.isAlive());
        System.out.println("t2 "+t2.isAlive());
        try {
          MainThread.sleep(1);  
        }
        catch(Exception e) {
            System.out.println(e);
        }
        System.out.println(C.count);
        System.out.println("t1 "+t1.isAlive());
        System.out.println("t2 "+t2.isAlive());
        try {
          MainThread.sleep(10);  
        }
        catch(Exception e) {
            System.out.println(e);
        }
        System.out.println(C.count);
        System.out.println("t1 "+t1.isAlive());
        System.out.println("t2 "+t2.isAlive());
        try {
          MainThread.sleep(1000);  
        }
        catch(Exception e) {
            System.out.println(e);
        }
        System.out.println(C.count);
        System.out.println("t1 "+t1.isAlive());
        System.out.println("t2 "+t2.isAlive());
    }
}
