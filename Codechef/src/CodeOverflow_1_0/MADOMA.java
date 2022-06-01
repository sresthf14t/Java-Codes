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
import java.math.BigInteger;
public class MADOMA {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        long n=input.nextLong();
        long a=input.nextLong();
        long b=input.nextLong();
        n--;
        long mod=(long)(Math.pow(10, 9) +7);
        long sum;
        if(n%2==0) {
            sum=(((a+b)*power(4L,n,mod))+((4*a)-b))/5;
        }
        else {
            sum=(((a+b)*power(4L,n,mod))-((4*a)-b))/5;
        }
        sum%=mod;
        System.out.println(sum);
    }
    
    public static long power(long a,long n,long mod) {
        long pow=1;
        for(long i=1;i<=n;i++) {
            pow=(pow*a)%mod;
        }
        return pow;
    } 
}
