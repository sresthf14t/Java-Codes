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
public class MADOMA_1 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long a=input.nextLong();
        long b=input.nextLong();
        long mod=(long)(Math.pow(10, 9) +7);
        long[] arr=new long[n];
        if(n==1) {
            System.out.println(a);
        }
        if(n==2) {
            System.out.println(b);
        }
        arr[0]=a;
        arr[1]=b;
        
        for(int i=2;i<arr.length;i++) {
            arr[i]=3*arr[i-1]+4*arr[i-2];
            arr[i]%=mod;
        }
        System.out.println(arr[arr.length-1]);
    }
}
