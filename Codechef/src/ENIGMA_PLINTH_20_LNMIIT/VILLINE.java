/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENIGMA_PLINTH_20_LNMIIT;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class VILLINE {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int n=input.nextInt();
        int m=input.nextInt();
        int c=input.nextInt();
        int pow_up=0,pow_dn=0;
        for(int i=0;i<n;i++) {
            int x=input.nextInt();
            int y=input.nextInt();
            int power=input.nextInt();
            if(func(m,c,x,y)>0) {
                pow_up+=power;
            }
            else {
                pow_dn+=power;
            }
        }
        if(pow_up>pow_dn) {
            System.out.println(pow_up);
        }
        else {
            System.out.println(pow_dn);
        }
    }
    
    public static int func(int m,int c,int x,int y) {
        return (m*x)+c-y;
    }
}
