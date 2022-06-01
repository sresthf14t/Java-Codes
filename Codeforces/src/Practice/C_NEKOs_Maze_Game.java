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
import java.io.*; 
import java.util.*;
public class C_NEKOs_Maze_Game {
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
    static boolean arr[][];
    static int indx[][];
    static Set<Integer> hashset;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int q=input.scanInt();
        arr=new boolean[4][n+2];
        indx=new int[4][n+2];
        int cnt=0;
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++) {
                indx[i][j]=cnt;
                cnt++;
            }
        }
        hashset=new HashSet<>();
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<q;i++) {
            int x=input.scanInt();
            int y=input.scanInt();
            arr[x][y]=!arr[x][y];
            if(arr[x][y]) {
                update_add(x,y);
            }
            else {
                update_rem(x,y);
            }
//            System.out.println(hashset.size());
            if(hashset.isEmpty()) {
                ans.append("Yes");
            }
            else {
                ans.append("No");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static void update_rem(int x,int y) {
        if(arr[x==1?2:1][y-1] || arr[x==1?2:1][y] || arr[x==1?2:1][y+1]) {
            hashset.remove(indx[x][y]);
        }
        int x1=(x==1?2:1),y1=y-1;
        if(arr[x1][y1] && hashset.contains(indx[x1][y1])) {
            if(!arr[x1==1?2:1][y1-1] && !arr[x1==1?2:1][y1] && !arr[x1==1?2:1][y1+1]) {
                hashset.remove(indx[x1][y1]);
            }
        }
        
        x1=(x==1?2:1);
        y1=y;
        if(arr[x1][y1] && hashset.contains(indx[x1][y1])) {
            if(!arr[x1==1?2:1][y1-1] && !arr[x1==1?2:1][y1] && !arr[x1==1?2:1][y1+1]) {
                hashset.remove(indx[x1][y1]);
            }
        }
        
        x1=(x==1?2:1);
        y1=y+1;
        if(arr[x1][y1] && hashset.contains(indx[x1][y1])) {
            if(!arr[x1==1?2:1][y1-1] && !arr[x1==1?2:1][y1] && !arr[x1==1?2:1][y1+1]) {
                hashset.remove(indx[x1][y1]);
            }
        }
    }
    public static void update_add(int x,int y) {
        if(arr[x==1?2:1][y-1]) {
            if(!hashset.contains(indx[x][y])) {
                hashset.add(indx[x][y]);
            }
            if(!hashset.contains(indx[x==1?2:1][y-1])) {
                hashset.add(indx[x==1?2:1][y-1]);
            }
        }
        if(arr[x==1?2:1][y]) {
            if(!hashset.contains(indx[x][y])) {
                hashset.add(indx[x][y]);
            }
            if(!hashset.contains(indx[x==1?2:1][y])) {
                hashset.add(indx[x==1?2:1][y]);
            }
        }
        if(arr[x==1?2:1][y+1]) {
            if(!hashset.contains(indx[x][y])) {
                hashset.add(indx[x][y]);
            }
            if(!hashset.contains(indx[x==1?2:1][y+1])) {
                hashset.add(indx[x==1?2:1][y+1]);
            }
        }
    }
}
