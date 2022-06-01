/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_160;

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
public class E {
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
        int x=input.nextInt();
        int y=input.nextInt();
        int a=input.nextInt();
        int b=input.nextInt();
        int c=input.nextInt();
        int arr_a[]=new int[a];
        int arr_b[]=new int[b];
        int arr_c[]=new int[c];
        int a_ptr=0,b_ptr=0,c_ptr=0;
        for(int i=0;i<a;i++) {
            arr_a[i]=input.nextInt();
        }
        for(int i=0;i<b;i++) {
            arr_b[i]=input.nextInt();
        }
        for(int i=0;i<c;i++) {
            arr_c[i]=input.nextInt();
        }
        int sum_a=0,sum_b=0,sum=0;
        for(int i=0;i<x && i<a ;i++) {
            sum_a+=arr_a[i];
        }
        for(int i=0;i<x && i<b ;i++) {
            sum_b+=arr_b[i];
        }
        Arrays.sort(arr_a);
        Arrays.sort(arr_b);
        Arrays.sort(arr_c);
        while(x!=0 && y!=0) {
            if(arr_c[c_ptr]>arr_a[a_ptr] && arr_c[c_ptr]>arr_b[b_ptr]) {
                sum+=arr_c[c_ptr];
                c_ptr++;
                if(sum_a>sum_b) {
                    if(x<a) {
                        sum_a-=arr_a[x-1];
                    }
                    x--;
                }
                else {
                    if(y<b) {
                        sum_b-=arr_b[y-1];
                    }
                    y--;
                }
            }
            else if(arr_a[a_ptr]>arr_b[b_ptr]) {
                sum+=arr_a[a_ptr];
                sum_a-=arr_a[a_ptr];
                a_ptr++;
                x--;
            }
            else {
                sum+=arr_b[b_ptr];
                sum_b-=arr_b[b_ptr];
                b_ptr++;
                y--;
            }
        }
        System.out.println(sum);
    }
}
