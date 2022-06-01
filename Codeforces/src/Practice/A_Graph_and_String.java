/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*; 
public class A_Graph_and_String {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static char color[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        //No of nodes
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        color=new char[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            u--;
            v--;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        for(int i=0;i<n;i++) {
            if(adj_lst[i].size()==n-1) {
                color[i]='b';
            }
        }
        for(int i=0;i<n;i++) {
            if(color[i]=='b') {
                continue;
            }
            color[i]='a';
            for(int j=0;j<adj_lst[i].size();j++) {
                if(color[adj_lst[i].get(j)]=='b') {
                    continue;
                }
                color[adj_lst[i].get(j)]='a';
            }
            break;
        }
        for(int i=0;i<n;i++) {
            if(color[i]=='a' || color[i]=='b') {
                continue;
            }
            color[i]='c';
        }
        int a=0,b=0,c=0;
        for(int i=0;i<n;i++) {
            if(color[i]=='a') {
                a++;
            }
            else if(color[i]=='b') {
                b++;
            }
            else {
                c++;
            }
        }
        boolean is_pos=true;
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans.append(color[i]);
            if(color[i]=='a') {
                if(adj_lst[i].size()!=a+b-1) {
                    is_pos=false;
//                    break;
                }
            }
            else if(color[i]=='b') {
                if(adj_lst[i].size()!=a+b+c-1) {
                    is_pos=false;
//                    break;
                }
            }
            else {
                if(adj_lst[i].size()!=b+c-1) {
                    is_pos=false;
//                    break;
                }
            }
        }
        if(is_pos) {
            System.out.println("YES\n"+ans);
        }
        else {
            System.out.println("NO");
        }
    }
}
