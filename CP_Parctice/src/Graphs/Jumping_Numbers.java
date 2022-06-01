/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

/**
 *
 * @author User
 */

//A number whose adjecent digits differ by 1 is called a jumpibg number
//Print all the jumping numbers<=n

import java.io.*; 
import java.util.*; 
public class Jumping_Numbers {
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        adj_lst=new ArrayList[10];
        long n=input.nextLong();
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<>();
            if(i==0) {
                adj_lst[i].add(1);
            }
            else if(i==9) {
                adj_lst[i].add(8);
            }
            else {
                adj_lst[i].add(i-1);
                adj_lst[i].add(i+1);
            }
        }
        System.out.println(0);
        for(int i=1;i<10;i++) {
            mod_DFS(i,n,0);
        }
    }
    public static void mod_DFS(int root,long lim,long num) {
        num=(10*num+root);
        if(num<=lim) {
            System.out.println(num);
        }
        else {
            return;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            mod_DFS(adj_lst[root].get(i),lim,num);
        }
    }
}
