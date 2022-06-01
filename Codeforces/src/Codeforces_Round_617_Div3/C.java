/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_617_Div3;

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
public class C {
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
            Set<String> hash_set = new HashSet<String>();
            String str=input.readLine();
            int arr[][]=new int[2][n+1];
            int x=0,y=0,count=999999999,l=-1,r=-1;
            arr[0][0]=x;
            arr[1][0]=y;
            hash_set.add(x+" "+y);
            for(int i=0;i<n;i++) {
                if(str.charAt(i)=='L') {
                    x--;
                }
                else if(str.charAt(i)=='R') {
                    x++;
                }
                else if(str.charAt(i)=='U') {
                    y++;
                }
                else if(str.charAt(i)=='D') {
                    y--;
                }
                if(hash_set.contains(x+" "+y)) {
                    int tmp=i,cntr=0;
                    while(tmp>=0) {
                        if(arr[0][tmp]==x && arr[1][tmp]==y) {
                            break;
                        }
                        //System.out.println(tmp);
                        tmp--;
                        cntr++;
                    }
                    if(cntr<count) {
                        l=tmp;
                        r=i;
                        //System.out.println((l)+" "+(r));
                        count=cntr;
                    }
                }
                arr[0][i+1]=x;
                arr[1][i+1]=y;
                hash_set.add(x+" "+y);
            }
            if(l==-1 && r==-1) {
                System.out.println(-1);
            }
            else {
              System.out.println((l+1)+" "+(r+1));  
            }
        }
    }
}
