/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_614_Div2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class B {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int n=input.nextInt();
        float sum=0;
        for(int i=n;i>0;i--) {
            sum+=1.0/i;
        }
        System.out.println(sum);
    }
}
