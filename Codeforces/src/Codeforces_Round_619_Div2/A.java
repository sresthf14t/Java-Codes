/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_619_Div2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class A {
    public static void main(String rags[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            String a=input.next();
            String b=input.next();
            String c=input.next();
            int n=a.length();
            boolean is_possible=true;
            for(int i=0;i<n;i++) {
                if(a.charAt(i)==c.charAt(i) || b.charAt(i)==c.charAt(i)) ;
                else {
                    is_possible=false;
                    break;
                }
            }
            if(is_possible) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }   
}
