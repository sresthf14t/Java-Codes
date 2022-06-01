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
public class D_Theseus_and_labyrinth {
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
    static int n,m,depth[][][];
    static ArrayList<Integer> adj_lst[][][];
    static char arr[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        arr=new char[n][m];
        adj_lst=new ArrayList[4][n][m];
        depth=new int[4][n][m];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
                adj_lst[0][i][j]=new ArrayList<>();
                adj_lst[1][i][j]=new ArrayList<>();
                adj_lst[2][i][j]=new ArrayList<>();
                adj_lst[3][i][j]=new ArrayList<>();
            }
        }
        int x1=input.scanInt()-1;
        int y1=input.scanInt()-1;
        int x2=input.scanInt()-1;
        int y2=input.scanInt()-1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                add(i,j);
            }
        }
        BFS(x1,y1);
        if(x1==x2 && y1==y2) {
            System.out.println(0);
            return;
        }
        System.out.println(depth[0][x2][y2]+" "+depth[1][x2][y2]+" "+depth[2][x2][y2]+" "+depth[3][x2][y2]);
        int ans=Math.min(depth[0][x2][y2],depth[1][x2][y2]);
        ans=Math.min(ans,depth[2][x2][y2]);
        ans=Math.min(ans,depth[3][x2][y2]);
        if(ans==0) {
            ans=-1;
        }
        System.out.println(ans);
    }
    public static void BFS(int rx,int ry) {
        Queue<Integer> que=new LinkedList<>();
        que.add(0);
        que.add(rx);
        que.add(ry);
        depth[0][rx][ry]=0;
        boolean vis[][][]=new boolean[4][n][m];
        while(!que.isEmpty()) {
            int indx1=que.poll();
            int indx2=que.poll();
            int indx3=que.poll();
            for(int i=0;i<adj_lst[indx1][indx2][indx3].size();i+=3) {
                if(vis[adj_lst[indx1][indx2][indx3].get(i)][adj_lst[indx1][indx2][indx3].get(i+1)][adj_lst[indx1][indx2][indx3].get(i+2)]) {
                   continue; 
                }
                vis[adj_lst[indx1][indx2][indx3].get(i)][adj_lst[indx1][indx2][indx3].get(i+1)][adj_lst[indx1][indx2][indx3].get(i+2)]=true;
                que.add(adj_lst[indx1][indx2][indx3].get(i));
                que.add(adj_lst[indx1][indx2][indx3].get(i+1));
                que.add(adj_lst[indx1][indx2][indx3].get(i+2));
                depth[adj_lst[indx1][indx2][indx3].get(i)][adj_lst[indx1][indx2][indx3].get(i+1)][adj_lst[indx1][indx2][indx3].get(i+2)]=1+depth[indx1][indx2][indx3];
            }
        }
    }
    public static void add(int i,int j) {
        adj_lst[0][i][j].add(1);
        adj_lst[0][i][j].add(i);
        adj_lst[0][i][j].add(j);
        
        adj_lst[1][i][j].add(2);
        adj_lst[1][i][j].add(i);
        adj_lst[1][i][j].add(j);
        
        adj_lst[2][i][j].add(3);
        adj_lst[2][i][j].add(i);
        adj_lst[2][i][j].add(j);
        
        adj_lst[3][i][j].add(0);
        adj_lst[3][i][j].add(i);
        adj_lst[3][i][j].add(j);
        
        if(arr[i][j]=='*') {
            return;
        }
        if(arr[i][j]=='<') {
            add_1(i,j);
        }
        if(arr[i][j]=='^') {
            add_2(i,j);
        }
        if(arr[i][j]=='>') {
            add_3(i,j);
        }
        if(arr[i][j]=='V') {
            add_4(i,j);
        }
        if(arr[i][j]=='-') {
            add_1(i,j);
            add_3(i,j);
        }
        if(arr[i][j]=='|') {
            add_2(i,j);
            add_4(i,j);
        }
        if(arr[i][j]=='L') {
            add_2(i,j);
            add_3(i,j);
            add_4(i,j);
        }
        if(arr[i][j]=='R') {
            add_2(i,j);
            add_1(i,j);
            add_4(i,j);
        }
        if(arr[i][j]=='U') {
            add_3(i,j);
            add_1(i,j);
            add_4(i,j);
        }
        if(arr[i][j]=='D') {
            add_2(i,j);
            add_1(i,j);
            add_3(i,j);
        }
        if(arr[i][j]=='+') {
            add_1(i,j);
            add_2(i,j);
            add_3(i,j);
            add_4(i,j);
        }
    }
    
    public static void add_1(int i,int j) { //<
        add_lft(i,j,0);
        add_up(i,j,1);
        add_rgt(i,j,2);
        add_down(i,j,3);
    }
    
    public static void add_2(int i,int j) { //^
        add_lft(i,j,3);
        add_up(i,j,0);
        add_rgt(i,j,1);
        add_down(i,j,2);
    }
    
    public static void add_3(int i,int j) { //>
        add_lft(i,j,2);
        add_up(i,j,3);
        add_rgt(i,j,0);
        add_down(i,j,1);
    }
    
    public static void add_4(int i,int j) { //V
        add_lft(i,j,1);
        add_up(i,j,2);
        add_rgt(i,j,3);
        add_down(i,j,0);
    }
    
    public static void add_lft(int i,int j,int dep) {
        if(j!=0) {
            adj_lst[dep][i][j].add(dep);
            adj_lst[dep][i][j].add(i);
            adj_lst[dep][i][j].add(j-1);
        }
    }
    public static void add_rgt(int i,int j,int dep) {
        if(j!=m-1) {
            adj_lst[dep][i][j].add(dep);
            adj_lst[dep][i][j].add(i);
            adj_lst[dep][i][j].add(j+1);
        }
    }
    
    public static void add_up(int i,int j,int dep) {
        if(i!=0) {
            adj_lst[dep][i][j].add(dep);
            adj_lst[dep][i][j].add(i-1);
            adj_lst[dep][i][j].add(j);
        }
    }
    public static void add_down(int i,int j,int dep) {
        if(i!=n-1) {
            adj_lst[dep][i][j].add(dep);
            adj_lst[dep][i][j].add(i+1);
            adj_lst[dep][i][j].add(j);
        }
    }
}
