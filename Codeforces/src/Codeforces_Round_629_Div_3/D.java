/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_629_Div_3;

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
public class D {
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
            int arr[]=new int[n];
            int clr[]=new int[n];
            boolean all_eq=true,two_same=false;
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
                if(i>0) {
                    if(arr[i]!=arr[i-1]) {
                        all_eq=false;
                    }
                    if(arr[i]==arr[i-1]) {
                        two_same=true;
                    }
                }
            }
            if(n%2==0 && !all_eq) {
                System.out.println(2);
                for(int i=0;i<n;i++) {
                    if(i%2==0) {
                        System.out.print(1+" ");
                    }
                    else {
                        System.out.print(2+" ");
                    }
                }
                System.out.println();
                continue;
            }
            if(n%2==1 && !all_eq && two_same) {
                boolean got=false;
                System.out.println(2);
                for(int i=0;i<n;i++) {
                    if(!got && arr[i]==arr[i+1]) {
                        got=true;
                        if(i==0 || clr[i-1]==2) {
                            clr[i]=1;
                            clr[i+1]=1;
                        }
                        else {
                            clr[i]=2;
                            clr[i+1]=2;
                        }
                        i++;
                        continue;
                    }
                    if(i==0 || clr[i-1]==2) {
                        clr[i]=1;
                    }
                    else {
                        clr[i]=2;
                    }
                }
                for(int i=0;i<n;i++) {
                    System.out.print(clr[i]+" ");
                }
                System.out.println();
                continue;
            }
             clr[0]=1;
            for(int i=1;i<n;i++) {
                if(arr[i-1]==arr[i]) {
                    clr[i]=clr[i-1];
                }
                else {
                    if(clr[i-1]==1) {
                        clr[i]=2;
                    }
                    else {
                        clr[i]=1;
                    }
                }
            }
            if(arr[0]!=arr[n-1] && clr[0]==clr[n-1]) {
                if(clr[1]==clr[0]) {
                    if(clr[1]==1) {
                        clr[0]=2;
                    }
                    else {
                        clr[0]=1;
                    }
                }
                else if(clr[n-2]==clr[n-1]) {
                    if(clr[n-2]==1) {
                        clr[n-1]=2;
                    }
                    else {
                        clr[n-1]=1;
                    }
                }
                else {
                    clr[n-1]=3;
                }
            }
            int max_clr=1;
            for(int i=0;i<n;i++) {
                if(clr[i]>max_clr) {
                    max_clr=clr[i];
                }
            }
            System.out.println(max_clr);
            for(int i=0;i<n;i++) {
                System.out.print(clr[i]+" ");
            }
            System.out.println();
        }
    }
}
