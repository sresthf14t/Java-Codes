/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ozon_Tech_Challenge_2020;

/**
 *
 * @author User
 */
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class E {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        int sum=0;
        for(int i=2;i<=n;i++) {
            sum+=(i-1)/2;
//            System.out.println((i-1)/2);
        }
        if(m>sum) {
            System.out.println(-1);
            System.exit(0);
        }
        ArrayList<Integer> nums=new ArrayList<Integer>();
        StringBuilder str=new StringBuilder("");
        sum=0;
        int i=2;
        for(;;i++) {
            sum+=(i-1)/2;
            if(sum==m) {
                break;
            }
            if(sum>m) {
                sum-=(i-1)/2;
                i--;
                break;
            }
        }
        for(int j=1;j<=i;j++) {
            nums.add(j);
        }
        System.out.println(nums.size());
        if(sum==m) {
            for(i=0;i<nums.size();i++) {
                System.out.print(nums.get(i)+" ");
            }
            System.exit(0);
        }
        if(sum<m) {
            int tmp=2*i-(2*(m-sum));
            nums.add(tmp);
        }
        int rem=n-nums.size();
        boolean arr[]=new boolean[100000000];
        i=nums.get(nums.size()-1)+nums.get(nums.size()-2)+1;
        arr[nums.get(nums.size()-1)+nums.get(nums.size()-2)]=true;
        for(int count=0;;) {
            while(!arr[i]) {
                nums.add(i);
                count++;
                if(count==rem) {
                    break;
                }
            }
            if(count==m) {
                break;
            }
            i=nums.get(nums.size()-1)+nums.get(nums.size()-2)+1;
            arr[nums.get(nums.size()-1)+nums.get(nums.size()-2)]=true;
        }
        for(i=0;i<nums.size();i++) {
            System.out.print(nums.get(i)+" ");
        }
    }
}
