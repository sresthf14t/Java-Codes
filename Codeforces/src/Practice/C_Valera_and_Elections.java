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
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Scanner; 
import java.util.StringTokenizer;
public class C_Valera_and_Elections {
    
    static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
    
    
    static ArrayList<Integer> adj_lst[],elect;
    static HashMap<Integer, Integer> map; 
    static ArrayList<Boolean> is_damage[];
    static boolean vis[];
    public static void main(String args[]) throws IOException {
        Reader input=new Reader();
        //No of nodes
        int n=input.nextInt();
        adj_lst=new ArrayList[n];
        is_damage=new ArrayList[n];
        vis=new boolean[n];
        map = new HashMap<>();
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            is_damage[i]=new ArrayList<Boolean>();
        }
        elect=new ArrayList<Integer>();
        //No of edges
        for(int i=0;i<n-1;i++) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            int damage=input.nextInt();
            u--;
            v--;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            if(damage==1) {
                is_damage[u].add(Boolean.FALSE);
                is_damage[v].add(Boolean.FALSE);
            }
            else {
                is_damage[u].add(Boolean.TRUE);
                is_damage[v].add(Boolean.TRUE);
            }
        }
        mod_DFS(0,-1,-1);
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        System.out.println(elect.size());
        for(int i=0;i<elect.size();i++) {
            System.out.print((elect.get(i)+1)+" ");
        }
        System.out.println();
    }
    
    public static void mod_DFS(int root,int parent,int path_node) {
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            int nxt_path_node=path_node;
            if(!vis[adj_lst[root].get(i)]) {
                if(is_damage[root].get(i).booleanValue()) {
                    if(map.containsKey(new Integer(path_node))) {
                        elect.remove(map.get(path_node).intValue());
                        map.remove(new Integer(path_node));
                    }
                    elect.add(adj_lst[root].get(i));
                    map.put(adj_lst[root].get(i), elect.size()-1);
                    nxt_path_node=adj_lst[root].get(i);
                }
                mod_DFS(adj_lst[root].get(i),root,nxt_path_node);
            }
        }
    }
}
