/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package June_Challenge_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class GUESSG {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) throws IOException {
        int n=input.nextInt();
        int l=1,r=n;
        while(true) {
            int pivot=(l+r)/2;
            System.out.println(n);
            System.out.flush();
            char check1=input.next().charAt(0);
            if(check1=='E') {
                System.exit(0);
            }
            
            System.out.println(pivot);
            System.out.flush();
            char in1=input.next().charAt(0);
            if(in1=='E') {
                System.exit(0);
            }
            
            System.out.println(pivot);
            System.out.flush();
            char in2=input.next().charAt(0);
            if(in2=='E') {
                System.exit(0);
            }
            
            if(check1=='G' || in1==in2) {
                if(in1=='G') {
                    l=pivot+1;
                }
                else {
                    r=pivot-1;
                }
                continue;
            }
            
            
            System.out.println(n);
            System.out.flush();
            char check2=input.next().charAt(0);
            if(check2=='E') {
                System.exit(0);
            }
            
            if(check2=='G') {
                if(in2=='G') {
                    l=pivot+1;
                }
                else {
                    r=pivot-1;
                }
                continue;
            }
            
        }
    }
}
