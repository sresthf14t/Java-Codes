/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package November_Challenge_2020_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class CONGRID {
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
    static boolean vis[][],check[][];
    static int n,lft[][],rgt[][],indx[][];
    static ArrayList<Character> path;
    public static void main(String args[]) throws IOException {
        long startTime = System.nanoTime(); 

//        Scanner input=new Scanner(new File("C:\\Users\\Srest\\Desktop\\inputs\\input_00.txt"));
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
//        n=input.nextInt();
        n=input.scanInt();
//        int k=input.nextInt();
        int k=input.scanInt();
        int x[]=new int[k];
        int y[]=new int[k];
        lft=new int[n][n];
        rgt=new int[n][n];
        indx=new int[n][n];
        check=new boolean[n][n];
        for(int i=0;i<k;i++) {
//            x[i]=input.nextInt()-1;
//            y[i]=input.nextInt()-1;
//            lft[x[i]][y[i]]=input.nextInt();
//            rgt[x[i]][y[i]]=input.nextInt();
            
            
            x[i]=input.scanInt()-1;
            y[i]=input.scanInt()-1;
            lft[x[i]][y[i]]=input.scanInt();
            rgt[x[i]][y[i]]=input.scanInt();
            check[x[i]][y[i]]=true;
            indx[x[i]][y[i]]=i+1;
        }
        vis=new boolean[n][n];
        int cnt=0;
        for(int i=0;i<k;i++) {
            long stopTime = System.nanoTime();
            if(stopTime - startTime>9750000000L) {
                break;
            }
            if(!check[x[i]][y[i]]) {
                continue;
            }
            StringBuilder tmp=BFS(x[i],y[i],-1,-1);
            if(tmp.length()!=0) {
                cnt++;
                ans.append((i+1)+" "+tmp);
                ans.append("\n");
            }
        }
        System.out.println(cnt+"\n"+ans);
//        System.out.println(0);
    }
    
    
    public static StringBuilder BFS(int src_x,int src_y,int target_i,int target_j) {
//        System.out.println("\tBFS");
        boolean vis_tmp[][]=new boolean[n][n];
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<n;j++) {
//                vis_tmp[i][j]=vis[i][j];
//            }
//        }
        int parent_i[][]=new int[n][n];
        int parent_j[][]=new int[n][n];
        Queue<Integer> que_i = new LinkedList<>();
        Queue<Integer> que_j = new LinkedList<>();
        Queue<Integer> dep = new LinkedList<>();
        dep.add(1);
        que_i.add(src_x);
        que_j.add(src_y);
        vis_tmp[src_x][src_y]=true;
        parent_i[src_x][src_y]=-1;
        parent_j[src_x][src_y]=-1;
        while(!que_i.isEmpty() && !que_j.isEmpty()) {
            int tmp_dep=dep.peek();
            if(tmp_dep>65) {
                que_i.poll();
                que_j.poll();
                dep.poll();
                continue;
            }
            int i=que_i.peek();
            int j=que_j.peek();
            if(check[i][j] && !(i==src_x && j==src_y)) {
                if(tmp_dep>=Math.max(lft[src_x][src_y],lft[i][j]) && tmp_dep<=Math.min(rgt[src_x][src_y], rgt[i][j])) {
                    StringBuilder tmp=chck(src_x,src_y,i,j,parent_i,parent_j);
                    return tmp;
                }
                que_i.poll();
                que_j.poll();
                dep.poll();
                continue;
            }
            if(i!=0 && !vis_tmp[i-1][j] && !vis[i-1][j]) {
                que_i.add(i-1);
                que_j.add(j);
                dep.add(tmp_dep+1);
                vis_tmp[i-1][j]=true;
                parent_i[i-1][j]=i;
                parent_j[i-1][j]=j;
            }
            if(i!=n-1 && !vis_tmp[i+1][j] && !vis[i+1][j]) {
                que_i.add(i+1);
                que_j.add(j);
                dep.add(tmp_dep+1);
                vis_tmp[i+1][j]=true;
                parent_i[i+1][j]=i;
                parent_j[i+1][j]=j;
            }
            if(j!=0 && !vis_tmp[i][j-1] && !vis[i][j-1]) {
                que_i.add(i);
                que_j.add(j-1);
                dep.add(tmp_dep+1);
                vis_tmp[i][j-1]=true;
                parent_i[i][j-1]=i;
                parent_j[i][j-1]=j;
            }
            if(j!=n-1 && !vis_tmp[i][j+1] && !vis[i][j+1]) {
                que_i.add(i);
                que_j.add(j+1);
                dep.add(tmp_dep+1);
                vis_tmp[i][j+1]=true;
                parent_i[i][j+1]=i;
                parent_j[i][j+1]=j;
            }
            que_i.poll();
            que_j.poll();
            dep.poll();
        }
//        System.out.println("\tPATH FOUND");
//        System.out.println(src_x+" "+src_y+" "+target_i+" "+target_j);
        return new StringBuilder("");
    }
    
    public static StringBuilder chck(int src_x,int src_y,int target_i,int target_j,int parent_i[][],int parent_j[][]) {
        StringBuilder path=new StringBuilder("");
        int i=target_i,j=target_j;
        vis[target_i][target_j]=true;
        vis[src_x][src_y]=true;
        check[i][j]=false;
        check[src_x][src_y]=false;
        while(!(i==src_x && j==src_y)) {
            if(parent_i[i][j]==i-1) {
                i--;
                path.append('D');
            }
            else if(parent_i[i][j]==i+1) {
                i++;
                path.append('U');
            }
            else if(parent_j[i][j]==j-1) {
                j--;
                path.append('R');
            }
            else if(parent_j[i][j]==j+1) {
                j++;
                path.append('L');
            }
            vis[i][j]=true;
        }
        path.reverse();
        path.insert(0, indx[target_i][target_j]+" ");
        return path;
    }
}
