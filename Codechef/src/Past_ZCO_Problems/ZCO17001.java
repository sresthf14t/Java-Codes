/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Past_ZCO_Problems;

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
public class ZCO17001 {
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
        int n=input.nextInt();
        int t=input.nextInt();
        int arr[]=new int[n];
        Set hash_set = new HashSet(); 
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
            hash_set.add(arr[i]);
        }
        Arrays.sort(arr);
        int count_arr[][]=new int[n][1000001];
        for(int i=0;i<n;i++) {
            if(arr[i]<=1000000) {
                count_arr[0][arr[i]]++;
                count_arr[count_arr[0][arr[i]]][arr[i]]=i;
            }
        }
        for(int i=0;i<6;i++) {
            for(int j=0;j<6;j++) {
                System.out.print(count_arr[i][j]+" ");
            }
            System.out.println();
        }
        long count=0;
        int tmp;
        for(int i=0;i<n && arr[i]<=t;i++) {
            for(int j=i+1;j<n && arr[j]<=t-arr[i];j++) {
                for(int k=j+1;k<n && arr[k]<=t-arr[i]-arr[j];k++) {
                    tmp=t-arr[i]-arr[j]-arr[k];
                    for(int m=1;m<=count_arr[0][tmp];m++) {
                        if(count_arr[m][tmp]>k) {
                            count+=1L;
                        }
                    }
//                    if(hash_set.contains(tmp)) {
//                        for(int l=k+1;l<n && arr[l]<=tmp;l++) {
//                            if(arr[l]==tmp) {
//                                count+=1L;
//                            }
//                        }
//                    }
                }
            }
        }
        System.out.println(count);
    }
}
