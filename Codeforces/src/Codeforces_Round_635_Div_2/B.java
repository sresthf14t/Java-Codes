/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_635_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int x=input.nextInt();
            int n=input.nextInt();
            int m=input.nextInt();
            while(x>20 && n>0) {
                x=(x/2);
                x+=10;
                n--;
            }
            while(x>0 && m>0) {
                x-=10;
                m--;
            }
            if(x<=0) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
