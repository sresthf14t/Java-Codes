/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODEFIESTA;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class SERIESTO {
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
    static long pow[];
    public static void main(String args[]) throws IOException {
        Reader input = new Reader();
        pow=new long[60];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            long a=input.nextLong();
            long b=input.nextLong();
            long n=input.nextLong();
            long tmp=0;
            int indx=60;
            for(int i=pow.length-1;i>=0;i--) {
                if((pow[i]&a)!=0) {
                    indx=Math.min(indx, i);
                    break;
                }
            }
            for(int i=pow.length-1;i>=0;i--) {
                if((pow[i]&b)!=0) {
                    indx=Math.min(indx, i);
                    break;
                }
            }
            for(int i=indx;i>=0;i--) {
                tmp+=pow[i];
            }
            if(n==1) {
                ans.append(a);
                continue;
            }
            if(n==2) {
                ans.append(b);
                continue;
            }
            n-=3;
            if(n==0) {
                ans.append(Math.max(a^b,xnor(a,b)));
            }
            else if(n%3==0) {
                ans.append(Math.max(a^b,xnor(xnor(a,b),tmp)));
            }
            else if(n%3==1) {
                ans.append(Math.max(a,xnor(a,tmp)));
            }
            else {
                ans.append(Math.max(b,xnor(b,tmp)));
            }
            ans.append("\n");
         }
        System.out.println(ans);
    }
    public static long xnor(long a,long b) {
        int indx=0;
        for(int i=pow.length-1;i>=0;i--) {
            if((pow[i]&a)!=0) {
                indx=Math.max(indx,i);
                break;
            }
            if((pow[i]&b)!=0) {
                indx=Math.max(indx,i);
                break;
            }
        }
        long ans=0;
        for(int i=indx;i>=0;i--) {
            if((pow[i]&a)==0 && (pow[i]&b)==0) {
                ans+=pow[i];
            }
            else if((pow[i]&a)!=0 && (pow[i]&b)!=0) {
                ans+=pow[i];
            }
        }
        return ans;
    }
}
