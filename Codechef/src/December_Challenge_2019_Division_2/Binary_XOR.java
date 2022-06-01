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
public class Binary_XOR {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        long modulo=1000000007;
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
            long total=0;
            long n_fact=fact(n);
            for(int j=min_1s;j<=max_1s;j+=2) {
                int ones=j;
                int zeros=n-j;
                long combinations=n_fact/(fact(ones)*fact(zeros));
                combinations%=modulo;
                total+=combinations;
            }
            total%=modulo;
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
        long modulo=1000000007;
        long fact=1;
        for(int i=2;i<=n;i++) {
            fact*=i;
            fact%=modulo;
        }
        return fact;
    }
}
