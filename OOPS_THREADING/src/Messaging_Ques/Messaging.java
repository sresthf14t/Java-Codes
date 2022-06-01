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
public class Messaging {
    static String msg[]={"A1","B1","A2","B2","A3","B3"};
    boolean flag=false;
    public synchronized void m1() {
        for(int i=0;i<msg.length;i=i+2) {
            if(flag) {
                try {
                    wait();
                }
                catch(Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println("User 1"+"\t"+msg[i]);
            flag=true;
            notify();
        }
    }
    public synchronized void m2() {
        for(int i=1;i<msg.length;i=i+2) {
            if(!flag) {
                try {
                    wait();
                }
                catch(Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println("User 2"+"\t"+msg[i]);
            flag=false;
            notify();
        }
    }
}
