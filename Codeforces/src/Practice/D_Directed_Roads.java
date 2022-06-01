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
public class D_Directed_Roads {
    static ArrayList<Integer> adj_lst[],cycles;
    static int parent[],color[],n_nodes;
    static long power[],mod=1000000007L;
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        power=new long[200001];
        power();
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<n;i++) {
            // input u & v
            int u=input.nextInt()-1;
            int v=i;
//            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        color=new int[n];
        parent=new int[n];
        for(int i=0;i<n;i++) {
            parent[i]=-1;
        }
        
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        long ans=1;
        for(int i=0;i<n;i++) {
            if(color[i]==0) {
                n_nodes=0;
                cycles=new ArrayList<>();
                DFS(i,-1);
                int tot=cycles.size();
                n_nodes-=tot;
                ans*=power[n_nodes];
                ans%=mod;
                if(tot!=0) {
                    ans*=(power[tot]-2);
                    ans%=mod;
                }
            }
        }
        System.out.println(ans);
    }
    public static void DFS(int root,int par) {
        if(color[root]==2) {
            return;
        }
        if(color[root]==1) {
            int r=par;
            while(r!=root) {
                cycles.add(r);
                color[r]=2;
                r=parent[r];
            }
            cycles.add(root);
            return;
        }
        n_nodes++;
        color[root]=1;
        parent[root]=par;
        for(int i=0;i<adj_lst[root].size();i++) {
//            if(adj_lst[root].get(i)==par) {
//                continue;
//            }
            DFS(adj_lst[root].get(i),root);
        }
        color[root]=2;
    }
    static void power() {
        power[0]=1L;
        for(int i=1;i<power.length;i++) {
            power[i]=(power[i-1]*2)%mod;
        }
    }
}
