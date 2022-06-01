/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICPC_Challenge_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A1 {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int color[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(new File("C:\\Users\\User\\Desktop\\ICPC Challenge 2020 Practice\\Main\\icpc-challenge-2020-tests(1)\\a2.in"));
        int n=1000000;
        adj_lst=new ArrayList[1000000];
        vis=new boolean[adj_lst.length];
        color=new int[adj_lst.length];
        while(input.hasNext()) {
            int u=input.nextInt();
            int v=input.nextInt();
            if(adj_lst[u]==null) {
                adj_lst[u]=new ArrayList<>();
            }
            if(adj_lst[v]==null) {
                adj_lst[v]=new ArrayList<>();
            }
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        System.out.println("Input Done");
        for(int i=0;i<n;i++) {
            if(adj_lst[i]!=null && !vis[i]) {
                DFS(i);
            }
        }
        ArrayList<Integer> freq[]=new ArrayList[n+1];
        for(int i=0;i<n;i++) {
            freq[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++) {
            freq[color[i]].add(i);
//            System.out.println(color[i]);
        }
        StringBuilder ans= new StringBuilder("");
        for(int i=1;i<n+1;i++) {
            if(freq[i]==null || freq[i].size()==0) {
                continue;
            }
            for(int j=0;j<freq[i].size();j++) {
                ans.append(freq[i].get(j)+" ");
            }
            ans.append("\n");
        }
        System.out.println("Writing To File");
        write(ans);
    }
    
    
    public static void DFS(int root) {
        Stack<Integer> stck=new Stack<>();
        stck.push(root);
        while(!stck.isEmpty()) {
            root=stck.pop();
            if(!vis[root]) {
//                System.out.println(root);
                vis[root]=true;
            }
            Set<Integer> used_color=new HashSet<>();
            for(int i=0;i<adj_lst[root].size();i++) {
                if(!used_color.contains(color[adj_lst[root].get(i)])) {
                    used_color.add(color[adj_lst[root].get(i)]);
                }
            }
            int least_number_unused_color=-1;
            for(int i=1;;i++) {
                if(!used_color.contains(i)) {
                    least_number_unused_color=i;
                    break;
                }
            }
            color[root]=least_number_unused_color;
            
            for(int i=0;i<adj_lst[root].size();i++) {
                if(!vis[adj_lst[root].get(i)]) {
                    stck.push(adj_lst[root].get(i));
                }
            }
        }
    }
    
    public static void write(StringBuilder ans) {
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\User\\Desktop\\ICPC Challenge 2020 Practice\\Main\\icpc-challenge-2020-tests(1)\\a2_ans.txt",false));
        }
        catch(FileNotFoundException e) {
            
        }
        outputstream.println(ans);
        outputstream.close();
    }
}
