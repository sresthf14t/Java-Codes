/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D_Distinct_Characters_Queries {
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
    
    
    static class segment_tree {
        int seg_tree[];
        public segment_tree(int n,int arr[]) {
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
            seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
        }

        public int sum(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return 0;
            }

            if(ql==l && qr==r) {
                return seg_tree[vertex];
            }
            int mid=(l+r)/2;

            int total=0;

            //Left Child
            total+=sum((2*vertex)+1,l,mid,ql,Math.min(qr, mid));

            //Right Child
            total+=sum((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);

            return total;
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
            seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
        }
    }
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        String str=input.scanString();
        segment_tree seg[]=new segment_tree[26];
        for(int i=0;i<26;i++) {
            int arr[]=new int[str.length()];
            char chr=(char)(97+i);
            for(int j=0;j<arr.length;j++) {
                arr[j]=(str.charAt(j)==chr? 1:0);
            }
            seg[i]=new segment_tree(arr.length,arr);
        }
//        System.out.println(seg[0].sum(0,0,str.length()-1,0,str.length()-1));
        int query=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int q=1;q<=query;q++) {
            int type=input.scanInt();
            if(type==1) {
                int pos=input.scanInt()-1;
                int chr=input.scanString().charAt(0)-97;
                int curr=-1;
                for(int i=0;i<26;i++) {
                    if(seg[i].sum(0, 0, str.length()-1, pos, pos)==1) {
                        curr=i;
                        break;
                    }
                }
                seg[curr].update(0, 0, str.length()-1, pos, 0);
                seg[chr].update(0, 0, str.length()-1, pos, 1);
            }
            else {
                int l=input.scanInt()-1;
                int r=input.scanInt()-1;
                int cnt=0;
                for(int i=0;i<26;i++) {
                    if(seg[i].sum(0, 0, str.length()-1, l, r)>0) {
                        cnt++;
                    }
                }
                ans.append(cnt+"\n");
//                System.out.println(cnt);
            }
        }
        System.out.println(ans);
    }
}
