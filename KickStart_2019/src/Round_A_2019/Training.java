/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2019;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.Scanner;
public class Training {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int p=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            Arrays.sort(arr);
            int h_min=2000000000,sum=0;
            for(int i=0;i<p-1;i++) {
                sum+=arr[i];
            }
            for(int i=p-1;i<n;i++) {
                sum+=arr[i];
                if(((p*arr[i])-sum)<h_min) {
                    h_min=(p*arr[i])-sum;
                }
                sum-=arr[i-p+1];
            }
            System.out.println("Case #"+t+": "+h_min);
        }
    }
}
