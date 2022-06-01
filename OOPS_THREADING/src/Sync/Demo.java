/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sync;

/**
 *
 * @author User
 */
public class Demo extends Thread{
    static int count=0;
    public void run() {
        m1();
    }
    public static synchronized void m1() {
        for(int i=0;i<10000;i++) {
            count++;
        }
    }
}
