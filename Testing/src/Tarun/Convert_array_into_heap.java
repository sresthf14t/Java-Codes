/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarun;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Convert_array_into_heap {
    
    //Left child=2*parent_indx+1;
    //Right child=2*parent_indx+2;
    //Parent=(child_indx-1)/2;
    static int cnt;
    static StringBuilder ans;
    static ArrayList<Integer> min_heap;
    public static void insert(int data) {
        min_heap.add(data);
        ins_heapify();
    }
    public static void ins_heapify() {
        int indx=min_heap.size()-1;
        while(min_heap.get(indx)<min_heap.get((indx-1)/2)) {
            int tmp=min_heap.get(indx);
            min_heap.set(indx, min_heap.get((indx-1)/2));
            min_heap.set((indx-1)/2,tmp);
            ans.append(indx+" "+((indx-1)/2)+"\n");
            cnt++;
            indx=(indx-1)/2;
        }
    }
    
    public static void main(String args[]) {
        min_heap=new ArrayList<>();
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        cnt=0;
        ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            insert(input.nextInt());
        }
        System.out.println(cnt+"\n"+ans);
    }
}
