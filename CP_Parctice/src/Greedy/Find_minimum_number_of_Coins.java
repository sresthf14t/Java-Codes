/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedy;

/**
 *
 * @author User
 */
/*
Given a value V, if we want to make change for V Rs, and we have infinite 
supply of each of the denominations in Indian currency, i.e., we have infinite
supply of { 1, 2, 5, 10, 20, 50, 100, 500, 1000} valued coins/notes, what is
the minimum number of coins and/or notes needed to make the change?
*/
import java.util.*;
import java.io.*;
public class Find_minimum_number_of_Coins {
    public static void main(String rag[]) {
        Scanner input=new Scanner(System.in);
        int value=input.nextInt();
        int denominatons[]={1,2,5,10,20,50,100,500,1000};
        int no_taken[]=new int[denominatons.length];
        int no_coins=0;
        for(int i=denominatons.length-1;i>=0;i--) {
            no_coins+=value/denominatons[i];
            no_taken[i]=value/denominatons[i];
            value=value%denominatons[i];
        }
        for(int i=0;i<denominatons.length;i++) {
            System.out.println(denominatons[i]+" "+no_taken[i]);
        }
        System.out.println("Total no of coins taken="+no_coins);
    }
}
