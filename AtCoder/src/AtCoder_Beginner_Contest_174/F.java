/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_174;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class F {
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
    
    
    
    static int MAX = 1000001; 
  
    // structure to store queries 
    static class Query  
    { 
        int l, r, idx; 
    } 
  
    // updating the bit array 
    static void update(int idx, int val,  
                        int bit[], int n)  
    { 
        for (; idx <= n; idx += idx & -idx) 
            bit[idx] += val; 
    } 
  
    // querying the bit array 
    static int query(int idx, int bit[], int n)  
    { 
        int sum = 0; 
        for (; idx > 0; idx -= idx & -idx) 
            sum += bit[idx]; 
        return sum; 
    } 
  
    static void answeringQueries(int[] arr, int n,  
                                Query[] queries, int q) 
    { 
  
        // initialising bit array 
        int[] bit = new int[n + 1]; 
        Arrays.fill(bit, 0); 
  
        // holds the rightmost index of any number 
        // as numbers of a[i] are less than or equal to 10^6 
        int[] last_visit = new int[MAX]; 
        Arrays.fill(last_visit, -1); 
  
        // answer for each query 
        int[] ans = new int[q]; 
        int query_counter = 0; 
        for (int i = 0; i < n; i++)  
        { 
  
            // If last visit is not -1 update -1 at the 
            // idx equal to last_visit[arr[i]] 
            if (last_visit[arr[i]] != -1) 
                update(last_visit[arr[i]] + 1, -1, bit, n); 
  
            // Setting last_visit[arr[i]] as i and updating 
            // the bit array accordingly 
            last_visit[arr[i]] = i; 
            update(i + 1, 1, bit, n); 
  
            // If i is equal to r of any query store answer 
            // for that query in ans[] 
            while (query_counter < q && queries[query_counter].r == i)  
            { 
                ans[queries[query_counter].idx] =  
                        query(queries[query_counter].r + 1, bit, n) 
                        - query(queries[query_counter].l, bit, n); 
                query_counter++; 
            } 
        } 
  
        // print answer for each query
        StringBuilder fin_ans=new StringBuilder("");
        for (int i = 0; i < q; i++) {
            fin_ans.append(ans[i]+"\n");
        }
        System.out.println(fin_ans);
    } 
  
    // Driver Code 
    public static void main(String[] args) throws IOException {  
        Scan input=new Scan();
        int n = input.scanInt();
        int query=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        Query[] queries = new Query[query]; 
        for (int i = 0; i < query; i++) {
            queries[i] = new Query(); 
            queries[i].l = input.scanInt()-1; 
            queries[i].r = input.scanInt()-1; 
            queries[i].idx = i; 
        }
        int q = queries.length; 
        Arrays.sort(queries, new Comparator<Query>()  
        { 
            public int compare(Query x, Query y) 
            { 
                if (x.r < y.r) 
                    return -1; 
                else if (x.r == y.r) 
                    return 0; 
                else
                    return 1; 
            } 
        }); 
        answeringQueries(arr, n, queries, q); 
    } 
}
