/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.util.Scanner;
public class Allocation {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int b=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            Arrays.sort(arr);
            int sum=0,count=0;
            for(int i=0;i<n;i++) {
                sum+=arr[i];
                count++;
                if(sum==b) {
                    break;
                }
                else if(sum>b) {
                    count--;
                    break;
                }
            }
            System.out.println("Case #"+t+": "+count);
        }
        
    }
}
