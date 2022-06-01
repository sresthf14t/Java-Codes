/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COOK114;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class CHFCHK {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int min=input.nextInt();
            for(int i=1;i<n;i++) {
                int tmp=input.nextInt();
                if(tmp<min) {
                    min=tmp;
                }
            }
            System.out.println(min);
        }
    }
}
