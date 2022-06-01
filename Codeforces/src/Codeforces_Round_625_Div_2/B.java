/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_625_Div_2;

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
        static long[] table;
        public static void main(String args[]) throws IOException {
            Reader input=new Reader();
            int n=input.nextInt();
            int arr[]=new int[n];
            table=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            long max_beauty=-1;
            for(int i=0;i<n;i++) {
                long beauty=count(i,n,arr);
                if(beauty>max_beauty) {
                    max_beauty=beauty;
                }
            }
            System.out.println(max_beauty);
        }
        
        public static long count(int pos,int n,int arr[]) {
            if(table[pos]!=0) {
                return table[pos];
            }
            boolean is_done=false;
            for(int i=pos+1;i<n;i++) {
                if(i-pos==arr[i]-arr[pos]) {
                    is_done=true;
                    table[pos]=arr[pos]+count(i,n,arr);
                    break;
                }
            }
            if(!is_done) {
                table[pos]=arr[pos];
                return table[pos]; 
               
            }
            return table[pos];
        }
     
    }


