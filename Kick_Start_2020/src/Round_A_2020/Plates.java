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
public class Plates {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int k=input.nextInt();
            int p=input.nextInt();
            ArrayList<Integer> arr[]=new ArrayList[n];
            int sum_arr[]=new int[n];
            int max_buty=0;
            for(int i=0;i<n;i++) {
                arr[i]=new ArrayList<>();
                for(int j=0;j<k;j++) {
                    arr[i].add(input.nextInt());
                }
            }
            for(;p>0;p--) {
                sum_arr=new int[n];
                for(int i=0;i<n;i++) {
                    for(int j=0;j<arr[i].size() && j<p;j++) {
                        sum_arr[i]+=arr[i].get(j);
                    }
                    System.out.println(i+" "+sum_arr[i]);
                }
                int indx=get_max_indx(sum_arr);
                max_buty+=arr[indx].get(0);
                arr[indx].remove(0);
            }
            System.out.println("Case #"+t+": "+max_buty);
        }
    }
    public static int get_max_indx(int arr[]) {
        int max=0,indx=-1;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]>max) {
                max=arr[i];
                indx=i;
            }
        }
        return indx;
    }
}
