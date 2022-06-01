/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package January_Challenge_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class CHEFPSA {
     public static void main(String args[]) {
         Scanner input=new Scanner(System.in);
         int t=input.nextInt();
         for(int test=1;test<=t;test++) {
             int n=input.nextInt();
             int arr[]=new int[2*n];
             
             for(int i=0;i<arr.length;i++) {
                 arr[i]=input.nextInt();
             }
             
             if(n==1) {
                 if(arr[0]==arr[1]) {
                     System.out.println(1);
                 }
                 else {
                     System.out.println(0);
                 }
                 continue;
             }
             int sum_of_all=sum_of_arr(arr)/(n+1);
             arr=arr_edit(arr,sum_of_all);
             int count=0;
             for(int i=0;i<arr.length;i++) {
                 int diff=sum_of_all-arr[i];
                 for(int j=0;j<arr.length;j++) {
                     if(j!=i && diff==arr[j] && arr[j]<=1000000000) {
                         count++;
                     }
                 }
             }
             System.out.println(count);
         }
     }
     
     public static int sum_of_arr(int arr[]) {
         int sum=0;
         for(int i=0;i<arr.length;i++) {
             sum+=arr[i];
         }
         return sum;
     }
     
     public static int[] arr_edit(int arr[],int sum_of_all) {
         int count=0;
         for(int i=0;i<arr.length;i++) {
             if(arr[i]==sum_of_all) {
                 arr[i]=1500000000;
                 count++;
             }
             if(count==2) {
                 break;
             }
         }
         return arr;
         
     }
}
