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
public class B_Anton_and_Digits {
    public static void main(String rag[]) {
        Scanner input=new Scanner(System.in);
        long two=input.nextLong();
        long three=input.nextLong();
        long five=input.nextLong();
        long six=input.nextInt();
        long n_256=Math.min(Math.min(two, five), six);
        two-=n_256;
        long n_32=Math.min(two,three);
        long ans=(n_256*256)+(n_32*32);
        System.out.println(ans);
    }
}