/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_684_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class C1 {
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
    static int brr[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int m=input.scanInt();
//            int n=(int)(Math.random()*20);
//            int m=(int )(Math.random()*20);
//            n+=2;
//            m+=2;
            int arr[][]=new int[n][m];
            brr=new int[n][m];
            for(int i=0;i<n;i++) {
                String str=input.scanString();
                for(int j=0;j<m;j++) {
                    arr[i][j]=str.charAt(j)-'0';
//                    arr[i][j]=(int)(Math.random()*100);
//                    arr[i][j]%=2;
//                    brr[i][j]=arr[i][j];
                }
            }
            
            ans.append(solve(n,m,arr)+"\n");
//            boolean is_pos=true;
//            for(int i=0;i<n;i++) {
//                for(int j=0;j<m;j++) {
////                    System.out.print(arr[i][j]+" ");
//                    if(arr[i][j]==1) {
//                        is_pos=false;
//                    }
//                }
////                System.out.println();
//            }
//            if(!is_pos) {
//                System.out.println(-2);
//                return;
//            }
//            for(int i=0;i<n;i++) {
//                for(int j=0;j<m;j++) {
//                    System.out.print(arr[i][j]);
//                }
//                System.out.println();
//            }
        }
        System.out.println(ans);
    }
    public static StringBuilder solve(int n,int m,int arr[][]) {
        int cnt=0;
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            if(i+1>=n) {
                continue;
            }
            for(int j=0;j<m;j++) {
                if(j+1>=m) {
                    continue;
                }
                if(arr[i][j]==0 && i!=n-2 && j!=m-2) {
                    continue;
                }
                while(true) {
                    int one=count(i,j,arr);
                    if(one==0) {
                        break;
                    }
                    boolean done=false;
                    if(!(i==n-2 && j==m-2) && one==1) {
                            if(arr[i][j]==1 && i!=n-2) {
                                ans.append((i+1)+" "+(j+1)+" "+(i+1+1)+" "+(j+1)+" "+(i+1+1)+" "+(j+1+1));
                                done=true;
                                arr[i][j]=0;
                                arr[i+1][j]=arr[i+1][j+1]=1;
                            }
                            else if(arr[i][j]==1 && i==n-2) {
                                ans.append((i+1)+" "+(j+1)+" "+(i+1)+" "+(j+1+1)+" "+(i+1+1)+" "+(j+1+1));
                                done=true;
                                arr[i][j]=0;
                                arr[i][j+1]=arr[i+1][j+1]=1;
                            }
                            
                            else if(arr[i][j+1]==1 && j!=m-2);
                            else if(arr[i][j+1]==1 && j==m-2) {
                                ans.append((i+1)+" "+(j+1+1)+" "+(i+1+1)+" "+(j+1)+" "+(i+1+1)+" "+(j+1+1));
                                done=true;
                                arr[i][j+1]=0;
                                arr[i+1][j]=arr[i+1][j+1]=1;
                            }
                            
                            else if(arr[i+1][j]==1 && i!=n-2);
                            else if(arr[i+1][j]==1 && i==n-2) {
                                ans.append((i+1+1)+" "+(j+1)+" "+(i+1)+" "+(j+1+1)+" "+(i+1+1)+" "+(j+1+1));
                                done=true;
                                arr[i+1][j]=0;
                                arr[i][j+1]=arr[i+1][j+1]=1;
                        }
                        if(done) {
                            cnt++;
                            ans.append("\n");
                        }
                        break;
                    }
                    else {
                        op(i,j,arr,ans);
                    }
                    cnt++;
                    ans.append("\n");
                }
//                System.out.println(i+" "+j+" "+cnt);
            }
        }
