/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hack_the_Interview_VI_Asia_Pacific;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D {
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
    static int seg_tree[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int n=input.scanInt();
        int m=input.scanInt();
        int s=input.scanInt();
        int q=input.scanInt();
        int arr[]=new int[m];
        Set<Integer> hashset=new HashSet<>();
        for(int i=0;i<m;i++) {
            arr[i]=input.scanInt();
            hashset.add(arr[i]);
        }
        for(int i=0;i<s;i++) {
            int u=input.scanInt();
            int v=input.scanInt();
            if(hashset.contains(u) && !hashset.contains(v)) {
                hashset.remove(u);
                hashset.add(v);
            }
            else if(!hashset.contains(u) && hashset.contains(v)) {
                hashset.remove(v);
                hashset.add(u);
            }
        }
        int indx=0;
        for(int i:hashset) {
            arr[indx]=i;
            indx++;
        }
        Arrays.sort(arr);
//        for(int i=0;i<m;i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
        for(int i=0;i<q;i++) {
            int l=input.scanInt();
            int r=input.scanInt();
            int lft=jgt(arr,l);
            int rgt=jlt(arr,r);
            if(lft==-1 || rgt==-1) {
                ans.append(0+" ");
                continue;
            }
            ans.append((rgt-lft+1)+" ");
        }
        System.out.println(ans);
    }
    
    static int jlt(int[] arr, int target)  
    {  
        int start = 0, end = arr.length-1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to the left side if the target is smaller  
            if (arr[mid] > target) {  
                end = mid - 1;  
            }  
    
            // Move right side  
            else {  
                ans = mid;  
                start = mid + 1;  
            }  
        }  
        return ans;  
    }  
    
    static int jgt(int[] arr, int target)  
    {  
        int start = 0, end = arr.length - 1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to right side if target is  
            // greater.  
            if (arr[mid] < target) {  
                start = mid + 1;  
            }  
    
            // Move left side.  
            else {  
                ans = mid;  
                end = mid - 1;  
            }  
        }  
        return ans;  
    }  
      
}
