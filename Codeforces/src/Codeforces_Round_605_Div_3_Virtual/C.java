/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_605_Div_3_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int k=input.nextInt();
        String str=input.next();
        boolean chr[]=new boolean[26];
        for(int i=0;i<k;i++) {
            chr[input.next().charAt(0)-97]=true;
        }
        long n_substr=0,count=0;
        for(int i=0;i<n;i++) {
            if(!chr[str.charAt(i)-97]) {
                n_substr+=(count*(count+1))/2;
                count=0;
            }
            else {
                count++;
            }
        }
        n_substr+=(count*(count+1))/2;
        System.out.println(n_substr);
    }
}
