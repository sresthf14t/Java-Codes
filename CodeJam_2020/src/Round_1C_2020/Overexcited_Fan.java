/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_1C_2020;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*; 
public class Overexcited_Fan {
    static ArrayList<Integer> adj_lst[];
    static int indx[][],depth[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int x=input.nextInt();
        int y=input.nextInt();
        String str=input.next();
        adj_lst=new ArrayList[4000000];
        int cnt=0;
        indx=new int[2000][2000];
        for(int i=0;i<1000;i++) {
            for(int j=0;j<1000;j++) {
                indx[i][j]=cnt;
                cnt++;
                adj_lst[indx[i][j]]=new ArrayList<>();
            }
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        create_graph();
        BFS(indx[1000][1000]);
        int min=Integer.MAX_VALUE;
        x+=1000;
        y+=1000;
        min=Math.min(min,depth[indx[x][y]]);
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='N') {
                y++;
            }
            if(str.charAt(i)=='S') {
                y--;
            }
            if(str.charAt(i)=='E') {
                x++;
            }
            if(str.charAt(i)=='W') {
                x--;
            }
            min=Math.min(min,depth[indx[x][y]]);
        }
        System.out.println(min);
    }
    public static void create_graph() {
        for(int i=0;i<2000;i++) {
            for(int j=0;j<2000;j++) {
                if(i!=0) {
                    adj_lst[indx[i][j]].add(indx[i-1][j]);
                }
                if(i!=1999) {
                    adj_lst[indx[i][j]].add(indx[i+1][j]);
                }
                if(j!=0) {
                    adj_lst[indx[i][j]].add(indx[i][j-1]);
                }
                if(j!=1999) {
                    adj_lst[indx[i][j]].add(indx[i][j+1]);
                }
            }
        }
    }
    
    public static void BFS(int source) {
        boolean vis[]=new boolean[adj_lst.length];
        depth=new int[adj_lst.length];
        Queue<Integer> que = new LinkedList<>();
        que.add(source);
        depth[source]=0;
        vis[source]=true;
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                    depth[adj_lst[que.peek()].get(i)]=depth[que.peek()]+1;
                }
            }
            que.poll();
        }
    }
}
