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
/*You are given n activities with their start and finish times. Select 
the maximum number of activities that can be performed by a single person,
assuming that a person can only work on a single activity at a time.*/
import java.util.*;
import java.io.*;
public class Activity_Selection_Problem {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        //No of activities
        int n=input.nextInt();
        int strt_time[]=new int[n];
        int end_time[]=new int[n];
        //Start time and end time
        for(int i=0;i<n;i++) {
            strt_time[i]=input.nextInt();
            end_time[i]=input.nextInt();
        }
        //Sort
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(end_time[j]<end_time[i]) {
                    int tmp_strt=strt_time[j];
                    int tmp_end=end_time[j];
                    strt_time[j]=strt_time[i];
                    end_time[j]=strt_time[i];
                    strt_time[i]=tmp_strt;
                    end_time[i]=tmp_end;
                }
            }
        }
        int count=1,prev=0;
        for(int i=1;i<n;i++) {
            if(strt_time[i]>=end_time[prev]) {
                count++;
                prev=i;
            }
        }
        System.out.println(count);
    }
}
