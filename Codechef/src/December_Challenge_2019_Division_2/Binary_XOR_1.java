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
import java.math.BigInteger;
public class Binary_XOR_1 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        long modulo=(long)(Math.pow(10,9)+7);
        for(int i=1;i<=t;i++) {
            int n=input.nextInt();
            int m1=0,n1=0,m2=0,n2=0;
            String A=input.next();
            String B=input.next();
            for(int j=0;j<n;j++) {
                if(A.charAt(j)=='0') {
                    m1++;
                }
                else {
                    n1++;
                }
                if(B.charAt(j)=='0') {
                    m2++;
                }
                else {
                    n2++;
                }
            }
            int max_1s=min(n1,m2)+min(n2,m1);
            int max_0s=min(n1,n2)+min(m1,m2);
            int min_1s=n-max_0s;
            BigInteger total=new BigInteger(""+0);
            BigInteger mod=new BigInteger(""+modulo);
            long n_fact=fact(n);
            for(int j=min_1s;j<=max_1s;j+=2) {
                int ones=j;
                int zeros=n-j;
                BigInteger n_factorial=new BigInteger(""+n_fact);
                BigInteger ones_factorial=new BigInteger(""+fact(ones));
                BigInteger zeros_factorial=new BigInteger(""+fact(zeros));
                BigInteger denominator=zeros_factorial.multiply(ones_factorial);
                BigInteger combinations=n_factorial.divide(denominator);
                total=total.add(combinations);
            }
            total=total.mod(mod);
            System.out.println(total);
        }
    }
    public static int min(int a,int b) {
        if(a<b) {
            return a;
        }
        else {
            return b;
        }
    }
    public static long fact(int n) {
        long modulo=(long)(Math.pow(10,9)+7);
        long fact=1;
        for(int i=2;i<=n;i++) {
            fact*=i;
            fact%=modulo;
        }
        return fact;
    }
}
