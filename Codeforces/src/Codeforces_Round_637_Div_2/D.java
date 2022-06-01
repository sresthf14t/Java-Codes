/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_637_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
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
    static boolean working[][];
    static int n;
    static StringBuilder dp[][];
    static int pos[][],min_req[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        int k=input.scanInt();
        pos=new int[n][10];
        working=new boolean[n][7];
        min_req=new int[n];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<7;j++) {
                if(str.charAt(j)=='1') {
                    working[i][j]=true;
                }
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<10;j++) {
                pos[i][j]=can_display(i,j);
            }
        }
        int sum=0;
        for(int i=n-1;i>=0;i--) {
            int min=Integer.MAX_VALUE;
            for(int j=0;j<10;j++) {
                if(pos[i][j]!=-1) {
                    min=Math.min(min,pos[i][j]);
                }
            }
            sum+=min;
            min_req[i]=sum;
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(min_req[i]+" ");
//        }
//        System.out.println();
        dp=new StringBuilder[n][k+1];
        StringBuilder ans=new StringBuilder(solve(0,k));
        if(ans.length()==0) {
            ans.append("-1");
        }
        System.out.println(ans);
    }
    public static StringBuilder solve(int indx,int k_rem) {
        if(dp[indx][k_rem]!=null) {
            return dp[indx][k_rem];
        }
        if(k_rem<min_req[indx]) {
            dp[indx][k_rem]=new StringBuilder("");
            return dp[indx][k_rem];
        }
        if(indx==n-1) {
            for(int i=9;i>=0;i--) {
                int req=pos[indx][i];
                if(req==k_rem) {
                    dp[indx][k_rem]=new StringBuilder(""+i);
                    return dp[indx][k_rem];
                }
            }
            dp[indx][k_rem]=new StringBuilder("");
            return dp[indx][k_rem];
        }
        for(int i=9;i>=0;i--) {
           int req=pos[indx][i];
           if(req!=-1 && req<=k_rem) {
               StringBuilder tmp=new StringBuilder(""+i);
               tmp.append(solve(indx+1,k_rem-req));
               if(tmp.length()==n-indx) {
                   dp[indx][k_rem]=tmp;
                   return dp[indx][k_rem];
               }
           }
        }
        dp[indx][k_rem]=new StringBuilder("");
        return dp[indx][k_rem];
    }
    
//    public static boolean compare(StringBuilder num) {
//        for(int i=0;i<num.length();i++) {
//            if(num.charAt(i)>max.charAt(i)) {
//                return true;
//            }
//            else if(num.charAt(i)<max.charAt(i)) {
//                return false;
//            }
//        }
//        return false;
//    }
    
    public static int can_display(int indx,int num) {
        if(num==0) {
            int count=6;
            if(working[indx][0]) {
                count--;
            }
            if(working[indx][1]) {
                count--;
            }
            if(working[indx][2]) {
                count--;
            }
            if(working[indx][4]) {
                count--;
            }
            if(working[indx][5]) {
                count--;
            }
            if(working[indx][6]) {
                count--;
            }
            if(working[indx][3]) {
                return -1;
            }
            return count;
        }
        
        
        if(num==1) {
            int count=2;
            if(working[indx][2]) {
                count--;
            }
            if(working[indx][5]) {
                count--;
            }
            if(working[indx][0] || working[indx][1] || working[indx][3] ||working[indx][4] ||working[indx][6]) {
                return -1;
            }
            return count;
        }
        
        if(num==2) {
            int count=5;
            if(working[indx][0]) {
                count--;
            }
            if(working[indx][2]) {
                count--;
            }
            if(working[indx][3]) {
                count--;
            }
            if(working[indx][4]) {
                count--;
            }
            if(working[indx][6]) {
                count--;
            }
            if(working[indx][1] || working[indx][5]) {
                return -1;
            }
            return count;
        }
        
        
        if(num==3) {
            int count=5;
            if(working[indx][0]) {
                count--;
            }
            if(working[indx][2]) {
                count--;
            }
            if(working[indx][3]) {
                count--;
            }
            if(working[indx][5]) {
                count--;
            }
            if(working[indx][6]) {
                count--;
            }
            if(working[indx][1] || working[indx][4]) {
                return -1;
            }
            return count;
        }
        
        if(num==4) {
            int count=4;
            if(working[indx][1]) {
                count--;
            }
            if(working[indx][2]) {
                count--;
            }
            if(working[indx][3]) {
                count--;
            }
            if(working[indx][5]) {
                count--;
            }
            if(working[indx][0] || working[indx][4] || working[indx][6]) {
                return -1;
            }
            return count;
        }
        
        if(num==5) {
            int count=5;
            if(working[indx][0]) {
                count--;
            }
            if(working[indx][1]) {
                count--;
            }
            if(working[indx][3]) {
                count--;
            }
            if(working[indx][5]) {
                count--;
            }
            if(working[indx][6]) {
                count--;
            }
            if(working[indx][2] || working[indx][4]) {
                return -1;
            }
            return count;
        }
        
        if(num==6) {
            int count=6;
            if(working[indx][0]) {
                count--;
            }
            if(working[indx][1]) {
                count--;
            }
            if(working[indx][3]) {
                count--;
            }
            if(working[indx][4]) {
                count--;
            }
            if(working[indx][5]) {
                count--;
            }
            if(working[indx][6]) {
                count--;
            }
            if(working[indx][2]) {
                return -1;
            }
            return count;
        }
        
        if(num==7) {
            int count=3;
            if(working[indx][0]) {
                count--;
            }
            if(working[indx][2]) {
                count--;
            }
            if(working[indx][5]) {
                count--;
            }
            if(working[indx][1] || working[indx][3] || working[indx][4] || working[indx][6]) {
                return -1;
            }
            return count;
        }
        
        if(num==8) {
            int count=7;
            if(working[indx][0]) {
                count--;
            }
            if(working[indx][1]) {
                count--;
            }
            if(working[indx][2]) {
                count--;
            }
            if(working[indx][3]) {
                count--;
            }
            if(working[indx][4]) {
                count--;
            }
            if(working[indx][5]) {
                count--;
            }
            if(working[indx][6]) {
                count--;
            }
            return count;
        }
        
        
        if(num==9) {
            int count=6;
            if(working[indx][0]) {
                count--;
            }
            if(working[indx][1]) {
                count--;
            }
            if(working[indx][2]) {
                count--;
            }
            if(working[indx][3]) {
                count--;
            }
            if(working[indx][5]) {
                count--;
            }
            if(working[indx][6]) {
                count--;
            }
            if(working[indx][4]) {
                return -1;
            }
            return count;
        }
        return -1;
    }
}
