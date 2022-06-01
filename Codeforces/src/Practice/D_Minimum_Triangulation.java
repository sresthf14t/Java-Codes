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
public class D_Minimum_Triangulation {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int sum=0;
        for(int i=2;i<n;i++) {
            sum+=i*(i+1);
        }
        System.out.println(sum);
    }
}
