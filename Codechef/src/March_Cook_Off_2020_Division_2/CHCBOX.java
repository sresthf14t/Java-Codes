/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package March_Cook_Off_2020_Division_2;

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
public class CHCBOX{
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
            int max=-1,first_indx=-1,first_lst_indx=-1,lst_first_indx=-1,lst_indx=-1;
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
                if(arr[i]>max) {
                    max=arr[i];
                }
            }
            for(int i=0;i<n/2;i++) {
                if(arr[i]==max) {
                    if(first_indx==-1) {
                        first_indx=i;
                    }
                    first_lst_indx=i;
                }
            }
            for(int i=n/2;i<n;i++) {
                if(arr[i]==max) {
                    if(lst_first_indx==-1) {
                        lst_first_indx=i;
                    }
                    lst_indx=i;
                }
            }
//            System.out.println(max+" "+first_indx+" "+first_lst_indx+" "+lst_indx);
            if(first_indx==-1) {
                int diff=lst_indx-lst_first_indx+1;
                System.out.println((n/2)-diff+1);
            }
            else if(lst_indx==-1) {
                first_lst_indx+=(n/2)-first_indx;
                first_indx=n/2;
                int diff=first_lst_indx-first_indx+1;
                System.out.println((n/2)-diff+1);
            }
            else {
                if(lst_indx-first_indx+1 > n/2) {
                    System.out.println(0);
                }
                else {
                    lst_indx+=(n/2)-first_indx;
                    first_indx=n/2;
                    int diff=lst_indx-first_indx+1;
                    System.out.println((n/2)-diff+1);
                }
            }
        }
    }
}
