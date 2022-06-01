/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unacademy_Teaching_Assistant_Hiring_Contest;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class PATWALL {
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
    public static void main(String args[]) throws IOException {
        Reader input=new Reader();
        int n=input.nextInt();
        long s=input.nextLong();
        long arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextLong();
        }
        long prefix[]=new long[n];
        long prefix_indx[]=new long[n];
        prefix[0]=arr[0];
        prefix_indx[0]=1;
        for(int i=1;i<n;i++) {
            prefix[i]=prefix[i-1]+arr[i];
            prefix_indx[i]=prefix_indx[i-1]+(i+1);
        }
        System.out.println(solve(n,s,arr,prefix,prefix_indx));
    }
    public static long solve(int n,long s,long arr[],long prefix[],long prefix_indx[]) {
        int max=0;
        for(int i=0;i<n;i++) {
            int ans=-1,strt=i,end=n-1;
            while(strt<=end) {
                int mid=(strt+end)/2;
                long total=get(i,mid,prefix)+(mid-i+1)*get(i,mid,prefix_indx);
                if(total<=s) {
                    ans=mid;
                    strt=mid+1;
                }
                else {
                    end=mid-1;
                }
            }
            if(ans==-1) {
                continue;
            }
            max=Math.max(max,ans-i+1);
        }
        return max;
    }
    public static long get(int l,int r,long arr[]) {
        return arr[r]-(l==0?0:arr[l-1]);
    }
}
