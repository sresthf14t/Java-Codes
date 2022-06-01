/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_669_Div_2;

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
    static int n,arr[],less[],greater[],dp[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
//        n=30000;
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
//            arr[i]=(int)(Math.random()*1000000000 +1);
        }
        less=smaller(arr,n);
        greater=greater(arr,n);
        dp=new int[n];
        Arrays.fill(dp, -1);
        System.out.println(solve(0));
//        for(int i=0;i<n;i++) {
//            System.out.print(dp[i]+" ");
//        }
//        System.out.println();
    }
    
    public static int solve(int indx) {
//        System.out.println(indx);
        if(indx==n) {
            return -1;
        }
        if(indx==n-1) {
            return 0;
        }
        if(dp[indx]!=-1) {
            return dp[indx];
        }
        int min=n;
        min=Math.min(min, 1+solve(indx+1));
        if(arr[indx+1]>arr[indx]) {
            int tmp=indx+1;
            while(true) {
                tmp=less[tmp];
                if(tmp==-1) {
                    break;
                }
                min=Math.min(min,1+solve(tmp));
                if(arr[tmp]<=arr[indx]) {
                    break;
                }
            }
        }
        if(arr[indx+1]<arr[indx]) {
            int tmp=indx+1;
            while(true) {
                tmp=greater[tmp];
                if(tmp==-1) {
                    break;
                }
                min=Math.min(min,1+solve(tmp));
                if(arr[tmp]>=arr[indx]) {
                    break;
                }
            }
        }
        dp[indx]=min;
        return min;
    }
    
    static int[] smaller(int arr[], int n) { 
        // Create an empty stack  
        Stack<Integer> S = new Stack<>(); 
        Stack<Integer> indx = new Stack<>();
        int tmp[]=new int[n];
        // Traverse all array elements  
        for (int i = n-1; i >=0 ; i--) { 
            // Keep removing top element from S while the top  
            // element is greater than or equal to arr[i]  
            while (!S.empty() && S.peek() >= arr[i]) { 
                S.pop(); 
                indx.pop();
            } 
  
            // If all elements in S were greater than arr[i]  
            if (S.empty()) { 
                tmp[i]=-1; 
            } else //Else print the nearest smaller element  
            { 
                tmp[i]=indx.peek(); 
            } 
  
            // Push this element  
            S.push(arr[i]); 
            indx.push(i);
        } 
        return tmp;
    }
    
    static int[] greater(int arr[], int n) { 
        // Create an empty stack  
        Stack<Integer> S = new Stack<>(); 
        Stack<Integer> indx = new Stack<>();
        int tmp[]=new int[n];
        // Traverse all array elements  
        for (int i = n-1; i >=0 ; i--) { 
            // Keep removing top element from S while the top  
            // element is greater than or equal to arr[i]  
            while (!S.empty() && S.peek() <= arr[i]) { 
                S.pop(); 
                indx.pop();
            } 
  
            // If all elements in S were greater than arr[i]  
            if (S.empty()) { 
                tmp[i]=-1; 
            } else //Else print the nearest smaller element  
            { 
                tmp[i]=indx.peek(); 
            } 
  
            // Push this element  
            S.push(arr[i]); 
            indx.push(i);
        } 
        return tmp;
    } 
}
