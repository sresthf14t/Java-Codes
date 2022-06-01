/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package March_Cook_Off_2021_Division_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class WATRA {
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
    
    static int n,m,k,arr[][],arr1[][],arr2[][],arr3[][],arr4[][];
    static char dir[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            m=input.scanInt();
            k=input.scanInt();
            arr=new int[n][m];
            arr1=new int[n][m];
            arr2=new int[n][m];
            arr3=new int[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    arr[i][j]=input.scanInt();
                    arr1[i][j]=arr[i][j]-1;
                    arr2[i][j]=arr[i][j]-2;
                    arr3[i][j]=arr[i][j]-3;
                }
            }
            
            arr[n-1][m-1]=arr1[n-1][m-1]=arr2[n-1][m-1]=arr3[n-1][m-1]=Integer.MAX_VALUE;
            
            dir=new char[4][n][m];
            for(int i=0;i<n;i++) {
                String str=input.scanString();
                for(int j=0;j<m;j++) {
                    dir[0][i][j]=str.charAt(j);
                    
                    if(dir[0][i][j]=='U') {
                       dir[1][i][j]='R';
                       dir[2][i][j]='D';
                       dir[3][i][j]='L';
                    }
                    if(dir[0][i][j]=='R') {
                       dir[1][i][j]='D';
                       dir[2][i][j]='L';
                       dir[3][i][j]='U';
                    }
                    if(dir[0][i][j]=='D') {
                       dir[1][i][j]='L';
                       dir[2][i][j]='U';
                       dir[3][i][j]='R';
                    }
                    if(dir[0][i][j]=='L') {
                       dir[1][i][j]='U';
                       dir[2][i][j]='R';
                       dir[3][i][j]='D';
                    }
                }
            }
            
//            BFS(0);
            ans.append(solve()+"\n");
            
        }
        System.out.println(ans);
    }
    
    public static int solve() {
        int l=0,r=1000000000,ans=0;
        while(l<=r) {
            int mid=(l+r)/2;
            if(BFS(mid)) {
                ans=mid;
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return ans;
    }
    
    public static boolean BFS(int val) {
        if(arr[0][0]<val) {
            return false;
        }
        LinkedList<Integer> rot=new LinkedList<>();
        LinkedList<Integer> quex=new LinkedList<>();
        LinkedList<Integer> quey=new LinkedList<>();
        int dep[][][]=new int[4][n][m];
        boolean vis[][][]=new boolean[4][n][m];
        
        rot.add(0);
        quex.add(0);
        quey.add(0);
        
        vis[0][0][0]=true;
        
        while(!quex.isEmpty()) {
            int rr=rot.pollFirst(),i=quex.pollFirst(),j=quey.pollFirst();
            
//            System.out.println(rr+" "+i+" "+j);
            
            if(rr!=3) {
                if(arr[i][j]-(rr+1)>=val && !vis[rr+1][i][j]) {
                    rot.addLast(rr+1);
                    quex.addLast(i);
                    quey.addLast(j);
                    dep[rr+1][i][j]=dep[rr][i][j]+1;
                    vis[rr+1][i][j]=true;
                }
            }
            
            if(dir[rr][i][j]=='U' && i!=0 && arr[i-1][j]>=val &&!vis[0][i-1][j]) {
                rot.addFirst(0);
                quex.addFirst(i-1);
                quey.addFirst(j);
                dep[0][i-1][j]=dep[rr][i][j];
                vis[0][i-1][j]=true;
            }
            
            if(dir[rr][i][j]=='R' && j!=m-1 && arr[i][j+1]>=val && !vis[0][i][j+1]) {
                rot.addFirst(0);
                quex.addFirst(i);
                quey.addFirst(j+1);
                dep[0][i][j+1]=dep[rr][i][j];
                vis[0][i][j+1]=true;
            }
            
            if(dir[rr][i][j]=='D' && i!=n-1 && arr[i+1][j]>=val && !vis[0][i+1][j]) {
                rot.addFirst(0);
                quex.addFirst(i+1);
                quey.addFirst(j);
                dep[0][i+1][j]=dep[rr][i][j];
                vis[0][i+1][j]=true;
            }
            
            if(dir[rr][i][j]=='L' && j!=0 && arr[i][j-1]>=val && !vis[0][i][j-1]) {
                rot.addFirst(0);
                quex.addFirst(i);
                quey.addFirst(j-1);
                dep[0][i][j-1]=dep[rr][i][j];
                vis[0][i][j-1]=true;
            }
        }
        
        if(vis[0][n-1][m-1] && dep[0][n-1][m-1]<=k) {
            return true;
        }
        if(vis[1][n-1][m-1] && dep[1][n-1][m-1]<=k) {
            return true;
        }
        if(vis[2][n-1][m-1] && dep[2][n-1][m-1]<=k) {
            return true;
        }
        if(vis[3][n-1][m-1] && dep[3][n-1][m-1]<=k) {
            return true;
        }
        
        return false;
    }
}
