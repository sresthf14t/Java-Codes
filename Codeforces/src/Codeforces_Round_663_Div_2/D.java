/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_663_Div_2;

/**
 *
 * @author Srest
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
    static int n,m,arr[][],dp2[][][],dp3[][][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        arr=new int[n][m];
        int cnt=0;
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j)-48;
                cnt+=arr[i][j];
            }
        }
        if(n>3 && m>3) {
            System.out.println(-1);
            return;
        }
        if(n==1 || m==1) {
            System.out.println(0);
            return;
        }
        if(n==2) {
            dp2=new int[m][2][2];
            for(int i=0;i<m;i++) {
                for(int j=0;j<2;j++) {
                    for(int k=0;k<2;k++) {
                        dp2[i][j][k]=-1;
                    }
                }
            }
            int min=Integer.MAX_VALUE/2;
            min=Math.min(min,chng_2(0,0,0)+solve_2(1,0,0));
            min=Math.min(min,chng_2(0,0,1)+solve_2(1,0,1));
            min=Math.min(min,chng_2(0,1,0)+solve_2(1,1,0));
            min=Math.min(min,chng_2(0,1,1)+solve_2(1,1,1));
            System.out.println(min);
            return;
        }
        
        if(n==3) {
            dp3=new int[m][2][2][2];
            for(int i=0;i<m;i++) {
                for(int j=0;j<2;j++) {
                    for(int k=0;k<2;k++) {
                        for(int l=0;l<2;l++) {
                            dp3[i][j][k][l]=-1;
                        }
                    }
                }
            }
            int min=Integer.MAX_VALUE/2;
            min=Math.min(min,chng_3(0,0,0,0)+solve_3(1,0,0,0));
            min=Math.min(min,chng_3(0,0,0,1)+solve_3(1,0,0,1));
            min=Math.min(min,chng_3(0,0,1,0)+solve_3(1,0,1,0));
            min=Math.min(min,chng_3(0,0,1,1)+solve_3(1,0,1,1));
            
            min=Math.min(min,chng_3(0,1,0,0)+solve_3(1,1,0,0));
            min=Math.min(min,chng_3(0,1,0,1)+solve_3(1,1,0,1));
            min=Math.min(min,chng_3(0,1,1,0)+solve_3(1,1,1,0));
            min=Math.min(min,chng_3(0,1,1,1)+solve_3(1,1,1,1));
            System.out.println(min);
            return;
        }
    }
    public static int solve_2(int indx,int fst,int sec) {
        if(indx==m) {
            return 0;
        }
        if(dp2[indx][fst][sec]!=-1) {
            return dp2[indx][fst][sec];
        }
        int min=Integer.MAX_VALUE/2;
        if((fst+sec+0+0)%2==1) {
            min=Math.min(min,chng_2(indx,0,0)+solve_2(indx+1,0,0));
        }
        if((fst+sec+0+1)%2==1) {
            min=Math.min(min,chng_2(indx,0,1)+solve_2(indx+1,0,1));
        }
        if((fst+sec+1+0)%2==1) {
            min=Math.min(min,chng_2(indx,1,0)+solve_2(indx+1,1,0));
        }
        if((fst+sec+1+1)%2==1) {
            min=Math.min(min,chng_2(indx,1,1)+solve_2(indx+1,1,1));
        }
        dp2[indx][fst][sec]=min;
        return min;
    }
    public static int chng_2(int indx,int fst,int sec) {
        return Math.abs(arr[0][indx]-fst)+Math.abs(arr[1][indx]-sec);
    }
    
    
    public static int solve_3(int indx,int fst,int sec,int third) {
        if(indx==m) {
            return 0;
        }
        if(dp3[indx][fst][sec][third]!=-1) {
            return dp3[indx][fst][sec][third];
        }
        int min=Integer.MAX_VALUE/2;
        if(((fst+sec+0+0)%2==1) && ((sec+third+0+0)%2==1)) {
            min=Math.min(min,chng_3(indx,0,0,0)+solve_3(indx+1,0,0,0));
        }
        if(((fst+sec+0+0)%2==1) && ((sec+third+0+1)%2==1)) {
            min=Math.min(min,chng_3(indx,0,0,1)+solve_3(indx+1,0,0,1));
        }
        if(((fst+sec+0+1)%2==1) && ((sec+third+1+0)%2==1)) {
            min=Math.min(min,chng_3(indx,0,1,0)+solve_3(indx+1,0,1,0));
        }
        if(((fst+sec+0+1)%2==1) && ((sec+third+1+1)%2==1)) {
            min=Math.min(min,chng_3(indx,0,1,1)+solve_3(indx+1,0,1,1));
        }
        
        if(((fst+sec+1+0)%2==1) && ((sec+third+0+0)%2==1))  {
            min=Math.min(min,chng_3(indx,1,0,0)+solve_3(indx+1,1,0,0));
        }
        if(((fst+sec+1+0)%2==1) && ((sec+third+0+1)%2==1)) {
            min=Math.min(min,chng_3(indx,1,0,1)+solve_3(indx+1,1,0,1));
        }
        if(((fst+sec+1+1)%2==1) && ((sec+third+1+0)%2==1))  {
            min=Math.min(min,chng_3(indx,1,1,0)+solve_3(indx+1,1,1,0));
        }
        if(((fst+sec+1+1)%2==1) && ((sec+third+1+1)%2==1))  {
            min=Math.min(min,chng_3(indx,1,1,1)+solve_3(indx+1,1,1,1));
        }
//        if(indx==1) {
//            System.out.println(indx+" "+fst+" "+sec+" "+third+" "+min);
//        }
        dp3[indx][fst][sec][third]=min;
        return min;
    }
    public static int chng_3(int indx,int fst,int sec,int third) {
        return Math.abs(arr[0][indx]-fst)+Math.abs(arr[1][indx]-sec)+Math.abs(arr[2][indx]-third);
    }
}
