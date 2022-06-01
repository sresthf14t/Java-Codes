/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A_Hit_the_Lottery {
    public static void main(String rag[]) {
        Scanner input=new Scanner(System.in);
        int value=input.nextInt();
        int denominatons[]={1,5,10,20,100};
        int no_taken[]=new int[denominatons.length];
        int no_coins=0;
        for(int i=denominatons.length-1;i>=0;i--) {
            no_coins+=value/denominatons[i];
            no_taken[i]=value/denominatons[i];
            value=value%denominatons[i];
        }
        System.out.println(no_coins);
    }
}
