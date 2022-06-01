/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_615_Div3;

/**
 *
 * @author User
 */
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
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
    static StringBuilder str;
    public static void main(String args[]) throws IOException {
        Reader input=new Reader();
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int coord[][]=new int[2][n];
            int max=0;
            for(int i=0;i<n;i++) {
                coord[0][i]=input.nextInt();
                coord[1][i]=input.nextInt();
                if(coord[0][i]>max) {
                    max=coord[0][i];
                }
                if(coord[1][i]>max) {
                    max=coord[1][i];
                }
            }
            
            boolean arr[][]=new boolean[max+1][max+1];
            
            for(int i=0;i<n;i++) {
                arr[coord[1][i]][coord[0][i]]=true;
            }
            
            str=new StringBuilder("");
            boolean ispossible=true;
            StringBuilder tmp=new StringBuilder("");
            int x=0,y=0,count;
            for(int i=0;i<=max;i++) {
                count=0;
                for(int j=0;j<=max;j++) {
                    
                    if(arr[i][j]) {
                        if(j<x) {
                            ispossible=false;
                            System.out.println("NO");
                            break;
                        }
                        edit_str(count,'R');
                        str.append(tmp);
                        tmp=new StringBuilder("");
                        x=j;
                        count=0;
                    }
                    if(j>=x) {
                        count++;
                    }
                }
                if(!ispossible) {
                    break;
                }
                tmp.append('U');
            }
            if(!ispossible) {
                continue;
            }
            System.out.println("YES");
            System.out.println(str);
        }
    }
    public static void edit_str(int n,char c) {
        for(int i=0;i<n;i++) {
            str.append(c);
        }
    }
}
