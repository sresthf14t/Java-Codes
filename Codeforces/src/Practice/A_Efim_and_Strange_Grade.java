/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
public class A_Efim_and_Strange_Grade {
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
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
//        System.out.println(findSum(new StringBuilder("9.515"),new StringBuilder("0.551")));
        
        
        int n=input.scanInt();
        int k=input.scanInt();    
        String str=input.scanString();
        StringBuilder num=new StringBuilder(str);
        int point=-1;
        int indx=-1;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='.') {
                point=i;
                break;
            }
        }
        for(int i=point+1;i<str.length();i++) {
            if(str.charAt(i)>='5') {
                k--;
                indx=i;
                break;
            }
        }
        for(int i=indx-1;i>=1 && k>0;i--) {
            if(str.charAt(i)=='.') {
                break;
            }
            if(str.charAt(i)=='4') {
                k--;
                indx=i;
            }
            else {
                break;
            }
        }
        if(indx==-1 || indx==0) {
            ans.append(num+"\n");
        }
        else {
            if(indx>point) {
                StringBuilder tmp=new StringBuilder("");
                tmp.append("0.");
                for(int i=point+1;i<indx;i++) {
                    tmp.append(0);
                }
                tmp.append(1);
//                System.out.println(tmp);
                StringBuilder add=new StringBuilder("0");
                for(int i=0;i<10;i++) {
                    add=findSum(new StringBuilder(""+add),new StringBuilder(""+tmp));
//                    System.out.println(i+" "+add);
                }
//                System.out.println(1+" "+indx+" "+add);
                num=findSum(new StringBuilder(""+num),new StringBuilder(""+add));
            }
            
//            System.out.println(indx+" "+num);
            num=new StringBuilder(""+num.substring(0, indx));
            if(num.charAt(num.length()-1)=='.') {
                num.deleteCharAt(num.length()-1);
            }
            ans.append(num+"\n");
        }
        System.out.print(ans);
    }
    
    
    
    static StringBuilder findSum(StringBuilder str1, StringBuilder str2) {
        
        int cnt1=-1,cnt2=-1;
        for(int i=str1.length()-1;i>=0;i--) {
            if(str1.charAt(i)=='.') {
                break;
            }
            cnt1++;
        }
        
        for(int i=str2.length()-1;i>=0;i--) {
            if(str2.charAt(i)=='.') {
                break;
            }
            cnt2++;
        }
        
        while(cnt1<cnt2) {
            str1.append(0);
            cnt1++;
        }
        while(cnt2<cnt1) {
            str2.append(0);
            cnt2++;
        }
        
        
    // Before proceeding further, make sure length
    // of str2 is larger.
    if (str1.length() > str2.length()){
        StringBuilder t = new StringBuilder(""+str1);
        str1 = new StringBuilder(""+str2);
        str2 = new StringBuilder(""+t);
    }
 
    // Take an empty String for storing result
    StringBuilder str = new StringBuilder("");
 
    // Calculate length of both String
    int n1 = str1.length(), n2 = str2.length();
 
    // Reverse both of Strings
    str1=str1.reverse();
    str2=str2.reverse();
 
    int carry = 0;
    for (int i = 0; i < n1; i++)
    {
        if(str2.charAt(i)=='.' ) {
            str.append('.');
            continue;
        }
        // Do school mathematics, compute sum of
        // current digits and carry
        int sum = ((int)(str1.charAt(i) - '0') +
                    (int)(str2.charAt(i) - '0') + carry);
        str.append((char)(sum % 10 + '0'));
 
        // Calculate carry for next step
        carry = sum / 10;
    }
 
    // Add remaining digits of larger number
    for (int i = n1; i < n2; i++)
    {
        if(str2.charAt(i)=='.' ) {
            str.append('.');
            continue;
        }
        int sum = ((int)(str2.charAt(i) - '0') + carry);
        str.append((char)(sum % 10 + '0'));
        carry = sum / 10;
    }
 
    // Add remaining carry
    if (carry > 0)
        str.append((char)(carry + '0'));
 
    // reverse resultant String
    str=str.reverse();
 
    return str;
}
}
