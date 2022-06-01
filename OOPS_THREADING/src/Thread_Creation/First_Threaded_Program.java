/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread_Creation;

/**
 *
 * @author User
 */
public class First_Threaded_Program extends Thread{
    public void run() {
        for(int i=0;i<10;i++) {
            System.out.print(i+" ");
        }
    }
}
