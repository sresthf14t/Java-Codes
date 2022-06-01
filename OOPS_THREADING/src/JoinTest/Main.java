/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JoinTest;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String args[]) {
        DemoClass D1=new DemoClass();
        DemoClass D2=new DemoClass();
        D2.start();
        D1.start();
        try {
            D1.join();
            D2.join();
        }
        catch(InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(DemoClass.count);
    }
}
