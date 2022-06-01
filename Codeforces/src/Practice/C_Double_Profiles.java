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
public class C_Double_Profiles {
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.nextInt()-1;
            int v=input.nextInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        System.out.println(solve(n));
    }
    public static int solve(int n) {
        boolean is_double[]=new boolean[n];
        for(int i=1;i<n;i++) {
            is_double[i]=true;
        }
        if(adj_lst[0].size()==1) {
            is_double[adj_lst[0].get(0)]=false;
        }
        for(int i=0;i<n;i++) {
            if(is_double[i]) {
                continue;
            }
            for(int j=0;j<n;j++) {
                if(j==i) {
                    continue;
                }
                for(int k=j+1;k<n;k++) {
                    if(k==i) {
                        continue;
                    }
                    if(adj_lst[j].contains(i) && adj_lst[k].contains(i)) {
                        is_double[i]=true;
                        break;
                    }
                    if(!adj_lst[j].contains(i) && !adj_lst[k].contains(i)) {
                        is_double[i]=true;
                        break;
                    }
                }
                if(is_double[i]) {
                    break;
                }
            }
        }
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(is_double[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}
