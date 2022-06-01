/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_661_Div_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class E1 {
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
    static ArrayList<Integer> weight[];
    static ArrayList<Long> count;
    static ArrayList<Long> amount;
    public static void main(String args[]) throws IOException {
        
        PriorityQueue<Integer> pq= new PriorityQueue<Integer>(); 
        pq.add(10);
        pq.add(10);
        System.out.println(pq.size());
        
        Reader input=new Reader(); 
        StringBuilder ans=new StringBuilder("");
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            long s=input.nextLong();
            adj_lst=new ArrayList[n];
            weight=new ArrayList[n];
            count=new ArrayList<>();
            amount=new ArrayList<>();
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
                weight[i]=new ArrayList<>();
            }
            for(int i=0;i<n-1;i++) {
                int u=input.nextInt()-1;
                int v=input.nextInt()-1;
                int wei=input.nextInt();
                adj_lst[u].add(v);
                adj_lst[v].add(u);
                weight[u].add(wei);
                weight[v].add(wei);
            }
            DFS(0,-1);
            ans.append(solve(s)+"\n");
        }
        System.out.println(ans);
    }
    public static int solve(long s) {
        TreeMap<Long,Stack<Integer>> map=new TreeMap<>();
        long sum=0;
        for(int i=0;i<count.size();i++) {
            long wei=amount.get(i);
            long mul=count.get(i);
            sum+=(wei*mul);
            long diff=(wei*mul)-((wei/2)*mul);
            if(map.containsKey(diff)) {
                Stack<Integer> arrli=map.get(diff);
                arrli.push(i);
            }
            else {
                Stack<Integer> arrli=new Stack<>();
                arrli.push(i);
                map.put(diff, arrli);
            }
        }
//        System.out.println(sum);
        int cnt=0;
        while(sum>s) {
//            System.out.println(sum);
            cnt++;
            long key=map.lastKey();
            Stack<Integer> arrli=map.get(key);
            int indx=arrli.pop();
            if(arrli.size()==0) {
                map.remove(key);
            }
            sum-=key;
            amount.set(indx, amount.get(indx)/2);
            long wei=amount.get(indx);
            long mul=count.get(indx);
            long diff=(wei*mul)-((wei/2)*mul);
            if(map.containsKey(diff)) {
                Stack<Integer> tmp=map.get(diff);
                tmp.add(indx);
            }
            else {
                Stack<Integer> tmp=new Stack<>();
                tmp.add(indx);
                map.put(diff, tmp);
            }
        }
        return cnt;
    }
    public static int DFS(int root,int prev) {
        if(adj_lst[root].size()==1 && adj_lst[root].get(0)==prev) {
            return 1;
        }
        int cnt=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==prev) {
                continue;
            }
            int tmp=DFS(adj_lst[root].get(i),root);
            cnt+=tmp;
            count.add((long)tmp);
            amount.add((long)weight[root].get(i));
        }
        return cnt;
    }
}
