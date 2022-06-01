/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unacademy_Teaching_Assistant_Hiring_Contest;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class ISITSQRT {
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
    
    
    
    static class seg_tree {
        int seg_tree[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public int max(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return 0;
            }

            if(ql==l && qr==r) {
                return seg_tree[vertex];
            }
            int mid=(l+r)/2;

            int max=Integer.MIN_VALUE;

            //Left Child
            max=Math.max(max,max((2*vertex)+1,l,mid,ql,Math.min(qr, mid)));

            //Right Child
            max=Math.max(max,max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));

            return max;
        }

        public void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
            if(l==r) {
                seg_tree[vertex]=value;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            if(pos<=mid) {
                update((2*vertex)+1,l,mid,pos,value);
            }
            //Right Child
            else {
                update((2*vertex)+2,mid+1,r,pos,value);
            }
            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }
    }
    
    
    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int q=input.scanInt();
            int quality[]=new int[n];
            long arr[]=new long[n];
            HashMap<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<n;i++) {
                quality[i]=input.scanInt();
                map.put(quality[i], i);
            }
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            seg_tree st=new seg_tree(n,quality);
            StringBuilder ans=new StringBuilder("");
            for(int i=0;i<q;i++) {
                int type=input.scanInt();
                if(type==1) {
                    int l=input.scanInt()-1;
                    int r=input.scanInt()-1;
                    int qt=input.scanInt();
                    int max=st.max(0, 0, n-1, l, r);
                    int indx=map.get(max);
                    arr[indx]+=qt;
                }
                else {
                    int v=input.scanInt();
                    int mid=input.scanInt();
                    int qt=input.scanInt();
                    int thr=input.scanInt();
                    int l=Math.max(1,mid-v)-1;
                    int r=Math.min(n, mid+v)-1;
                    int max=st.max(0, 0, n-1, l, r);
                    if(max<thr) {
                        ans.append("No purchase\n");
                    }
                    else {
                        int indx=map.get(max);
                        if(arr[indx]<qt) {
                            ans.append("No purchase\n");
                        }
                        else {
                            ans.append(indx+1);
                            ans.append("\n");
                            arr[indx]-=qt;
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
