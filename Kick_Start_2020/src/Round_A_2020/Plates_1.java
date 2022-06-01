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
public class Plates_1 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int k=input.nextInt();
            int p=input.nextInt();
            ArrayList<Integer> arr[]=new ArrayList[n];
            for(int i=0;i<n;i++) {
                arr[i]=new ArrayList<>();
                for(int j=0;j<k;j++) {
                    arr[i].add(input.nextInt());
                }
            }
            int tot_sum=0;
            for(;p>0;p--) {
                int max_sum=0,indx=-1;
                for(int i=0;i<n;i++) {
                    int sum=0;
                    for(int j=0;j<arr[i].size() && j<p;j++) {
                        sum+=arr[i].get(j);
                    }
                    if(sum>max_sum) {
                        max_sum=sum;
                        indx=i;
                    }
                    if(sum!=0 && sum==max_sum) {
                        if(arr[i].get(0)>arr[indx].get(0)) {
                            indx=i;
                        }
                    }
                }
                tot_sum+=arr[indx].get(0);
                arr[indx].remove(0);
                
//                for(int i=0;i<n;i++) {
//                    System.out.print(i+"->");
//                    for(int j=0;j<arr[i].size();j++) {
//                        System.out.print(arr[i].get(j)+" ");
//                    }
//                    System.out.println();
//                }
            }
            System.out.println("Case #"+t+": "+tot_sum);
        }
    }
}