//        if(cnt>n*m) {
//            System.out.println(n+" "+m+" "+cnt);
//            for(int i=0;i<n;i++) {
//                for(int j=0;j<m;j++) {
//                    System.out.print(brr[i][j]);
//                }
//                System.out.println();
//            }
//            
//            System.out.println(-1);
//            System.exit(0);
//        }
        return new StringBuilder(""+cnt+"\n"+ans);
    }
    public static int count(int i,int j,int arr[][]) {
        int cnt=0;
        cnt+=arr[i][j];
        cnt+=arr[i+1][j];
        cnt+=arr[i][j+1];
        cnt+=arr[i+1][j+1];
        return cnt;
    }
    public static void op(int i,int j,int arr[][],StringBuilder ans) {
        int one=arr[i][j]+arr[i+1][j]+arr[i][j+1]+arr[i+1][j+1];
        if(one==4) {
            ans.append((i+1)+" "+(j+1)+" "+(i+1+1)+" "+(j+1)+" "+(i+1)+" "+(j+1+1));
            arr[i][j]=0;
            arr[i+1][j]=0;
            arr[i][j+1]=0;
        }
        else if(one==3) {
            if(arr[i][j]==1) {
                arr[i][j]=0;
                ans.append((i+1)+" "+(j+1)+" ");
            }
            if(arr[i+1][j]==1) {
                arr[i+1][j]=0;
                ans.append((i+1+1)+" "+(j+1)+" ");
            }
            if(arr[i][j+1]==1) {
                arr[i][j+1]=0;
                ans.append((i+1)+" "+(j+1+1)+" ");
            }
            if(arr[i+1][j+1]==1) {
                arr[i+1][j+1]=0;
                ans.append((i+1+1)+" "+(j+1+1)+" ");
            }
        }
        else if(one==2) {
            int x=-1,y=-1;
            if(x==-1 && arr[i][j]==1) {
                arr[i][j]=0;
                x=i;
                y=j;
                ans.append((i+1)+" "+(j+1)+" ");
            }
            if(x==-1 && arr[i+1][j]==1) {
                arr[i+1][j]=0;
                x=i+1;
                y=j;
                ans.append((i+1+1)+" "+(j+1)+" ");
            }
            if(x==-1 && arr[i][j+1]==1) {
                arr[i][j+1]=0;
                x=i;
                y=j+1;
                ans.append((i+1)+" "+(j+1+1)+" ");
            }
            if(x==-1 && arr[i+1][j+1]==1) {
                arr[i+1][j+1]=0;
                x=i+1;
                y=j+1;
                ans.append((i+1+1)+" "+(j+1+1)+" ");
            }
            
            
            
            if(!(i==x && j==y) && arr[i][j]==0) {
                arr[i][j]=1;
                ans.append((i+1)+" "+(j+1)+" ");
            }
            if(!(i+1==x && j==y) && arr[i+1][j]==0) {
                arr[i+1][j]=1;
                ans.append((i+1+1)+" "+(j+1)+" ");
            }
            if(!(i==x && j+1==y) && arr[i][j+1]==0) {
                arr[i][j+1]=1;
                ans.append((i+1)+" "+(j+1+1)+" ");
            }
            if(!(i+1==x && j+1==y) && arr[i+1][j+1]==0) {
                arr[i+1][j+1]=1;
                ans.append((i+1+1)+" "+(j+1+1)+" ");
            }
        }
        
        else if(one==1) {
            int x=-1,y=-1;
            if(x==-1 && arr[i][j]==1) {
                arr[i][j]=0;
                x=i;
                y=j;
                ans.append((i+1)+" "+(j+1)+" ");
            }
            if(x==-1 && arr[i+1][j]==1) {
                arr[i+1][j]=0;
                x=i+1;
                y=j;
                ans.append((i+1+1)+" "+(j+1)+" ");
            }
            if(x==-1 && arr[i][j+1]==1) {
                arr[i][j+1]=0;
                x=i;
                y=j+1;
                ans.append((i+1)+" "+(j+1+1)+" ");
            }
            if(x==-1 && arr[i+1][j+1]==1) {
                arr[i+1][j+1]=0;
                x=i+1;
                y=j+1;
                ans.append((i+1+1)+" "+(j+1+1)+" ");
            }
            
            
            int cnt=0;
            if(cnt<2 && !(i==x && j==y) && arr[i][j]==0) {
                arr[i][j]=1;
                ans.append((i+1)+" "+(j+1)+" ");
                cnt++;
            }
            if(cnt<2 && !(i+1==x && j==y) && arr[i+1][j]==0) {
                arr[i+1][j]=1;
                ans.append((i+1+1)+" "+(j+1)+" ");
                cnt++;
            }
            if(cnt<2 && !(i==x && j+1==y) && arr[i][j+1]==0) {
                arr[i][j+1]=1;
                ans.append((i+1)+" "+(j+1+1)+" ");
                cnt++;
            }
            if(cnt<2 && !(i+1==x && j+1==y) && arr[i+1][j+1]==0) {
                arr[i+1][j+1]=1;
                ans.append((i+1+1)+" "+(j+1+1)+" ");
                cnt++;
            }
        }
    }
}
