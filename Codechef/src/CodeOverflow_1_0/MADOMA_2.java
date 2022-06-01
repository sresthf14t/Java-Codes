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
public class MADOMA_2 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long a=input.nextLong();
        long b=input.nextLong();
        long mod=(long)(Math.pow(10, 9) +7);
        long c1=a+b;
        long c2=(4*a)-b;
        n--;
        BigInteger N=new BigInteger(""+n);
        BigInteger A=new BigInteger(""+a);
        BigInteger B=new BigInteger(""+b);
        BigInteger C1=new BigInteger(""+c1);
        BigInteger C2=new BigInteger(""+c2);
        BigInteger MOD=new BigInteger(""+mod);
        BigInteger five=new BigInteger(""+5);
        BigInteger four=new BigInteger(""+4);
        BigInteger sum;
        BigInteger pow=four.pow(n);
        if(n%2==0) {
            sum=C1.multiply(pow);
            sum=sum.add(C2);
            sum=sum.divide(five);
        }
        else {
            sum=C1.multiply(pow);
            sum=sum.subtract(C2);
            sum=sum.divide(five);
        }
        sum=sum.mod(MOD);
        n++;
        if(n==1) {
            System.out.println(a);
        }
        else if(n==2) {
            System.out.println(b);
        }
        else {
          System.out.println(sum);  
        } 
    }
}
