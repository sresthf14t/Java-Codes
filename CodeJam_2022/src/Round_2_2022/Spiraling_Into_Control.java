/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_2_2022;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class Spiraling_Into_Control {
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
    
    static LinkedList<Integer> fin;
    static int n,arr[][],dp[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {  
            fin=null;
            n=input.scanInt();
            int k=input.scanInt();
            arr=spiral(n);
            LinkedList<Integer> que=new LinkedList<>();
            dp=new int[n+5][n+5][(n+5)*(n+5)];
            solve(0,0,k,que);
            if(fin==null) {
                ans.append("Case #"+tt+": "+"IMPOSSIBLE"+"\n");
                continue;
            }
            int prev=que.removeFirst();
            int cnt=0;
            StringBuilder tmp=new StringBuilder("");
            while(!fin.isEmpty()) {
                int val=que.removeFirst();
                if(val>prev+1) {
                    tmp.append(prev+" "+val+"\n");
                    cnt++;
                }
                prev=val;
            }
            ans.append("Case #"+tt+": "+cnt+"\n"+tmp);
        }
        System.out.print(ans);
    }
    
    public static void solve(int i,int j,int rem,LinkedList<Integer> que) {
        que.addLast(arr[i][j]);
        if(rem==0) {
            if(arr[i][j]==(n*n)) {
                fin=que;
                return;
            }
            que.removeLast();
            return;
        }
        
        if(dp[i][j][rem]!=0) {
            que.removeLast();
            return;
        }
        if(fin!=null) {
            return;
        }
        if(i!=0 && arr[i-1][j]>arr[i][j]) {
            solve(i-1,j,rem-1,que);
        }
        if(fin!=null) {
            return;
        }
        
        if(i!=n-1 && arr[i+1][j]>arr[i][j]) {
            solve(i+1,j,rem-1,que);
        }
        if(fin!=null) {
            return;
        }
        
        if(j!=0 && arr[i][j-1]>arr[i][j]) {
            solve(i,j-1,rem-1,que);
        }
        if(fin!=null) {
            return;
        }
        
        if(j!=n-1 && arr[i][j+1]>arr[i][j]) {
            solve(i,j+1,rem-1,que);
        }
        if(fin!=null) {
            return;
        }
        
        dp[i][j][rem]=1;
        que.removeLast();
    }
    
    
    
    public static int[][] spiral(int size) {
 
        // Create row and col
        // to traverse rows and columns
        int row = 0, col = 0;
 
        int boundary = size - 1;
        int sizeLeft = size - 1;
        int flag = 1;
 
        // Variable to determine the movement
        // r = right, l = left, d = down, u = upper
        char move = 'r';
 
        // Array  for matrix
        int matrix[][] = new int[size][size];
 
        for (int i = 1; i < size * size + 1; i++) {
 
            // Assign the value
            matrix[row][col] = i;
 
            // switch-case to determine the next index
            switch (move) {
 
            // If right, go right
            case 'r':
                col += 1;
                break;
 
            // if left, go left
            case 'l':
                col -= 1;
                break;
 
            // if up, go up
            case 'u':
                row -= 1;
                break;
 
            // if down, go down
            case 'd':
                row += 1;
                break;
            }
 
            // Check if the matrix
            // has reached array boundary
            if (i == boundary) {
 
                // Add the left size for the next boundary
                boundary += sizeLeft;
 
                // If 2 rotations has been made,
                // decrease the size left by 1
                if (flag != 2) {
 
                    flag = 2;
                }
                else {
 
                    flag = 1;
                    sizeLeft -= 1;
                }
 
                // switch-case to rotate the movement
                switch (move) {
 
                // if right, rotate to down
                case 'r':
                    move = 'd';
                    break;
 
                // if down, rotate to left
                case 'd':
                    move = 'l';
                    break;
 
                // if left, rotate to up
                case 'l':
                    move = 'u';
                    break;
 
                // if up, rotate to right
                case 'u':
                    move = 'r';
                    break;
                }
            }
        }
 
        return matrix;
    }
}
