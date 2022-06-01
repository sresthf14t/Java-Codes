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
public class A_Team {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int solve=0;
        for(int i=0;i<n;i++) {
            int a=input.nextInt(),b=input.nextInt(),c=input.nextInt();
            int count=0;
            if(a==1) {
                count++;
            }
            if(b==1) {
                count++;
            }
            if(c==1) {
                count++;
            }
            if(count>1) {
                solve++;
            }
        }
        System.out.println(solve);
    }
}
