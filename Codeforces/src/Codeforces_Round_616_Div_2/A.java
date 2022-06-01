/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_616_Div_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class A {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            String str=input.next();
            String tmp="";
            int count=0;
            for(int i=0;i<n && count<2;i++) {
                if((str.charAt(i)-48)%2==1) {
                    tmp+=str.charAt(i);
                    count++;
                }
            }
            if(count<2) {
                System.out.println(-1);
            }
            else {
                System.out.println(tmp);
            }
        }
    }
}
