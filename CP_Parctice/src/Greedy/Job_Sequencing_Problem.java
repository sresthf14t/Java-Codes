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
Given an array of jobs where every job has a deadline and associated profit 
if the job is finished before the deadline. It is also given that every job 
takes single unit of time, so the minimum possible deadline for any job is 1.
How to maximize total profit if only one job can be scheduled at a time.
*/
import java.util.*;
import java.io.*;
public class Job_Sequencing_Problem {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        //No of jobs
        int n=input.nextInt();
        int jobid[]=new int[n];
        int deadline[]=new int[n];
        int profit[]=new int[n];
        
        for(int i=0;i<n;i++) {
            jobid[i]=i;
            deadline[i]=input.nextInt();
            profit[i]=input.nextInt();
        }
        //Sort by profit
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(profit[j]>profit[i]) {
                    swap(profit,i,j);
                    swap(deadline,i,j);
                    swap(jobid,i,j);
                }
            }
        }
        //Selecting optimal job by profit
        int max_deadline=-1;
        ArrayList<Integer> jobs_taken=new ArrayList<>();
        for(int i=0;i<n;i++) {
            max_deadline=Math.max(max_deadline, deadline[i]);
        }
        boolean slot_is_taken[]=new boolean[max_deadline]; 
        int total_profit=0;
        for(int i=0;i<n;i++) {
            int tmp_deadline=deadline[i]-1;
            while(tmp_deadline>=0 && slot_is_taken[tmp_deadline]) {
                tmp_deadline--;
            }
            if(tmp_deadline!=-1) {
                slot_is_taken[tmp_deadline]=true;
                total_profit+=profit[i];
                jobs_taken.add(jobid[i]);
            }
        }
        //Printing
        System.out.println("Total Profit="+total_profit+"\nJobs taken");
        for(int i=0;i<jobs_taken.size();i++) {
            System.out.print(jobs_taken.get(i)+" ");
        }
        System.out.println();
    }
    public static void swap(int arr[],int indx1,int indx2) {
        int tmp=arr[indx1];
        arr[indx1]=arr[indx2];
        arr[indx2]=tmp;
    }
}
