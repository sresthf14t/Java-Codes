/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeOverflow_1_0;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class MITLOV {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        long n=input.nextLong();
        long m=input.nextLong();
        long gcd=GCD(n,m);
        long lcm=(n*m)/gcd;
        System.out.println(lcm);
    }
    
    public static long GCD(long a,long n) {
            long q,r1=n,r2=a,r;
            while(true) {
                q=r1/r2;
                r=r1%r2;
                r1=r2;
                r2=r;
                if(r2==0) {
                        break;
                }
            }
            return r1;
                 
	}
}
