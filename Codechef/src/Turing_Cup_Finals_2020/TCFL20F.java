/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turing_Cup_Finals_2020;

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
public class TCFL20F {
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
            int a=input.nextInt();
            int b=input.nextInt();
            int p=input.nextInt();
            int q=input.nextInt();
            Set<String> hash_set = new HashSet<String>();
            for(int i=0;i<n;i++) {
                int x=input.nextInt();
                int y=input.nextInt();
                hash_set.add(x+" "+y);
            }
            //0-up 1-right 2-down 3- left
            int direction=0,x=0,y=0,count=0,counter=0;
            System.out.println();
            while(x<=150 && y<=100) {
                if(direction==0) {
                    int lim=y+a;
                    while(y<lim) {
                        y++;
//                        System.out.println(x+" "+y);
                        if(hash_set.contains(x+" "+y)) {
                            count++;
                            hash_set.remove(x+" "+y);
                        }
                    }
                }
                else if(direction==1) {
                    int lim=x+a;
                    while(x<lim) {
//                        System.out.println(x+" "+y);
                        x++;
                        if(hash_set.contains(x+" "+y)) {
                            count++;
                            hash_set.remove(x+" "+y);
                        }
                    }
                }
                else if(direction==2) {
                    int lim=y-a;
                    while(y>lim) {
//                        System.out.println(x+" "+y);
                        y--;
                        if(hash_set.contains(x+" "+y)) {
                            count++;
                            hash_set.remove(x+" "+y);
                        }
                    }
                }
                else if(direction==3) {
                    int lim=x-a;
                    while(x>lim) {
//                        System.out.println(x+" "+y);
                        x--;
                        if(hash_set.contains(x+" "+y)) {
                            count++;
                            hash_set.remove(x+" "+y);
                        }
                    }
                }
                
                
                
                direction=(direction+1)%4;
                
                if(direction==0) {
                    int lim=y+b;
                    while(y<lim) {
//                        System.out.println(x+" "+y);
                        y++;
                        if(hash_set.contains(x+" "+y)) {
                            count++;
                            hash_set.remove(x+" "+y);
                        }
                    }
                }
                else if(direction==1) {
                    int lim=x+b;
                    while(x<lim) {
//                        System.out.println(x+" "+y);
                        x++;
                        if(hash_set.contains(x+" "+y)) {
                            count++;
                            hash_set.remove(x+" "+y);
                        }
                    }
                }
                else if(direction==2) {
                    int lim=y-b;
                    while(y>lim) {
//                        System.out.println(x+" "+y);
                        y--;
                        if(hash_set.contains(x+" "+y)) {
                            count++;
                            hash_set.remove(x+" "+y);
                        }
                    }
                }
                else if(direction==3) {
                    int lim=x-b;
                    while(x>lim) {
//                        System.out.println(x+" "+y);
                        x--;
                        if(hash_set.contains(x+" "+y)) {
                            count++;
                            hash_set.remove(x+" "+y);
                        }
                    }
                }
                
                a+=p;
                b+=q;
                counter++;
            }
            System.out.println(count);
        }
    }
}
