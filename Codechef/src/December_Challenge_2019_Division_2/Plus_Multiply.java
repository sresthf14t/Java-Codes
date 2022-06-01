/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package December_Challenge_2019_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class Plus_Multiply {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int i=1;i<=t;i++) {
            int no_of_twos=0,no_of_zeros=0;
            int n=input.nextInt();
            for(int j=0;j<n;j++) {
                int temp=input.nextInt();
                if(temp==2) {
                    no_of_twos++;
                }
                if(temp==0) {
                    no_of_zeros++;
                }
            }
            int total = ((no_of_twos*(no_of_twos-1))/2)+((no_of_zeros*(no_of_zeros-1))/2);
            System.out.println(total);
        }
    }
}
