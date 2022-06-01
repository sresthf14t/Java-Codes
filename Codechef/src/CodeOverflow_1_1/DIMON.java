/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeOverflow_1_1;

/**
 *
 * @author User
 */
import java.util.Scanner;
import java.math.BigInteger;
public class DIMON {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        long m=input.nextLong();
        long k=input.nextLong();
        long sum=0;
        int mod=(int)(Math.pow(10, 9)+7);
        for(long i=1;i<=k;i++) {
            sum+=input.nextLong();
        }
        long diff=m-sum;
        if(diff<0) {
            System.out.println(0);
        }
        else {
            BigInteger K=new BigInteger(""+k);
            BigInteger MOD=new BigInteger(""+mod);
            BigInteger ans=K.pow((int)diff);
            ans=ans.mod(MOD);
            System.out.println(ans);
        } 
    }
}
