/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_791_Div_2;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class C {
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
    
    
    public static class seg_tree_row {
        int seg_tree[],lazy[];
        boolean val_in_lazy[];
        public seg_tree_row(int n,int arr[]) {
            seg_tree=new int[4*n];
            lazy=new int[4*n];
            val_in_lazy=new boolean[4*n];
            built(arr,0,0,n-1);
        }

        public void built(int arr[], int vertex, int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;

            //Left Child
            built(arr,(2*vertex)+1, l, mid);

            //Right Child
            built(arr,(2*vertex)+2, mid+1, r);

            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public void push(int vertex) {
            
            if(!val_in_lazy[vertex]) {
                return;
            }
            
            seg_tree[(2*vertex)+1]=lazy[vertex];
            lazy[(2*vertex)+1]=lazy[vertex];

            seg_tree[(2*vertex)+2]=lazy[vertex];
            lazy[(2*vertex)+2]=lazy[vertex];
            
            lazy[vertex]=0;
            
            val_in_lazy[vertex]=false;
            val_in_lazy[(2*vertex)+1]=true;
            val_in_lazy[(2*vertex)+2]=true;
        }

        public void update(int vertex,int l,int r,int ql,int qr,int add) {

            if(ql>qr) {
                return;
            }
            if(l==ql && r==qr) {
                seg_tree[vertex]=add;
                lazy[vertex]=add;
                val_in_lazy[vertex]=true;
                return;
            }
            push(vertex);

            int mid=(l+r)/2;
            //Left Child
            update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
            //Right Child
            update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);

            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public int find_min(int vertex,int l,int r,int ql,int qr) {
    //        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
            if(ql>qr) {
                return Integer.MAX_VALUE;
            }
            if(ql<=l && r<=qr) {
                return seg_tree[vertex];
            }
            push(vertex);
            int mid=(l+r)/2;
            return Math.min(find_min((2*vertex)+1,l,mid,ql,Math.min(qr,mid)),find_min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));
        }
        
        public void print() {
            for(int i=0;i<seg_tree.length;i++) {
                System.out.print(seg_tree[i]+" ");
            }
            System.out.println();
            for(int i=0;i<lazy.length;i++) {
                System.out.print(lazy[i]+" ");
            }
            System.out.println();
        }
    }
    
    
    
    
    public static class seg_tree_col {
        int seg_tree[],lazy[];
        boolean val_in_lazy[];
        public seg_tree_col(int n,int arr[]) {
            seg_tree=new int[4*n];
            lazy=new int[4*n];
            val_in_lazy=new boolean[4*n];
            built(arr,0,0,n-1);
        }

        public void built(int arr[], int vertex, int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;

            //Left Child
            built(arr,(2*vertex)+1, l, mid);

            //Right Child
            built(arr,(2*vertex)+2, mid+1, r);

            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public void push(int vertex) {
            
            if(!val_in_lazy[vertex]) {
                return;
            }
            
            seg_tree[(2*vertex)+1]=lazy[vertex];
            lazy[(2*vertex)+1]=lazy[vertex];

            seg_tree[(2*vertex)+2]=lazy[vertex];
            lazy[(2*vertex)+2]=lazy[vertex];
            
            lazy[vertex]=0;
            
            val_in_lazy[vertex]=false;
            val_in_lazy[(2*vertex)+1]=true;
            val_in_lazy[(2*vertex)+2]=true;
        }

        public void update(int vertex,int l,int r,int ql,int qr,int add) {

            if(ql>qr) {
                return;
            }
            if(l==ql && r==qr) {
                seg_tree[vertex]=add;
                lazy[vertex]=add;
                val_in_lazy[vertex]=true;
                return;
            }
            push(vertex);

            int mid=(l+r)/2;
            //Left Child
            update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
            //Right Child
            update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);

            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public int find_min(int vertex,int l,int r,int ql,int qr) {
    //        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
            if(ql>qr) {
                return Integer.MAX_VALUE;
            }
            if(ql<=l && r<=qr) {
                return seg_tree[vertex];
            }
            push(vertex);
            int mid=(l+r)/2;
            return Math.min(find_min((2*vertex)+1,l,mid,ql,Math.min(qr,mid)),find_min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));
        }
        
        public void print() {
            for(int i=0;i<seg_tree.length;i++) {
                System.out.print(seg_tree[i]+" ");
            }
            System.out.println();
            for(int i=0;i<lazy.length;i++) {
                System.out.print(lazy[i]+" ");
            }
            System.out.println();
        }
    }

    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        int n=input.scanInt();
        int q=input.scanInt();
        seg_tree_row str=new seg_tree_row(n,new int[n]);
        seg_tree_col stc=new seg_tree_col(n,new int[n]);
        
//        ArrayList<Integer> arrli=new ArrayList<>();
//        int arr[][]=new int[n][n];
        for(int qq=1;qq<=q;qq++) {
            int type=input.scanInt();
//            int type=(int)(Math.random()*100);
//            type%=3;
//            type++;
            if(type==1) {
                int x=input.scanInt()-1;
                int y=input.scanInt()-1;
//                int x=(int)(Math.random()*n);
//                if(x==n) {
//                    x--;
//                }
//                int y=(int)(Math.random()*n);
//                if(y==n) {
//                    y--;
//                }
//                if(arr[x][y]==1) {
//                    continue;
//                }
//                arr[x][y]++;
                str.update(0, 0, n-1, x, x, str.find_min(0, 0, n-1, x, x)+1);
                stc.update(0, 0, n-1, y, y, stc.find_min(0, 0, n-1, y, y)+1);
            }
            if(type==2) {
                int x=input.scanInt()-1;
                int y=input.scanInt()-1;
//                int x=(int)(Math.random()*n);
//                if(x==n) {
//                    x--;
//                }
//                int y=(int)(Math.random()*n);
//                if(y==n) {
//                    y--;
//                }
//                if(arr[x][y]!=1) {
//                    continue;
//                }
//                arr[x][y]--;
                str.update(0, 0, n-1, x, x, str.find_min(0, 0, n-1, x, x)-1);
                stc.update(0, 0, n-1, y, y, stc.find_min(0, 0, n-1, y, y)-1);
            }
            if(type==3) {
                int x1=input.scanInt()-1;
                int y1=input.scanInt()-1;
                int x2=input.scanInt()-1;
                int y2=input.scanInt()-1;
                
//                int x1=(int)(Math.random()*n);
//                if(x1==n) {
//                    x1--;
//                }
//                int y1=(int)(Math.random()*n);
//                if(y1==n) {
//                    y1--;
//                }
//                
//                int x2=(int)(Math.random()*n);
//                if(x2==n) {
//                    x2--;
//                }
//                int y2=(int)(Math.random()*n);
//                if(y2==n) {
//                    y2--;
//                }
//                if(x1>x2) {
//                    int tmp=x1;
//                    x1=x2;
//                    x2=tmp;
//                }
//                if(y1>y2) {
//                    int tmp=y1;
//                    y1=y2;
//                    y2=tmp;
//                }
                if(str.find_min(0, 0, n-1, Math.min(x1, x2), Math.max(x1, x2))>0 || stc.find_min(0, 0, n-1, Math.min(y1, y2), Math.max(y1, y2))>0) {
                    
//                    if(!check(n,arr,x1,y1,x2,y2)) {
//                        System.out.println("yes "+x1+" "+y1+" "+x2+" "+y2+"\n");
//                        for(int i=0;i<n;i++) {
//                            for(int j=0;j<n;j++) {
//                                System.out.print(arr[i][j]);
//                            }
//                            System.out.println();
//                        }
//                        System.out.println();
//                        for(int i=0;i<n;i++) {
//                            System.out.print(str.find_min(0, 0, n-1, i, i)+" ");
//                        }
//                        System.out.println();
//                        for(int i=0;i<n;i++) {
//                            System.out.print(stc.find_min(0, 0, n-1, i, i)+" ");
//                        }
//                        System.out.println();
//                    }
                    ans.append("Yes\n");
                }
                else {
//                    if(check(n,arr,x1,y1,x2,y2)) {
//                        System.out.println("No "+x1+" "+y1+" "+x2+" "+y2+"\n");
//                        for(int i=0;i<n;i++) {
//                            for(int j=0;j<n;j++) {
//                                System.out.print(arr[i][j]);
//                            }
//                            System.out.println();
//                        }
//                        System.out.println();
//                        for(int i=0;i<n;i++) {
//                            System.out.print(str.find_min(0, 0, n-1, i, i)+" ");
//                        }
//                        System.out.println();
//                        for(int i=0;i<n;i++) {
//                            System.out.print(stc.find_min(0, 0, n-1, i, i)+" ");
//                        }
//                        System.out.println();
//                    }
                    ans.append("No\n");
                }
            }
        }
        
        System.out.print(ans);
    }
    
    public static boolean check(int n, int arr[][],int x1,int y1,int x2,int y2) {
        for(int i=x1;i<=x2;i++) {
            for(int j=y1;j<=y2;j++) {
                boolean is_pos=false;
                for(int k=0;k<n;k++) {
                    if(arr[i][k]>0 || arr[k][j]>0) {
                        is_pos=true;
                        break;
                    }
                }
                if(!is_pos) {
                    return false;
                }
            }
        }
        return true;
    }
}
