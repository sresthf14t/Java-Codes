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
public class C_Given_Length_and_Sum_of_Digits {
    public static void main(String rag[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int s=input.nextInt();
        if(s==0) {
            if(n==1) {
                System.out.println(0+" "+0);
            }
            else {
                System.out.println(-1+" "+-1);
            }
            System.exit(0);
        }
        StringBuilder max=new StringBuilder("");
        for(int i=0;i<n;i++) {
            int app=Math.min(s, 9);
            s-=app;
            max.append(app);
        }
        if(s>0) {
            System.out.println(-1+" "+-1);
            System.exit(0);
        }
        StringBuilder min=new StringBuilder(max);
        min=min.reverse();
        if(min.charAt(0)=='0') {
            int indx=0;
            for(;indx<min.length();indx++) {
                if(min.charAt(indx)!='0') {
                    break;
                }
            }
            min.setCharAt(0, '1');
            char chr=(char)((int)min.charAt(indx)-1);
            min.setCharAt(indx, chr);
        }
        System.out.println(min+" "+max);
    }
}
