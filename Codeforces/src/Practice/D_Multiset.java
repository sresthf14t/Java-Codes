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
public class D_Multiset {
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

    static int n,arr[],seg_tree[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        int q=input.scanInt();
        arr=new int[n+1];
        seg_tree=new int[4*arr.length];
        for(int i=0;i<n;i++) {
            arr[input.scanInt()-1]++;
        }
        create_seg_tree(0,0,arr.length-1);
        for(int i=0;i<q;i++) {
            int ele=input.scanInt();
            if(ele>0) {
                ele--;
                arr[ele]++;
                update(0,0,arr.length-1,ele,1);
            }
            else {
                ele*=-1;
                int indx=get_indx(0,0,arr.length-1,ele);
                
                arr[indx]--;
                update(0,0,arr.length-1,indx,-1);
//                System.out.println("indx="+indx+" "+arr[indx]);
            }
//            for(int j=0;j<arr.length;j++) {
//                System.out.print(arr[j]+" ");
//            }
//            System.out.println();
        }
        int ans=0;
        for(int i=0;i<arr.length;i++) {
//            System.out.print(arr[i]+" ");
            if(arr[i]>0) {
//                System.out.println(arr[i]);
                ans=i+1;
                break;
            }
        }
        System.out.println(ans);
    }
    public static void create_seg_tree(int vertex,int l,int r) {
        if(l==r) {
            seg_tree[vertex]=arr[r];
            return;
        }
        int mid=(l+r)/2;
        //Left Child
        create_seg_tree((2*vertex)+1,l,mid);
        //Right Child
        create_seg_tree((2*vertex)+2,mid+1,r);
        //Filling this node
        seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
    }
    public static void update(int vertex,int l,int r,int pos,int add) {
        if(l==r) {
            seg_tree[vertex]+=add;
            return;
        }
        int mid=(l+r)/2;
        //Left Child
        if(pos<=mid) {
            update((2*vertex)+1,l,mid,pos,add);
        }
        else {
            update((2*vertex)+2,mid+1,r,pos,add);
        }
        seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
    }
    public static int get_indx(int vertex,int l,int r,int sum) {
//        System.out.println("srg_tree="+vertex+" "+l+" "+r+" "+sum+" "+seg_tree[(2*vertex)+1]+" "+seg_tree[(2*vertex)+2]);
        if(l==r) {
            return r;
        }
        int mid=(l+r)/2;
        int lft=seg_tree[(2*vertex)+1];
        int rgt=seg_tree[(2*vertex)+2];
        if(sum<=lft) {
            return get_indx((2*vertex)+1,l,mid,sum);
        }
        else {
            sum-=lft;
            return get_indx((2*vertex)+2,mid+1,r,sum);
        }
    }
}
