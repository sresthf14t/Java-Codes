/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_611_Div_3_Virtual;

/**
 *
 * @author User
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        Arrays.sort(arr);
        TreeSet<Integer> rgt=new TreeSet<>();
        TreeSet<Integer> lft=new TreeSet<>();
        HashSet<Integer> hashset=new HashSet<>();
        for(int i=0;i<n;i++) {
            rgt.add(arr[i]);
            lft.add(arr[i]);
            hashset.add(arr[i]);
        }
        ArrayList<Integer> app=new ArrayList<>();
        ArrayList<Integer> ans=new ArrayList<>();
        while(m!=0) {
            for(int i:lft) {
                if(hashset.contains(i-1)) {
                    continue;
                }
                ans.add(i-1);
                hashset.add(i-1);
                app.add(i-1);
                m--;
                if(m==0) {
                    break;
                }
            }
            if(m==0) {
                break;
            }
            lft.clear();
            while(!app.isEmpty()) {
                lft.add(app.get(0));
                app.remove(0);
            }
            
            
            for(int i:rgt) {
                if(hashset.contains(i+1)) {
                    continue;
                }
                ans.add(i+1);
                hashset.add(i+1);
                app.add(i+1);
                m--;
                if(m==0) {
                    break;
                }
            }
            rgt.clear();
            while(!app.isEmpty()) {
                rgt.add(app.get(0));
                app.remove(0);
            }
        }
        long sum=0;
        StringBuilder fin=new StringBuilder("");
        for(int i=0;i<ans.size();i++) {
            int less=less(arr,ans.get(i));
            int grt=grt(arr,ans.get(i));
//            System.out.println(ans.get(i)+" "+less+" "+grt);
            if(less==-1) {
                sum+=Math.abs(ans.get(i)-arr[grt]);
            }
            else if(grt==-1) {
                sum+=Math.abs(ans.get(i)-arr[less]);
            }
            else {
                sum+=Math.min(Math.abs(ans.get(i)-arr[less]),Math.abs(ans.get(i)-arr[grt]));
            }
            fin.append(ans.get(i)+" ");
        }
        System.out.println(sum+"\n"+fin);
    }
    public static int less(int[] arr, int target) {  
        int start = 0, end = arr.length-1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to the left side if the target is smaller  
            if (arr[mid] >= target) {  
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
    
    public static int grt(int[] arr, int target) {  
        int start = 0, end = arr.length - 1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to right side if target is  
            // greater.  
            if (arr[mid] <= target) {  
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
