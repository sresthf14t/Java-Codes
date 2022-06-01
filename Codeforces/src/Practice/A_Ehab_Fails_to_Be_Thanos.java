/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A_Ehab_Fails_to_Be_Thanos {
    public static void main(String rag[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[2*n];
        for(int i=0;i<2*n;i++) {
            arr[i]=input.nextInt();
        }
        Arrays.sort(arr);
        int sum1=0,sum2=0;
        for(int i=0;i<n;i++) {
            sum1+=arr[i];
        }
        for(int i=n;i<2*n;i++) {
            sum2+=arr[i];
        }
        if(sum1==sum2) {
            System.out.println(-1);
        }
        else {
            for(int i=0;i<2*n;i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
    }
}
