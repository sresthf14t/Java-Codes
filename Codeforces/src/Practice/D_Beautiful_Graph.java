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
public class D_Beautiful_Graph {
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

    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    //-1->odd  1->even
    static int vertex[],even,odd;
    public static void main(String args[]) throws IOException {
        Reader input=new Reader();
        //No of nodes
        StringBuilder fin_ans=new StringBuilder("");
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int m=input.nextInt();
//            if(m==0) {
//                long ans=1,mod=998244353L;
//                for(int i=0;i<n;i++) {
//                    ans*=3;
//                    ans%=mod;
//                }
//                System.out.println(ans);
//                continue;
//            }
            adj_lst=new ArrayList[n];
            vis=new boolean[n];
            vertex=new int[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            //No of edges
            for(int i=m;i>0;i--) {
                // input u & v
                int u=input.nextInt();
                int v=input.nextInt();
                u--;
                v--;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            long ans=1,mod=998244353L;
            for(int i=0;i<n;i++) {
                if(!vis[i]) {
                    if(adj_lst[i].size()==0) {
                        ans*=3;
                        ans%=mod;
                        continue;
                    }
                    even=0;
                    odd=1;
                    vertex[i]=-1;
                    mod_DFS(i);
                    long tmp_ans1=1,tmp_ans2=1;
                    for(int j=0;j<even;j++) {
                        tmp_ans1*=2;
                        tmp_ans1%=mod;
                    }
                    for(int j=0;j<odd;j++) {
                        tmp_ans2*=2;
                        tmp_ans2%=mod;
                    }
                    ans*=((tmp_ans1+tmp_ans2)%mod);
                    ans%=mod;
                }
            }
            if(!check()) {
                fin_ans.append(0+"\n");
                continue;
            }
            fin_ans.append(ans+"\n");
        }
        System.out.println(fin_ans);
    }
    
    static boolean check() {
        //-1->odd  1->even
        for(int i=0;i<adj_lst.length;i++) {
            for(int j=0;j<adj_lst[i].size();j++) {
                if(vertex[i]==vertex[adj_lst[i].get(j)]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void mod_DFS(int root) {
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                if(vertex[root]==-1) {
                    vertex[adj_lst[root].get(i)]=1;
                    even++;
                }
                else {
                    vertex[adj_lst[root].get(i)]=-1;
                    odd++;
                }
                mod_DFS(adj_lst[root].get(i));
            }
        }
    }
}
