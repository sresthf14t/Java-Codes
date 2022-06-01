/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package July_Cook_Off_2020_Division_1;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class BOJACK {
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
        Scanner input=new Scanner(System.in);
//        int test=input.nextInt();
        int test=1;
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            solve(new StringBuilder(""),n);
        }
        System.out.println(ans);
    }
    public static boolean solve(StringBuilder str,int d) {
        if(str.length()>7*d) {
            return false;
        }
//        System.out.println(str);
        if(str.length()>0 && substr(str)-CountPS(str,str.length())==d) {
            System.out.println(substr(str));
            System.out.println(CountPS(str,str.length()));
            System.out.println(str);
            return true;
        }
        str.append('a');
        if(solve(str,d)) {
            return true;
        }
        str.deleteCharAt(str.length()-1);
        str.append('b');
        if(solve(str,d)) {
            return true;
        }
        str.deleteCharAt(str.length()-1);
        return false;
    }
    public static int substr(StringBuilder tmp) 
    { 
        String str=""+tmp;
        // Put all distinct substring in a HashSet 
        Set<String> result = new HashSet<String>(); 
  
        // List All Substrings 
        for (int i = 0; i <= str.length(); i++) { 
            for (int j = i+1; j <= str.length(); j++) { 
  
                // Add each substring in Set 
                result.add(str.substring(i, j)); 
            } 
        } 
  
        // Return size of the HashSet 
        return result.size(); 
    } 
    
    
    static int CountPS(StringBuilder str, int n) 
    { 
        // create empty 2-D matrix that counts all palindrome 
        // substring. dp[i][j] stores counts of palindromic 
        // substrings in st[i..j] 
        int dp[][] = new int[n][n]; 
       
        // P[i][j] = true if substring str[i..j] is palindrome, 
        // else false 
        boolean P[][] = new boolean[n][n]; 
       
        // palindrome of single length 
        for (int i= 0; i< n; i++) 
            P[i][i] = true; 
       
        // palindrome of length 2 
        for (int i=0; i<n-1; i++) 
        { 
            if (str.charAt(i) == str.charAt(i+1)) 
            { 
                P[i][i+1] = true; 
                dp[i][i+1] = 1 ; 
            } 
        } 
       
        // Palindromes of length more than 2. This loop is similar 
        // to Matrix Chain Multiplication. We start with a gap of 
        // length 2 and fill the DP table in a way that gap between 
        // starting and ending indexes increases one by one by 
        // outer loop. 
        for (int gap=2 ; gap<n; gap++) 
        { 
            // Pick starting point for current gap 
            for (int i=0; i<n-gap; i++) 
            { 
                // Set ending point 
                int j = gap + i; 
       
                // If current string is palindrome 
                if (str.charAt(i) == str.charAt(j) && P[i+1][j-1] ) 
                    P[i][j] = true; 
       
                // Add current palindrome substring ( + 1) 
                // and rest palindrome substring (dp[i][j-1] + dp[i+1][j]) 
                // remove common palindrome substrings (- dp[i+1][j-1]) 
                if (P[i][j] == true) 
                    dp[i][j] = dp[i][j-1] + dp[i+1][j] + 1 - dp[i+1][j-1]; 
                else
                    dp[i][j] = dp[i][j-1] + dp[i+1][j] - dp[i+1][j-1]; 
            } 
        } 
       
        // return total palindromic substrings 
        return dp[0][n-1]+n; 
    } 
}
