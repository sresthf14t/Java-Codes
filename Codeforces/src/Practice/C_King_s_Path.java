/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C_King_s_Path {
    static class Scan {
        private byte[] buf=new byte[1024];
        private int index;
        private InputStream in;
        private int total;
        public Scan()
        {
            in=System.in;
        }
        public int scan()throws IOException
        {
            if(total<0)
            throw new InputMismatchException();
            if(index>=total)
            {
                index=0;
                total=in.read(buf);
                if(total<=0)
                return -1;
            }
            return buf[index++];
        }
        public int scanInt()throws IOException
        {
            int integer=0;
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n))
            {
                if(n>='0'&&n<='9')
                {
                    integer*=10;
                    integer+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            return neg*integer;
        }
        public double scanDouble()throws IOException
        {
            double doub=0;
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n)&&n!='.')
            {
                if(n>='0'&&n<='9')
                {
                    doub*=10;
                    doub+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            if(n=='.')
            {
                n=scan();
                double temp=1;
                while(!isWhiteSpace(n))
                {
                    if(n>='0'&&n<='9')
                    {
                        temp/=10;
                        doub+=(n-'0')*temp;
                        n=scan();
                    }
                    else throw new InputMismatchException();
                }
            }
            return doub*neg;
        }
        public String scanString()throws IOException
        {
            StringBuilder sb=new StringBuilder();
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            while(!isWhiteSpace(n))
            {
                sb.append((char)n);
                n=scan();
            }
            return sb.toString();
        }
        private boolean isWhiteSpace(int n)
        {
            if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
            return true;
            return false;
        }
    }
    static HashMap<Long,Long> map;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int x0=input.scanInt();
        int y0=input.scanInt();
        int x1=input.scanInt();
        int y1=input.scanInt();
        int n=input.scanInt();
        int row[]=new int[n];
        int a[]=new int[n];
        int b[]=new int[n];
        for(int i=0;i<n;i++) {
            row[i]=input.scanInt();
            a[i]=input.scanInt();
            b[i]=input.scanInt();
        }
        map=new HashMap<>();
        for(int i=0;i<n;i++) {
            for(int j=a[i];j<=b[i];j++) {
                long hash=hash(row[i],j);
                if(!map.containsKey(hash)) {
                    map.put(hash, 0L);
                }
            }
        }
        BFS(x0,y0);
        long hash=hash(x1,y1);
        System.out.println(map.get(hash)-1);
    }
    
    public static long hash(long x,long y) {
        long tmp=1000000007L;
        return x+(tmp*y);
    }
    
    
    static void BFS(int root_x,int root_y) {
        Queue<Integer> quex = new LinkedList<>();
        Queue<Integer> quey = new LinkedList<>();
        quex.add(root_x);
        quey.add(root_y);
        long hash=hash(root_x,root_y);
        map.replace(hash, 1L);
        while(!quex.isEmpty()) {
            root_x=quex.peek();
            root_y=quey.peek();
            long root_hash=hash(root_x,root_y);
            hash=hash(root_x-1,root_y);
            if(map.containsKey(hash) && map.get(hash)==0) {
                map.replace(hash, map.get(root_hash)+1);
                quex.add(root_x-1);
                quey.add(root_y);
            }
            hash=hash(root_x+1,root_y);
            if(map.containsKey(hash) && map.get(hash)==0) {
                map.replace(hash, map.get(root_hash)+1);
                quex.add(root_x+1);
                quey.add(root_y);
            }
            
            hash=hash(root_x,root_y-1);
            if(map.containsKey(hash) && map.get(hash)==0) {
                map.replace(hash, map.get(root_hash)+1);
                quex.add(root_x);
                quey.add(root_y-1);
            }
            hash=hash(root_x,root_y+1);
            if(map.containsKey(hash) && map.get(hash)==0) {
                map.replace(hash, map.get(root_hash)+1);
                quex.add(root_x);
                quey.add(root_y+1);
            }
            
            
            hash=hash(root_x-1,root_y-1);
            if(map.containsKey(hash) && map.get(hash)==0) {
                map.replace(hash, map.get(root_hash)+1);
                quex.add(root_x-1);
                quey.add(root_y-1);
            }
            hash=hash(root_x+1,root_y+1);
            if(map.containsKey(hash) && map.get(hash)==0) {
                map.replace(hash, map.get(root_hash)+1);
                quex.add(root_x+1);
                quey.add(root_y+1);
            }
            
            hash=hash(root_x+1,root_y-1);
            if(map.containsKey(hash) && map.get(hash)==0) {
                map.replace(hash, map.get(root_hash)+1);
                quex.add(root_x+1);
                quey.add(root_y-1);
            }
            hash=hash(root_x-1,root_y+1);
            if(map.containsKey(hash) && map.get(hash)==0) {
                map.replace(hash, map.get(root_hash)+1);
                quex.add(root_x-1);
                quey.add(root_y+1);
            }
            
            quex.poll();
            quey.poll();
        }
    }
}
