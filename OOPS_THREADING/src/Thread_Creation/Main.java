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
public class Main {
    public static void main(String args[]) {
        First_Threaded_Program ob1=new First_Threaded_Program();
        First_Threaded_Program ob2=new First_Threaded_Program();
        ob1.start();
        ob2.start();
    }
}
