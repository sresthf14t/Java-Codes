/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codiction;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class CC000 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            double x1=input.nextDouble();
            double y1=input.nextDouble();
            double x2=input.nextDouble();
            double y2=input.nextDouble();
            double x0=((y1*x2)+(y2*x1))/(y1+y2);
            System.out.println(x0);
        }
    }
}
