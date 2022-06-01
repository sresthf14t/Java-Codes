/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedy;

/**
 *
 * @author User
 */
/*
Given weights and values of n items, we need to put these items in a 
knapsack of capacity W to get the maximum total value in the knapsack.
We can break these items and the take a part of it
*/
import java.util.*;
import java.io.*;
public class Fractional_Knapsack_Problem {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        //No of jobs
        int n=input.nextInt();
        int capacity=input.nextInt();
        int itemid[]=new int[n];
        int weight[]=new int[n];
        int value[]=new int[n];
        double ratio[]=new double[n];//Value per uniy weight
        
        for(int i=0;i<n;i++) {
            itemid[i]=i;
            weight[i]=input.nextInt();
            value[i]=input.nextInt();
            ratio[i]=(double)value[i]/(double)weight[i];
        }
        //Sort by profit
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(ratio[j]>ratio[i]) {
                    swap(itemid,i,j);
                    swap(weight,i,j);
                    swap(value,i,j);
                    swap_double(ratio,i,j);
                }
            }
        }
        double total_value=0.0;
        for(int i=0;i<n && capacity>0;i++) {
            if(capacity<weight[i]) {
                double taken_ratio=(double)capacity/(double)weight[i];
                total_value+=taken_ratio*value[i];
                capacity=0;
                System.out.println("Item taken="+itemid[i]+"\tratio taken="+taken_ratio);
            }
            else {
                total_value+=value[i];
                capacity-=weight[i];
                System.out.println("Item taken="+itemid[i]+"\tratio taken="+1);
            }
        }
        System.out.println("Total vale ue taken="+total_value);
    }
    public static void swap(int arr[],int indx1,int indx2) {
        int tmp=arr[indx1];
        arr[indx1]=arr[indx2];
        arr[indx2]=tmp;
    }
    public static void swap_double(double arr[],int indx1,int indx2) {
        double tmp=arr[indx1];
        arr[indx1]=arr[indx2];
        arr[indx2]=tmp;
    }
}
