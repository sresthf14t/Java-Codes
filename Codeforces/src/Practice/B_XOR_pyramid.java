/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class B_XOR_pyramid {
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
    
    static class seg_tree {
        int seg_tree[][];
        public seg_tree(int rows,int n,int arr[][]) {
            seg_tree=new int[rows][4*n];
            for(int i=0;i<rows;i++) {
                create_seg_tree(arr[i],0,0,n-1,i);
            }
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r,int row) {
            if(l==r) {
                seg_tree[row][vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid,row);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r,row);
            //Filling this node
            seg_tree[row][vertex]=Math.max(seg_tree[row][(2*vertex)+1],seg_tree[row][(2*vertex)+2]);
        }

        public int max(int vertex,int l,int r,int ql,int qr,int row) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return Integer.MIN_VALUE;
            }

            if(ql==l && qr==r) {
                return seg_tree[row][vertex];
            }
            int mid=(l+r)/2;

            int max=Integer.MIN_VALUE;

            //Left Child
            max=Math.max(max,max((2*vertex)+1,l,mid,ql,Math.min(qr, mid),row));

            //Right Child
            max=Math.max(max,max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,row));

            return max;
        }

        public void update(int vertex,int l,int r,int pos,int value,int row) {   //pos->Position of the update   value->updates value
            if(l==r) {
                seg_tree[row][vertex]=value;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            if(pos<=mid) {
                update((2*vertex)+1,l,mid,pos,value,row);
            }
            //Right Child
            else {
                update((2*vertex)+2,mid+1,r,pos,value,row);
            }
            seg_tree[row][vertex]=Math.max(seg_tree[row][(2*vertex)+1],seg_tree[row][(2*vertex)+2]);
        }
    }

    static int dp[][];
    static int n,xor[][];
    static seg_tree st;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        n=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
//            arr[i]=(int)(Math.random()*10000);
        }
        int tmp1[]=new int[n];
        xor=new int[n][n];
        for(int i=0;i<n;i++) {
            ArrayList<Integer> arrli=new ArrayList<>();
            arrli.add(0);
            if(i==0) {
                tmp1[0]=1;
            }
            for(int j=i;j>0;j--) {
                tmp1[j]^=tmp1[j-1];
                if(tmp1[j]==1) {
                    arrli.add(j);
                }
            }
            
            
            for(int j=0;j<n;j++) {
                int fin=0;
                if(j+i>=n) {
                    break;
                }
                for(int k=0;k<arrli.size();k++) {
                    fin^=arr[j+arrli.get(k)];
                }
                xor[j][j+i]=fin;
                System.out.println(j+" "+(j+i)+" "+xor[j][j+i]);
            }
        }
        st=new seg_tree(n,n,xor);
        
        dp=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=i;j<n;j++) {
                dp[i][j]=-1;
            }
        }
//        System.exit(0);
        int qqq=input.scanInt();
        for(int qq=0;qq<qqq;qq++) {
            int l=input.scanInt()-1;
            int r=input.scanInt()-1;
//            int l=(int)(Math.random()*n);
//            int r=(int)(Math.random()*n);
            if(l>r) {
                int tmp=l;
                l=r;
                r=tmp;
            }
            ans.append(solve(l,r)+"\n");
        }
        
        System.out.print(ans);
    }
    
    public static void print(int n,int arr[]) {
        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
    public static int solve(int l,int r) {
        if(l==r) {
            return xor[l][r];
        }
        if(dp[l][r]!=-1) {
            return dp[l][r];
        }
        int ans=Math.max(st.max(0, 0, n-1, l, r,l), solve(l+1,r));
        dp[l][r]=ans;
        return ans;
    }
    
    public static int bf(int arr[],int l,int r) {
        int max=0;
        for(int i=l;i<=r;i++) {
            for(int j=i;j<=r;j++) {
                if(calc(arr,i,j)>max) {
                    max=calc(arr,i,j);
                    System.out.println(i+" "+j);
                }
//                max=Math.max(max,calc(arr,i,j));
            }
        }
        return max;
    }
    public static int calc(int arr[],int l,int r) {
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=l;i<=r;i++) {
            arrli.add(arr[i]);
        }
        while(arrli.size()>1) {
            ArrayList<Integer> tmp=new ArrayList<>();
            for(int i=0;i<arrli.size()-1;i++) {
                tmp.add(arrli.get(i)^arrli.get(i+1));
            }
            arrli=tmp;
        }
        return arrli.get(0);
    }
    public static int get(int arr[],int l,int r) {
        return arr[r]^(l==0?0:arr[l-1]);
    }
}
