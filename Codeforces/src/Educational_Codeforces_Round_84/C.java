/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_84;

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
        int n=input.nextInt();
        int m=input.nextInt();
        int k=input.nextInt();
        ArrayList<Integer> x_init=new ArrayList<>();
        ArrayList<Integer> y_init=new ArrayList<>();
        ArrayList<Integer> x_fin=new ArrayList<>();
        ArrayList<Integer> y_fin=new ArrayList<>();
        for(int i=0;i<k;i++) {
            x_init.add(input.nextInt());
            y_init.add(input.nextInt());
        }
        for(int i=0;i<k;i++) {
            x_fin.add(input.nextInt());
            y_fin.add(input.nextInt());
        }
        StringBuilder str=new StringBuilder("");
        if(n==1) {
            for(int i=0;i<m;i++) {
                str.append("L");
            }
            for(int i=0;i<m;i++) {
                str.append("R");
            }
            System.out.println(str.length()+"\n"+str);
            System.exit(0);
        }
        if(m==1) {
            for(int i=0;i<n;i++) {
                str.append("D");
            }
            for(int i=0;i<n;i++) {
                str.append("U");
            }
            System.out.println(str.length()+"\n"+str);
            System.exit(0);
        }
        for(int i=0;i<x_init.size();i++) {
            if(x_init.get(i).equals(x_fin.get(i)) && y_init.get(i).equals(y_fin.get(i))) {
                x_init.remove(i);
                x_fin.remove(i);
                y_init.remove(i);
                y_fin.remove(i);
                i--;
            }
        }
        while(x_init.size()!=0) {
            int min_dist=1000000000,min_indx=-1;
            for(int j=0;j<x_init.size();j++) {
                int dist=(Math.abs(x_init.get(j)-x_fin.get(j)))+(Math.abs(y_init.get(j)-y_fin.get(j)));
                if(dist<min_dist) {
                    min_dist=dist;
                    min_indx=j;
                }
            }
            int x_i=x_init.get(min_indx),x_f=x_fin.get(min_indx),y_i=y_init.get(min_indx),y_f=y_fin.get(min_indx);
            
            if(x_f>x_i) {
                int dist=0;
                for(int j=x_i;j!=x_f;j++) {
                    str.append('D');
                    dist++;
                    for(int p=0;p<x_init.size();p++) {
                        int set=x_init.get(p)+1;
                        if(set>n) {
                            set=n;
                        }
                        x_init.set(p, set);
                        if(x_init.get(p).equals(x_fin.get(p)) && y_init.get(p).equals(y_fin.get(p))) {
                            x_init.remove(p);
                            x_fin.remove(p);
                            y_init.remove(p);
                            y_fin.remove(p);
                            p--;
                        }
                    }
                }
            }
            
            else if(x_f<x_i) {
                int dist=0;
                for(int j=x_i;j!=x_f;j--) {
                    str.append('U');
                    dist++;
                    for(int p=0;p<x_init.size();p++) {
                        int set=x_init.get(p)-1;
                        if(set<1) {
                            set=1;
                        }
                        x_init.set(p, set);
                        if(x_init.get(p).equals(x_fin.get(p)) && y_init.get(p).equals(y_fin.get(p))) {
                            x_init.remove(p);
                            x_fin.remove(p);
                            y_init.remove(p);
                            y_fin.remove(p);
                            p--;
                        }
                    }
                }
            }
            
            if(y_f>y_i) {
                int dist=0;
                for(int j=y_i;j!=y_f;j++) {
                    str.append('R');
                    dist++;
                    for(int p=0;p<y_init.size();p++) {
                        int set=y_init.get(p)+1;
                        if(set>m) {
                            set=m;
                        }
                        y_init.set(p, set);
                        if(x_init.get(p).equals(x_fin.get(p)) && y_init.get(p).equals(y_fin.get(p))) {
                            x_init.remove(p);
                            x_fin.remove(p);
                            y_init.remove(p);
                            y_fin.remove(p);
                            p--;
                        }
                    }
                }
                
            }
            
            else if(y_f<y_i) {
                int dist=0;
                for(int j=y_i;j!=y_f;j--) {
                    str.append('L');
                    dist++;
                    for(int p=0;p<y_init.size();p++) {
                        int set=y_init.get(p)-1;
                        if(set<1) {
                            set=1;
                        }
                        y_init.set(p, set);
                        if(x_init.get(p).equals(x_fin.get(p)) && y_init.get(p).equals(y_fin.get(p))) {
                            x_init.remove(p);
                            x_fin.remove(p);
                            y_init.remove(p);
                            y_fin.remove(p);
                            p--;
                        }
                    }
                }
            }

            
//            x_init.remove(min_indx);
//            x_fin.remove(min_indx);
//            y_init.remove(min_indx);
//            y_fin.remove(min_indx);
            
//            System.out.println("------------------");
//            for(int j=0;j<x_init.size();j++) {
//                System.out.println(x_init.get(j)+" "+y_init.get(j));
//                System.out.println(x_fin.get(j)+" "+y_fin.get(j));
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println("\n");
        }
        if(str.length()<=2*m*n) {
            System.out.println(str.length()+"\n"+str);
        }
        else {
            System.out.println(-1);
        }
    }
}
