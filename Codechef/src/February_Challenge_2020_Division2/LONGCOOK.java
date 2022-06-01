/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package February_Challenge_2020_Division2;

/**
 *
 * @author User
 */
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Arrays;
import java.util.Scanner; 
import java.util.StringTokenizer;
public class LONGCOOK {
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
            int m1=input.nextInt();
            int y1=input.nextInt();
            int m2=input.nextInt();
            int y2=input.nextInt();
            int count=0;
            StringBuilder str=new StringBuilder("3456123460124560234501235601345612346012456023450123560134561234601245602345012356013456123460124560123460124560234501235601345612346012456023450123560134561234601245602345012356013456123460124560234560124560234501235601345612346012456023450123560134561234601245602345012356013456123460124560234501234560234501235601345612346012456023450123560134561234601245602345012356013456123460124560234501235601");
            if(m1>2) {
                y1++;
            }
            if(m2<2) {
                y2--;
            }
            if(y1%400!=1) {
                int strt=y1%400;
                if(strt==0) {
                    strt=400;
                }
                for(int i=strt-1;i<400;i++) {
                    if(str.charAt(i)=='5') {
                        count++;
                    }
                    else if(str.charAt(i)=='6' && !is_leap(i+1)) {
                        count++;
                    }
                    y1++;
                    if(y1>y2) {
                        break;
                    }
                }
            }
            if(y1>y2) {
                System.out.println(count);
                continue;
            }
            if(y2%400!=0) {
                int strt=y2%400;
                for(int i=strt-1;i>=0;i--) {
                    if(str.charAt(i)=='5') {
                        count++;
                    }
                    else if(str.charAt(i)=='6' && !is_leap(i+1)) {
                        count++;
                    }
                    y2--;
                    if(y1>y2) {
                        break;
                    }
                }
            }
            if(y1<y2) {
                int tmp=(y2-y1+1)/400;
                count+=(tmp*101);
            }
            System.out.println(count);
        }
    }
    
    public static boolean is_leap(int year) {
        if(year%400==0) {
            return true;
        }
        else if(year%4==0 && year%100!=0) {
            return true;
        }
        return false;
    }
    
    public static void count1() {
        StringBuilder str=new StringBuilder("3456123460124560234501235601345612346012456023450123560134561234601245602345012356013456123460124560123460124560234501235601345612346012456023450123560134561234601245602345012356013456123460124560234560124560234501235601345612346012456023450123560134561234601245602345012356013456123460124560234501234560234501235601345612346012456023450123560134561234601245602345012356013456123460124560234501235601");
        int count=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='5') {
                count++;
            }
            else if(str.charAt(i)=='6' && !is_leap(i+1)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
