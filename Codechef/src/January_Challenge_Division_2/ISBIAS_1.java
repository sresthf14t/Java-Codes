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
public class ISBIAS_1 {
    
    static int max_strt_indx[]=new int[100000];
    static int max_end_indx[]=new int[100000];
    static int min_strt_indx[]=new int[100000];
    static int min_end_indx[]=new int[100000];
    static int max_indx_pointer=-1,min_indx_pointer=-1;
    
     public static void main(String args[]) {
         
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        int q=input.nextInt();
        
        for(int i=0;i<arr.length;i++) {
            arr[i]=input.nextInt();
        }
        
        maximal_increasing_subsequence(arr,0,arr.length-1);
        maximal_decreasing_subsequence(arr,0,arr.length-1);
        
//        System.out.println();
//        for(int i=0;i<=max_indx_pointer;i++) {
//            for(int j=max_strt_indx[i];j<=max_end_indx[i];j++) {
//                System.out.print(arr[j]+"  ");
//            }
//            System.out.println();
//        }
//        
//        System.out.println();
//        for(int i=0;i<=min_indx_pointer;i++) {
//            for(int j=min_strt_indx[i];j<=min_end_indx[i];j++) {
//                System.out.print(arr[j]+"  ");
//            }
//            System.out.println();
//        }
        
        for(int test=0;test<q;test++) {
            int l=input.nextInt();
            int r=input.nextInt();
            l--;r--;
            if(check(arr,l,r)) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
         
     }
     
     public static int maximal_increasing_subsequence(int[] arr, int l, int r) {
        int count=0;
        boolean strt=false,end=false;
        int strt_indx=-1,end_indx=-1;
        for(int i=l;i<r;i++) {
            if(arr[i]<arr[i+1]) {
                if(!strt) {
                    strt=true; 
                    strt_indx=i;
                } 
            }
            else {
                if(strt) {
                    end=true;
                    end_indx=i;
                }
            }
            if(strt && end) {
                count++;
                strt=end=false;
                max_indx_pointer++;
                max_strt_indx[max_indx_pointer]=strt_indx;
                max_end_indx[max_indx_pointer]=end_indx;
            }
            //System.out.println(strt+"  "+end);
        }
        if(strt) {
            count++;
            end_indx=r;
            max_indx_pointer++;
            max_strt_indx[max_indx_pointer]=strt_indx;
            max_end_indx[max_indx_pointer]=end_indx;
        }
        return count;
    }
    
    public static int maximal_decreasing_subsequence(int[] arr, int l, int r) {
        int count=0;
        boolean strt=false,end=false;
        int strt_indx=-1,end_indx=-1;
        for(int i=l;i<r;i++) {
            if(arr[i]>arr[i+1]) {
                if(!strt) {
                    strt=true;
                       strt_indx=i;
                } 
            }
            else {
                if(strt) {
                    end=true;
                    end_indx=i;
                }
            }
            if(strt && end) {
                count++;
                strt=end=false;
                min_indx_pointer++;
                min_strt_indx[min_indx_pointer]=strt_indx;
                min_end_indx[min_indx_pointer]=end_indx;
            }

        }
        if(strt) {
            count++;
            end_indx=r;
            min_indx_pointer++;
            min_strt_indx[min_indx_pointer]=strt_indx;
            min_end_indx[min_indx_pointer]=end_indx;
        }
        return count;   
    } 
     
    
    public static boolean check(int arr[],int l,int r) {
        int count_inc=0,count_dec=0;
        
        for(int i=0;i<=max_indx_pointer;i++) {
            if(max_strt_indx[i]==l && max_end_indx[i]==r) {
                count_inc++;
            }
            else if(max_strt_indx[i]==l && max_end_indx[i]<r) {
                if(arr[max_end_indx[i]]>arr[max_end_indx[i]+1]) {
                    count_inc++;
                }
            }
            else if(max_strt_indx[i]>l && max_end_indx[i]==r) {
                if(arr[max_strt_indx[i]-1]>arr[max_strt_indx[i]]) {
                    count_inc++;
                }
            }
            else if((max_strt_indx[i]>l && max_end_indx[i]<r)) {
                if( (arr[max_end_indx[i]]>arr[max_end_indx[i]+1]) && (arr[max_strt_indx[i]-1]>arr[max_strt_indx[i]])) {
                    count_inc++;
                }
            }
            else if( (l>=max_strt_indx[i]) && (r<=max_end_indx[i]) ) {
                count_inc++;
            }
        }
        
        for(int i=0;i<=min_indx_pointer;i++) {
            if(min_strt_indx[i]==l && min_end_indx[i]==r) {
                count_dec++;
            }
            else if(min_strt_indx[i]==l && min_end_indx[i]<r) {
                if(arr[min_end_indx[i]]<arr[min_end_indx[i]+1]) {
                    count_dec++;
                }
            }
            else if(min_strt_indx[i]>l && min_end_indx[i]==r) {
                if(arr[min_strt_indx[i]-1]<arr[min_strt_indx[i]]) {
                    count_dec++;
                }
            }
            else if((min_strt_indx[i]>l && min_end_indx[i]<r)) {
                if( (arr[min_end_indx[i]]<arr[min_end_indx[i]+1]) && (arr[min_strt_indx[i]-1]<arr[min_strt_indx[i]])) {
                    count_dec++;
                }
            }
            else if( (min_strt_indx[i]<=l) && (min_end_indx[i]>=r) ) {
                count_dec++;
            }
        }
        
        if(count_inc==count_dec) {
            return true;
        }
        return false;
    }
     
}
