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
public class CHFDORA {
     public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int test=0;test<t;test++) {
            int n=input.nextInt();
            int m=input.nextInt();
            int arr[][]=new int[n][m];
            for(int i=0;i<arr.length;i++) {
                for(int j=0;j<arr[i].length;j++) {
                    arr[i][j]=input.nextInt();
                }
            }
            
            System.out.println(no_of_pairs(arr,n,m));
        }
     }
     
     public static int no_of_pairs(int arr[][],int n,int m) {
         
         int n_pairs=0;
         
         for(int i=1;i<n-1;i++) {
             for(int j=1;j<m-1;j++) {
                 
                 int len1=Math.min(i, j);
                 int len2=Math.min(n-i-1, m-j-1);
                 int len=Math.min(len1,len2);
                 
                 for(int k=1;k<=len;k++) {
                     
                     //Checking
                     if((arr[i+k][j]==arr[i-k][j]) && (arr[i][j+k]==arr[i][j-k])) {
                         n_pairs++;
                     }
                     else {
                         break;
                     }
                 }
                 
             }
         }
         
         return n_pairs+(n*m);
     }
}
