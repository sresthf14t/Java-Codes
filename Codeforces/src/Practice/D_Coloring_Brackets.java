/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D_Coloring_Brackets {
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
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    static String str;
    static long dp[][][][],mod;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        str=input.scanString();
        str="("+str+")";
        mod=1000000007L;
        dp=new long[str.length()][str.length()][5][5];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                for(int k=0;k<dp[0][0].length;k++) {
                    for(int m=0;m<dp[0][0][0].length;m++) {
                        dp[i][j][k][m]=-1;
                    }
                }
            }
        }
        System.out.println(solve(0,str.length()-1,0,0));
    }
    public static long solve(int l,int r,int l_clr,int r_clr) {
        if(l+1==r) {
            if(l_clr==0 && r_clr==0) {
                return 4;
            }
            if(l_clr!=0 && r_clr!=0) {
                return 2;
            }
            return 3;
        }
        if(dp[l][r][l_clr][r_clr]!=-1) {
            return dp[l][r][l_clr][r_clr];
        }
        ArrayList<Integer> strt=new ArrayList<>();
        ArrayList<Integer> end=new ArrayList<>();
        get(l+1,r-1,strt,end);
//        System.out.println(l+" "+r);
        if(strt.size()==1) {
            dp[strt.get(0)][end.get(0)][state(0,1)][state(l_clr,r_clr)]=r_clr==1?0:solve(strt.get(0),end.get(0),0,1);
            dp[strt.get(0)][end.get(0)][state(0,2)][state(l_clr,r_clr)]=r_clr==2?0:solve(strt.get(0),end.get(0),0,2);
            dp[strt.get(0)][end.get(0)][state(1,0)][state(l_clr,r_clr)]=l_clr==1?0:solve(strt.get(0),end.get(0),1,0);
            dp[strt.get(0)][end.get(0)][state(2,0)][state(l_clr,r_clr)]=l_clr==2?0:solve(strt.get(0),end.get(0),2,0);
            
            dp[strt.get(0)][end.get(0)][state(0,1)][state(l_clr,r_clr)]%=mod;
            dp[strt.get(0)][end.get(0)][state(0,2)][state(l_clr,r_clr)]%=mod;
            dp[strt.get(0)][end.get(0)][state(1,0)][state(l_clr,r_clr)]%=mod;
            dp[strt.get(0)][end.get(0)][state(2,0)][state(l_clr,r_clr)]%=mod;
        }
        for(int i=0;i<strt.size() && strt.size()>1;i++) {
            if(i==0) {
                dp[strt.get(i)][end.get(i)][state(0,1)][state(l_clr,r_clr)]=solve(strt.get(i),end.get(i),0,1);
                dp[strt.get(i)][end.get(i)][state(0,2)][state(l_clr,r_clr)]=solve(strt.get(i),end.get(i),0,2);
                dp[strt.get(i)][end.get(i)][state(1,0)][state(l_clr,r_clr)]=l_clr==1?0:solve(strt.get(i),end.get(i),1,0);
                dp[strt.get(i)][end.get(i)][state(2,0)][state(l_clr,r_clr)]=l_clr==2?0:solve(strt.get(i),end.get(i),2,0);
                
                dp[strt.get(i)][end.get(i)][state(0,1)][state(l_clr,r_clr)]%=mod;
                dp[strt.get(i)][end.get(i)][state(0,2)][state(l_clr,r_clr)]%=mod;
                dp[strt.get(i)][end.get(i)][state(1,0)][state(l_clr,r_clr)]%=mod;
                dp[strt.get(i)][end.get(i)][state(2,0)][state(l_clr,r_clr)]%=mod;
                continue;
            }
            if(i==strt.size()-1) {
                dp[strt.get(i)][end.get(i)][state(0,1)][state(l_clr,r_clr)]=r_clr==1?0:(dp[strt.get(i-1)][end.get(i-1)][0][1]+dp[strt.get(i-1)][end.get(i-1)][0][2]+dp[strt.get(i-1)][end.get(i-1)][2][0]+dp[strt.get(i-1)][end.get(i-1)][1][0])*solve(strt.get(i),end.get(i),0,1);
                dp[strt.get(i)][end.get(i)][state(0,2)][state(l_clr,r_clr)]=r_clr==2?0:(dp[strt.get(i-1)][end.get(i-1)][0][1]+dp[strt.get(i-1)][end.get(i-1)][0][2]+dp[strt.get(i-1)][end.get(i-1)][2][0]+dp[strt.get(i-1)][end.get(i-1)][1][0])*solve(strt.get(i),end.get(i),0,2);
                dp[strt.get(i)][end.get(i)][state(1,0)][state(l_clr,r_clr)]=(dp[strt.get(i-1)][end.get(i-1)][0][2]+dp[strt.get(i-1)][end.get(i-1)][2][0]+dp[strt.get(i-1)][end.get(i-1)][1][0])*solve(strt.get(i),end.get(i),1,0);
                dp[strt.get(i)][end.get(i)][state(2,0)][state(l_clr,r_clr)]=(dp[strt.get(i-1)][end.get(i-1)][0][1]+dp[strt.get(i-1)][end.get(i-1)][2][0]+dp[strt.get(i-1)][end.get(i-1)][1][0])*solve(strt.get(i),end.get(i),2,0);
                
                dp[strt.get(i)][end.get(i)][state(0,1)][state(l_clr,r_clr)]%=mod;
                dp[strt.get(i)][end.get(i)][state(0,2)][state(l_clr,r_clr)]%=mod;
                dp[strt.get(i)][end.get(i)][state(1,0)][state(l_clr,r_clr)]%=mod;
                dp[strt.get(i)][end.get(i)][state(2,0)][state(l_clr,r_clr)]%=mod;
                continue;
            }
            dp[strt.get(i)][end.get(i)][state(0,1)][state(l_clr,r_clr)]=(dp[strt.get(i-1)][end.get(i-1)][0][1]+dp[strt.get(i-1)][end.get(i-1)][0][2]+dp[strt.get(i-1)][end.get(i-1)][2][0]+dp[strt.get(i-1)][end.get(i-1)][1][0])*solve(strt.get(i),end.get(i),0,1);
            dp[strt.get(i)][end.get(i)][state(0,2)][state(l_clr,r_clr)]=(dp[strt.get(i-1)][end.get(i-1)][0][1]+dp[strt.get(i-1)][end.get(i-1)][0][2]+dp[strt.get(i-1)][end.get(i-1)][2][0]+dp[strt.get(i-1)][end.get(i-1)][1][0])*solve(strt.get(i),end.get(i),0,2);
            dp[strt.get(i)][end.get(i)][state(1,0)][state(l_clr,r_clr)]=(dp[strt.get(i-1)][end.get(i-1)][0][2]+dp[strt.get(i-1)][end.get(i-1)][2][0]+dp[strt.get(i-1)][end.get(i-1)][1][0])*solve(strt.get(i),end.get(i),1,0);
            dp[strt.get(i)][end.get(i)][state(2,0)][state(l_clr,r_clr)]=(dp[strt.get(i-1)][end.get(i-1)][0][1]+dp[strt.get(i-1)][end.get(i-1)][2][0]+dp[strt.get(i-1)][end.get(i-1)][1][0])*solve(strt.get(i),end.get(i),2,0);
            
            dp[strt.get(i)][end.get(i)][state(0,1)][state(l_clr,r_clr)]%=mod;
            dp[strt.get(i)][end.get(i)][state(0,2)][state(l_clr,r_clr)]%=mod;
            dp[strt.get(i)][end.get(i)][state(1,0)][state(l_clr,r_clr)]%=mod;
            dp[strt.get(i)][end.get(i)][state(2,0)][state(l_clr,r_clr)]%=mod;
        }
        
        System.out.println(l+" "+r+" "+( dp[strt.get(strt.size()-1)][end.get(end.size()-1)][state(0,1)][state(l_clr,r_clr)]+
                dp[strt.get(strt.size()-1)][end.get(end.size()-1)][state(0,2)][state(l_clr,r_clr)]+
                dp[strt.get(strt.size()-1)][end.get(end.size()-1)][state(1,0)][state(l_clr,r_clr)]+
                dp[strt.get(strt.size()-1)][end.get(end.size()-1)][state(2,0)][state(l_clr,r_clr)] ));
        
        return ( dp[strt.get(strt.size()-1)][end.get(end.size()-1)][state(0,1)][state(l_clr,r_clr)]+
                dp[strt.get(strt.size()-1)][end.get(end.size()-1)][state(0,2)][state(l_clr,r_clr)]+
                dp[strt.get(strt.size()-1)][end.get(end.size()-1)][state(1,0)][state(l_clr,r_clr)]+
                dp[strt.get(strt.size()-1)][end.get(end.size()-1)][state(2,0)][state(l_clr,r_clr)] )%mod;
    }
    public static int state(int c1,int c2) {
        if(c1==0 && c2==0) {
            return 0;
        }
        if(c1==0 && c2==1) {
            return 1;
        }
        if(c1==0 && c2==2) {
            return 2;
        }
        if(c1==1 && c2==0) {
            return 3;
        }
        return 4;
    }
    public static void get(int l,int r,ArrayList<Integer> strt,ArrayList<Integer> end) {
        int cnt=0;
        strt.add(l);
        for(int i=l;i<=r;i++) {
            if(str.charAt(i)=='(') {
                cnt++;
            }
            else {
                cnt--;
            }
            if(cnt==0) {
                end.add(i);
                if(i!=r) {
                    strt.add(i+1);
                }
            }
        }
    }
}
