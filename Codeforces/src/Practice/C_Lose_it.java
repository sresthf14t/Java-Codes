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
import java.io.*; 
import java.util.*; 
public class C_Lose_it {
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
        int arr[]=new int[n];
        Stack<Integer> _4=new Stack<>();
        Stack<Integer> _8=new Stack<>();
        Stack<Integer> _15=new Stack<>();
        Stack<Integer> _16=new Stack<>();
        Stack<Integer> _23=new Stack<>();
        Stack<Integer> _42=new Stack<>();
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            if(arr[i]==4) {
                _4.push(i);
            }
            if(arr[i]==8) {
                _8.push(i);
            }
            if(arr[i]==15) {
                _15.push(i);
            }
            if(arr[i]==16) {
                _16.push(i);
            }
            if(arr[i]==23) {
                _23.push(i);
            }
            if(arr[i]==42) {
                _42.push(i);
            }
        }
        int size=0;
        while(true) {
            if(_4.isEmpty() || _8.isEmpty() || _15.isEmpty() || _16.isEmpty() || _23.isEmpty() || _42.isEmpty()) {
                break;
            }
            while(!_8.isEmpty() && _4.peek()>_8.peek()) {
                _8.pop();
            }
            if(_4.isEmpty() || _8.isEmpty() || _15.isEmpty() || _16.isEmpty() || _23.isEmpty() || _42.isEmpty()) {
                break;
            }
            while(!_15.isEmpty() && _8.peek()>_15.peek()) {
                _15.pop();
            }
            if(_4.isEmpty() || _8.isEmpty() || _15.isEmpty() || _16.isEmpty() || _23.isEmpty() || _42.isEmpty()) {
                break;
            }
            while(!_16.isEmpty() && _15.peek()>_16.peek()) {
                _16.pop();
            }
            if(_4.isEmpty() || _8.isEmpty() || _15.isEmpty() || _16.isEmpty() || _23.isEmpty() || _42.isEmpty()) {
                break;
            }
            while(!_23.isEmpty() && _16.peek()>_23.peek()) {
                _23.pop();
            }
            if(_4.isEmpty() || _8.isEmpty() || _15.isEmpty() || _16.isEmpty() || _23.isEmpty() || _42.isEmpty()) {
                break;
            }
            while(!_42.isEmpty() && _23.peek()>_42.peek()) {
                _42.pop();
            }
            if(_4.isEmpty() || _8.isEmpty() || _15.isEmpty() || _16.isEmpty() || _23.isEmpty() || _42.isEmpty()) {
                break;
            }
            size+=6;
        }
        System.out.println(n-size);
    }
}
