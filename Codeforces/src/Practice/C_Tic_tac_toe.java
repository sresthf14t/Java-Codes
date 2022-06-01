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
public class C_Tic_tac_toe {
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
    static char arr[][],tmp[][];
    static StringBuilder ans=new StringBuilder("");
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        arr=new char[3][3];
        tmp=new char[3][3];
        for(int i=0;i<3;i++) {
            String str=input.scanString();
            for(int j=0;j<3;j++) {
                arr[i][j]=str.charAt(j);
                tmp[i][j]='.';
            }
        }
        ans=null;
        solve(true);
        if(ans==null) {
            ans=new StringBuilder("illegal");
        }
        System.out.println(ans);
    }
    public static void solve(boolean fst) {
        if(ans!=null) {
            return;
        }
        int check=check();
        if(check==1 && match()) {
            ans=new StringBuilder("the first player won");
        }
        if(check==2 && match()) {
            ans=new StringBuilder("the second player won");
        }
        if(check==1 || check==2) {
            return;
        }
        if(match()) {
            if(full()) {
                ans=new StringBuilder("draw");
                return;
            }
            if(fst) {
                ans=new StringBuilder("first");
                return;
            }
            ans=new StringBuilder("second");
            return;
        }
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(tmp[i][j]!='.') {
                    continue;
                }
                if(fst) {
                    tmp[i][j]='X';
                }
                else {
                    tmp[i][j]='0';
                }
                solve(!fst);
                tmp[i][j]='.';
            }
        }
    }
    public static int check() {
        for(int i=0;i<3;i++) {
            boolean done=true;
            for(int j=0;j<3;j++) {
                if(tmp[i][0]!=tmp[i][j]) {
                    done=false;
                    break;
                }
            }
            if(done) {
                if(tmp[i][0]=='X') {
                    return 1;
                }
                if(tmp[i][0]=='0') {
                    return 2;
                }
            }
        }
        
        for(int i=0;i<3;i++) {
            boolean done=true;
            for(int j=0;j<3;j++) {
                if(tmp[0][i]!=tmp[j][i]) {
                    done=false;
                    break;
                }
            }
            if(done) {
                if(tmp[0][i]=='X') {
                    return 1;
                }
                if(tmp[0][i]=='0') {
                    return 2;
                }
            }
        }
        
        if(tmp[0][0]==tmp[1][1] && tmp[1][1]==tmp[2][2]) {
            if(tmp[0][0]=='X') {
                return 1;
            }
            if(tmp[0][0]=='0') {
                return 2;
            }
        }
        if(tmp[0][2]==tmp[1][1] && tmp[1][1]==tmp[2][0]) {
            if(tmp[0][2]=='X') {
                return 1;
            }
            if(tmp[0][2]=='0') {
                return 2;
            }
        }
        return 0;
    }
    
    public static boolean match() {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(arr[i][j]!=tmp[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean full() {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(tmp[i][j]=='.') {
                    return false;
                }
            }
        }
        return true;
    }
}
