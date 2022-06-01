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
public class Main {
    public static void main(String args[]) {
        Demo D1=new Demo();
        Demo D2=new Demo();
        D2.start();
        D1.start();
        try {
           D1.join();
           D2.join();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        System.out.println(Demo.count);
    }
}
