/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_619_Div2;

/**
 *
 * @author User
 */
import java.util.*; 
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays;
import java.util.Scanner; 
import java.util.StringTokenizer;
public class B {
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
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            long arr[]=new long[n];
            long arr_cpy[]=new long[n];
            long sum=0,count=0;
            boolean all_neg=true;
            for(int i=0;i<n;i++) {
                arr[i]=input.nextLong();
                arr_cpy[i]=arr[i];
                if(arr[i]!=-1) {
                    all_neg=false;
                }
            }
            if(all_neg) {
                System.out.println("0 0");
                continue;
            }
            for(int i=0;i<n;i++) {
                if(arr[i]!=-1 && i!=0 && arr[i-1]==-1) {
                    sum+=arr[i];
                    count++;
                }
                if(arr[i]!=-1 && i!=n-1 && arr[i+1]==-1) {
                    sum+=arr[i];
                    count++;
                    //System.out.println("in");
                }
            }
            long avg1=(long)Math.floor(sum/count);
            long avg2=(long)Math.ceil((double)sum/(double)count);
            
            
            for(int i=0;i<n;i++) {
                if(arr[i]==-1) {
                    arr[i]=avg1;
                }
            }
            long max_diff1=-1000000009L,diff1;
            for(int i=0;i<n-1;i++) {
                diff1=Math.abs(arr[i]-arr[i+1]);
                if(diff1>max_diff1) {
                    max_diff1=diff1;
                }
            }
            
            
            for(int i=0;i<n;i++) {
                if(arr_cpy[i]==-1) {
                    arr_cpy[i]=avg2;
                }
            }
            long max_diff2=-1000000009L,diff2;
            for(int i=0;i<n-1;i++) {
                diff2=Math.abs(arr_cpy[i]-arr_cpy[i+1]);
                if(diff2>max_diff2) {
                    max_diff2=diff2;
                }
            }
            
            long max_diff,avg;
            if(max_diff1<max_diff2) {
                max_diff=max_diff1;
                avg=avg1;
            }
            else {
                max_diff=max_diff2;
                avg=avg2;
            }
            System.out.println(max_diff+" "+avg);
        }
    }
}
