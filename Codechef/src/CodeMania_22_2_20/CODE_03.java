/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeMania_22_2_20;

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
public class CODE_03 {
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
//        int arr[]={4,
//        40,44,
//        400,440,444,
//        4000,4400,4440,4444,
//        40000,44000,44400,44440,44444,
//        400000,440000,444000,444400,444440,444444,
//        4000000,4400000,4440000,4444000,4444400,4444440,4444444,
//        40000000,44000000,44400000,44440000,44444000,44444400,44444440,44444444,
//        400000000,440000000,444000000,444400000,444440000,444444000,444444400,444444440,444444444};
        int arr[]={4,40,44,400,440,444,4000,4400,4440,4444,40000,44000,44400,44440,44444,400000,440000,444000,444400,444440,444444,4000000,4400000,4440000,4444000,4444400,4444440,4444444,40000000,44000000,44400000,44440000,44444000,44444400,44444440,44444444,400000000,440000000,444000000,444400000,444440000,444444000,444444400,444444440,444444444};
        for(int t=1;t<=test;t++) {
            int x=input.nextInt();
            for(int i=0;i<arr.length;i++) {
                int tmp=arr[i];
                if(tmp%x==0) {
                    int a=0,b=0;
                    String tmp1=""+tmp;
                    for(int j=0;j<tmp1.length();j++) {
                        if(tmp1.charAt(j)=='0') {
                            b++;
                        }
                        else {
                            a++;
                        }
                    }
                    System.out.println((2*a)+b);
                    break;
                }
            }
        }
    }
    public static int generate(int n) {
        String bin="";
        while(n!=0) {
            bin=(n%2)+bin;
            n/=2;
        }
        int Bin=0;
        for(int i=0;i<bin.length();i++) {
            if(bin.charAt(i)=='0') {
                Bin=Bin*10;
            }
            else {
                Bin=Bin*10+4;
            }
        }
        return Bin;
    }
}
