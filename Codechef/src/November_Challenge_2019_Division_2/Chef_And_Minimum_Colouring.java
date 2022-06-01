/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package November_Challenge_2019_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class Chef_And_Minimum_Colouring {
    static long find_num_a,find_num_b,find_num_bool;
    public static void main(String args[]) {
        try {
            Scanner input=new Scanner(System.in);
            int t=0;
            if(input.hasNextInt()) {
                t=input.nextInt();
            }
            for(int k=1;k<=t;k++) {
                int n=0;
                if(input.hasNextInt()) {
                    n=input.nextInt();
                }
                int m=0;
                if(input.hasNextInt()) {
                    m=input.nextInt();
                }
                long arr[][]=new long[n/m][m];
                for(int i=0;i<arr.length;i++) {
                    for(int j=0;j<arr[i].length;j++) {
                        if(input.hasNextLong()) {
                            arr[i][j]=input.nextLong();
                        } 
                    }
                }
                diff(arr);
            }
        }
        catch(Exception e) {
            
        }  
    }
    public static void diff(long arr[][]) {
        long min,max,a,b;
        a=arr[0][0];
        b=arr[0][1];
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length;j++) {
                if(Math.abs(arr[i][0]-arr[j][1])<Math.abs(a-b)) {
                    a=arr[i][0];
                    b=arr[j][1];
                }
            }
        }
        if(a>b) {
            min=b;
            max=a;
        }
        else {
            min=a;
            max=b;
        }
        long arr_trans[][]=transpose(arr);
        for(int i=2;i<arr_trans.length;i++) {
            find_num(arr_trans[i],min,max);
            if(find_num_bool==-1) {
                if(find_num_a==-1) {
                    max=find_num_b;
                }
                if(find_num_b==-1) {
                    min=find_num_a;
                }
            }
        }
        long pri=max-min;
        System.out.println(pri);
    }
    public static long[][] transpose(long arr[][]) {
        long t[][]=new long[arr[0].length][arr.length];
	for(int i=0;i<arr[0].length;i++) {
            for(int j=0;j<arr.length;j++ ) {
                t[i][j]=arr[j][i];
            }
	}
        return t;
    }
    public static void find_num(long arr[],long min,long max) {
        boolean bool=false;
        long min_dist=-1,max_dist=-1,a=-1,b=-1;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]>=min&&arr[i]<=max) {
                bool=true;
                break;
            }
            if(min_dist==-1||(min-arr[i])<min_dist) {
                a=arr[i];
                min_dist=min-arr[i];
            }
            if(max_dist==-1||(arr[i]-max)<max_dist) {
                b=arr[i];
                max_dist=arr[i]-max;
            }
        }
        if(bool) {
            find_num_a=-1;
            find_num_b=-1;
            find_num_bool=1;
        }
        else {
            if(max_dist>min_dist) {
                find_num_a=-1;
                find_num_b=b;
                find_num_bool=-1;
            }
            if(max_dist<min_dist) {
                find_num_a=a;
                find_num_b=-1;
                find_num_bool=-1;
            }
        }
    }
    
}
