/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sync_1;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String args[]) {
        Demo ob1=new Demo();
        Thread t1=new Thread(new Runnable() {
            public void run() {
                m1();
            }
            public synchronized void m1() {
                ob1.inc();
            }
        });
        Thread t2=new Thread(new Runnable() {
            public void run() {
                m1();
            }
            public synchronized void m1() {
                ob1.inc();
            }
        });
        t1.start();
        t2.start();
        for(int i=0;i<100000;i++);
        System.out.println(ob1.count);
    }
}
