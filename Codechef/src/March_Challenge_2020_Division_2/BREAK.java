/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package March_Challenge_2020_Division_2;

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
public class BREAK {
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
        int s=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int a[]=new int[n];
            int b[]=new int[n];
            for(int i=0;i<n;i++) {
                a[i]=input.nextInt();
            }
            for(int i=0;i<n;i++) {
                b[i]=input.nextInt();
            }
            Arrays.sort(a);
            Arrays.sort(b);
            if(s==1) {
                if(draw_is_pos_1(a,b,n)) {
                    System.out.println("YES");
                }
                else {
                    System.out.println("NO");
                }
                continue;
            }
            if(b[0]==b[n-1]) {
                System.out.println("NO");
            }
            else {
                System.out.println("YES");
            }
        }
    }
    
    //a attacker b defender
    public static boolean draw_is_pos_1(int[] a,int[] b,int n) {
        Set<Integer> hash_set = new HashSet<Integer>();
        for(int i=0;i<n;i++) {
            if(hash_set.isEmpty()) {
                hash_set.add(a[i]);
            }
            else {
                if(!hash_set.contains(a[i])) {
                    return false;
                }
            }
            if(b[i]>a[i]) {
                hash_set.add(a[i]);
                hash_set.add(b[i]);
            }
            else {
                return false;
            }
        }
        return true;
    }
    
    public static boolean draw_is_pos_2(int[] a,int[] b,int n,int strt) {
        Set<Integer> hash_set = new HashSet<Integer>();
        for(int i=strt;i<n;i++) {
            if(hash_set.isEmpty()) {
                hash_set.add(a[i]);
            }
            else {
                if(!hash_set.contains(a[i])) {
                    return draw_is_pos_2(b,a,n,i);
                }
            }
            if(b[i]>a[i]) {
                hash_set.add(a[i]);
                hash_set.add(b[i]);
            }
            else {
                return false;
            }
        }
        return true;
    }
}
