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
public class A_Sasha_and_His_Trip {
    public static void main(String rag[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int v=input.nextInt();
        int sum;
        if(n>v) {
            n-=v;
            sum=(((n)*(n+1))/2)+(v-1);
        }
        else {
            sum=n-1;
        }
        System.out.println(sum);
    }
}
