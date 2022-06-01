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
public class B_Interesting_Array {
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

    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int arr[]=new int[n];
        int pow[]=new int[30];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        int l[]=new int[m];
        int r[]=new int[m];
        int x[]=new int[m];
        for(int i=0;i<m;i++) {
            l[i]=input.scanInt()-1;
            r[i]=input.scanInt()-1;
            x[i]=input.scanInt();
        }
        
        int seg[][]=new int[30][n];
        for(int i=0;i<m;i++) {
            
            for(int j=0;j<30;j++) {
                if((x[i]&pow[j])!=0) {
                    seg[j][l[i]]+=1;
                    if(r[i]!=n-1) {
                        seg[j][r[i]+1]+=-1;
                    }
                }
            }
        }
        
        for(int i=0;i<30;i++) {
            int sum=0;
            for(int j=0;j<n;j++) {
                sum+=seg[i][j];
                if(sum>0) {
                    arr[j]+=pow[i];
                }
            }
        }
        
        and_tree and=new and_tree(n,arr);
        boolean is_pos=true;
        for(int i=0;i<m;i++) {
            if(and.sum(0, 0, n-1, l[i], r[i])!=x[i]) {
//                System.out.println(l[i]+" "+r[i]+" "+and.sum(0, 0, n-1, l[i], r[i]));
                is_pos=false;
                break;
            }
        }
        if(!is_pos) {
            System.out.println("NO");
            return;
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans.append(arr[i]+" ");
        }
        System.out.println("YES\n"+ans);
    }
    
}



class and_tree {
    int seg_tree[];
    public and_tree(int n,int arr[]) {
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
        seg_tree[vertex]=seg_tree[(2*vertex)+1]&seg_tree[(2*vertex)+2];
    }
    
    public int sum(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
        if(ql>qr) {
            return Integer.MAX_VALUE;
        }
        
        if(ql==l && qr==r) {
            return seg_tree[vertex];
        }
        int mid=(l+r)/2;
        
        
        //Left Child
        int total=sum((2*vertex)+1,l,mid,ql,Math.min(qr, mid));
        
        //Right Child
        total&=sum((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
        
        return total;
    }
}

