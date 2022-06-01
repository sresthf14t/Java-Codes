/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Challenge_2020_Division_1;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class REBIT_1 {

    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        long test=input.nextInt();
        long mod=998244353L;
        for(long t=1;t<=test;t++) {
            StringBuilder str=new StringBuilder(input.next());
            StringBuilder post=in_to_pos(str);
            if((""+post).equals("#")) {
                System.out.println("748683265 748683265 748683265 748683265");
                continue;
            }
            //a=0
            Stack<Long> num_1=new Stack<>();
            Stack<Long> den_1=new Stack<>();
            Stack<Long> num_0=new Stack<>();
            Stack<Long> den_0=new Stack<>();
            for(int i=0;i<post.length();i++) {
                if(post.charAt(i)=='#') {
                    num_1.push(-1L);
                    den_1.push(-1L);
                    num_0.push(-1L);
                    den_0.push(-1L);
                }
                else {
                    long a_num_1=num_1.pop(),a_den_1=den_1.pop();
                    long b_num_1=num_1.pop(),b_den_1=den_1.pop();
                    long a_num_0=num_0.pop(),a_den_0=den_0.pop();
                    long b_num_0=num_0.pop(),b_den_0=den_0.pop();
                    if(a_num_1==-1 && b_num_1==-1) {
                        long arr[]=prob(0,post.charAt(i));
                        num_1.push(arr[1]);
                        den_1.push(arr[0]+arr[1]);
                        num_0.push(arr[0]);
                        den_0.push(arr[0]+arr[1]);
                    }
                    
                    else if(a_num_1==-1) {
                        if(post.charAt(i)=='&') {
                            long tmp_0_num_1=(1*b_num_0)%mod;
                            long tmp_0_den_1=(4*b_den_0)%mod;
                            
                            long tmp_0_num_2=(1*b_num_1)%mod;
                            long tmp_0_den_2=(4*b_den_1)%mod;
                            
                            long tmp_0_num_3=(1*b_num_0)%mod;
                            long tmp_0_den_3=(4*b_den_0)%mod;
                            
                            long tmp_1_num_1=(1*b_num_1)%mod;
                            long tmp_1_den_1=(4*b_den_1)%mod;
                            
                            long[] tmp=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            tmp=add_frac(tmp_0_num_3,tmp_0_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp[0]);
                            den_0.push(tmp[1]);
                            num_1.push(tmp_1_num_1);
                            den_1.push(tmp_1_den_1);
                        }
                        if(post.charAt(i)=='|') {
                            long tmp_0_num_1=(1*b_num_0)%mod;
                            long tmp_0_den_1=(4*b_den_0)%mod;
                            
                            long tmp_1_num_1=(1*b_num_1)%mod;
                            long tmp_1_den_1=(4*b_den_1)%mod;
                            
                            long tmp_1_num_2=(1*b_num_0)%mod;
                            long tmp_1_den_2=(4*b_den_0)%mod;
                            
                            long tmp_1_num_3=(1*b_num_1)%mod;
                            long tmp_1_den_3=(4*b_den_1)%mod;
                            
                            long[] tmp=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            tmp=add_frac(tmp_1_num_3,tmp_1_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp_0_num_1);
                            den_0.push(tmp_0_den_1);
                            num_1.push(tmp[0]);
                            den_1.push(tmp[1]);
                        }
                        if(post.charAt(i)=='^') {
                            long tmp_0_num_1=(1*b_num_0)%mod;
                            long tmp_0_den_1=(4*b_den_0)%mod;
                            
                            long tmp_1_num_1=(1*b_num_1)%mod;
                            long tmp_1_den_1=(4*b_den_1)%mod;
                            
                            long tmp_1_num_2=(1*b_num_0)%mod;
                            long tmp_1_den_2=(4*b_den_0)%mod;
                            
                            long tmp_0_num_2=(1*b_num_1)%mod;
                            long tmp_0_den_2=(4*b_den_1)%mod;
                            
                            long[] tmp_0=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            long[] tmp_1=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            num_0.push(tmp_0[0]);
                            den_0.push(tmp_0[1]);
                            num_1.push(tmp_1[0]);
                            den_1.push(tmp_1[1]);
                        }
                    }
                    
                    else if(b_num_1==-1) {
                        
                        if(post.charAt(i)=='&') {
                            long tmp_0_num_1=(a_num_0*1)%mod;
                            long tmp_0_den_1=(a_den_0*4)%mod;
                            
                            long tmp_0_num_2=(a_num_0*1)%mod;
                            long tmp_0_den_2=(a_den_0*4)%mod;
                            
                            long tmp_0_num_3=(a_num_1*1)%mod;
                            long tmp_0_den_3=(a_den_1*4)%mod;
                            
                            long tmp_1_num_1=(a_num_1*1)%mod;
                            long tmp_1_den_1=(a_den_1*4)%mod;
                            
                            long[] tmp=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            tmp=add_frac(tmp_0_num_3,tmp_0_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp[0]);
                            den_0.push(tmp[1]);
                            num_1.push(tmp_1_num_1);
                            den_1.push(tmp_1_den_1);
                        }
                        if(post.charAt(i)=='|') {
                            long tmp_0_num_1=(a_num_0*1)%mod;
                            long tmp_0_den_1=(a_den_0*4)%mod;
                            
                            long tmp_1_num_1=(a_num_0*1)%mod;
                            long tmp_1_den_1=(a_den_0*4)%mod;
                            
                            long tmp_1_num_2=(a_num_1*1)%mod;
                            long tmp_1_den_2=(a_den_1*4)%mod;
                            
                            long tmp_1_num_3=(a_num_1*1)%mod;
                            long tmp_1_den_3=(a_den_1*4)%mod;
                            
                            long[] tmp=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            tmp=add_frac(tmp_1_num_3,tmp_1_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp_0_num_1);
                            den_0.push(tmp_0_den_1);
                            num_1.push(tmp[0]);
                            den_1.push(tmp[1]);
                        }
                        if(post.charAt(i)=='^') {
                            long tmp_0_num_1=(a_num_0*1)%mod;
                            long tmp_0_den_1=(a_den_0*4)%mod;
                            
                            long tmp_1_num_1=(a_num_0*1)%mod;
                            long tmp_1_den_1=(a_den_0*4)%mod;
                            
                            long tmp_1_num_2=(a_num_1*1)%mod;
                            long tmp_1_den_2=(a_den_1*4)%mod;
                            
                            long tmp_0_num_2=(a_num_1*1)%mod;
                            long tmp_0_den_2=(a_den_1*4)%mod;
                            
                            long[] tmp_0=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            long[] tmp_1=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            num_0.push(tmp_0[0]);
                            den_0.push(tmp_0[1]);
                            num_1.push(tmp_1[0]);
                            den_1.push(tmp_1[1]);
                        }
                        
                    }
                    else {
                        if(post.charAt(i)=='&') {
                            long tmp_0_num_1=(a_num_0*b_num_0)%mod;
                            long tmp_0_den_1=(a_den_0*b_den_0)%mod;
                            
                            long tmp_0_num_2=(a_num_0*b_num_1)%mod;
                            long tmp_0_den_2=(a_den_0*b_den_1)%mod;
                            
                            long tmp_0_num_3=(a_num_1*b_num_0)%mod;
                            long tmp_0_den_3=(a_den_1*b_den_0)%mod;
                            
                            long tmp_1_num_1=(a_num_1*b_num_1)%mod;
                            long tmp_1_den_1=(a_den_1*b_den_1)%mod;
                            
                            long[] tmp=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            tmp=add_frac(tmp_0_num_3,tmp_0_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp[0]);
                            den_0.push(tmp[1]);
                            num_1.push(tmp_1_num_1);
                            den_1.push(tmp_1_den_1);
                        }
                        if(post.charAt(i)=='|') {
                            long tmp_0_num_1=(a_num_0*b_num_0)%mod;
                            long tmp_0_den_1=(a_den_0*b_den_0)%mod;
                            
                            long tmp_1_num_1=(a_num_0*b_num_1)%mod;
                            long tmp_1_den_1=(a_den_0*b_den_1)%mod;
                            
                            long tmp_1_num_2=(a_num_1*b_num_0)%mod;
                            long tmp_1_den_2=(a_den_1*b_den_0)%mod;
                            
                            long tmp_1_num_3=(a_num_1*b_num_1)%mod;
                            long tmp_1_den_3=(a_den_1*b_den_1)%mod;
                            
                            long[] tmp=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            tmp=add_frac(tmp_1_num_3,tmp_1_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp_0_num_1);
                            den_0.push(tmp_0_den_1);
                            num_1.push(tmp[0]);
                            den_1.push(tmp[1]);
                        }
                        if(post.charAt(i)=='^') {
                            long tmp_0_num_1=(a_num_0*b_num_0)%mod;
                            long tmp_0_den_1=(a_den_0*b_den_0)%mod;
                            
                            long tmp_1_num_1=(a_num_0*b_num_1)%mod;
                            long tmp_1_den_1=(a_den_0*b_den_1)%mod;
                            
                            long tmp_1_num_2=(a_num_1*b_num_0)%mod;
                            long tmp_1_den_2=(a_den_1*b_den_0)%mod;
                            
                            long tmp_0_num_2=(a_num_1*b_num_1)%mod;
                            long tmp_0_den_2=(a_den_1*b_den_1)%mod;
                            
                            long[] tmp_0=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            long[] tmp_1=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            num_0.push(tmp_0[0]);
                            den_0.push(tmp_0[1]);
                            num_1.push(tmp_1[0]);
                            den_1.push(tmp_1[1]);
                        }
                    }
                }
            }

            long a_0_prob_0_num=num_0.pop();
            long a_0_prob_0_den=den_0.pop();
            long a_0_prob_1_num=num_1.pop();
            long a_0_prob_1_den=den_1.pop();


            //a=1
            num_1=new Stack<>();
            den_1=new Stack<>();
            num_0=new Stack<>();
            den_0=new Stack<>();
            for(int i=0;i<post.length();i++) {
                if(post.charAt(i)=='#') {
                    num_1.push(-1L);
                    den_1.push(-1L);
                    num_0.push(-1L);
                    den_0.push(-1L);
                }
                else {
                    long a_num_1=num_1.pop(),a_den_1=den_1.pop();
                    long b_num_1=num_1.pop(),b_den_1=den_1.pop();
                    long a_num_0=num_0.pop(),a_den_0=den_0.pop();
                    long b_num_0=num_0.pop(),b_den_0=den_0.pop();
                    if(a_num_1==-1 && b_num_1==-1) {
                        long arr[]=prob(1,post.charAt(i));
                        num_1.push(arr[1]);
                        den_1.push(arr[0]+arr[1]);
                        num_0.push(arr[0]);
                        den_0.push(arr[0]+arr[1]);
                    }
                    
                    else if(a_num_1==-1) {
                        if(post.charAt(i)=='&') {
                            long tmp_0_num_1=(1*b_num_0)%mod;
                            long tmp_0_den_1=(4*b_den_0)%mod;
                            
                            long tmp_0_num_2=(1*b_num_1)%mod;
                            long tmp_0_den_2=(4*b_den_1)%mod;
                            
                            long tmp_0_num_3=(1*b_num_0)%mod;
                            long tmp_0_den_3=(4*b_den_0)%mod;
                            
                            long tmp_1_num_1=(1*b_num_1)%mod;
                            long tmp_1_den_1=(4*b_den_1)%mod;
                            
                            long[] tmp=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            tmp=add_frac(tmp_0_num_3,tmp_0_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp[0]);
                            den_0.push(tmp[1]);
                            num_1.push(tmp_1_num_1);
                            den_1.push(tmp_1_den_1);
                        }
                        if(post.charAt(i)=='|') {
                            long tmp_0_num_1=(1*b_num_0)%mod;
                            long tmp_0_den_1=(4*b_den_0)%mod;
                            
                            long tmp_1_num_1=(1*b_num_1)%mod;
                            long tmp_1_den_1=(4*b_den_1)%mod;
                            
                            long tmp_1_num_2=(1*b_num_0)%mod;
                            long tmp_1_den_2=(4*b_den_0)%mod;
                            
                            long tmp_1_num_3=(1*b_num_1)%mod;
                            long tmp_1_den_3=(4*b_den_1)%mod;
                            
                            long[] tmp=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            tmp=add_frac(tmp_1_num_3,tmp_1_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp_0_num_1);
                            den_0.push(tmp_0_den_1);
                            num_1.push(tmp[0]);
                            den_1.push(tmp[1]);
                        }
                        if(post.charAt(i)=='^') {
                            long tmp_0_num_1=(1*b_num_0)%mod;
                            long tmp_0_den_1=(4*b_den_0)%mod;
                            
                            long tmp_1_num_1=(1*b_num_1)%mod;
                            long tmp_1_den_1=(4*b_den_1)%mod;
                            
                            long tmp_1_num_2=(1*b_num_0)%mod;
                            long tmp_1_den_2=(4*b_den_0)%mod;
                            
                            long tmp_0_num_2=(1*b_num_1)%mod;
                            long tmp_0_den_2=(4*b_den_1)%mod;
                            
                            long[] tmp_0=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            long[] tmp_1=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            num_0.push(tmp_0[0]);
                            den_0.push(tmp_0[1]);
                            num_1.push(tmp_1[0]);
                            den_1.push(tmp_1[1]);
                        }
                    }
                    
                    else if(b_num_1==-1) {
                        
                        if(post.charAt(i)=='&') {
                            long tmp_0_num_1=(a_num_0*1)%mod;
                            long tmp_0_den_1=(a_den_0*4)%mod;
                            
                            long tmp_0_num_2=(a_num_0*1)%mod;
                            long tmp_0_den_2=(a_den_0*4)%mod;
                            
                            long tmp_0_num_3=(a_num_1*1)%mod;
                            long tmp_0_den_3=(a_den_1*4)%mod;
                            
                            long tmp_1_num_1=(a_num_1*1)%mod;
                            long tmp_1_den_1=(a_den_1*4)%mod;
                            
                            long[] tmp=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            tmp=add_frac(tmp_0_num_3,tmp_0_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp[0]);
                            den_0.push(tmp[1]);
                            num_1.push(tmp_1_num_1);
                            den_1.push(tmp_1_den_1);
                        }
                        if(post.charAt(i)=='|') {
                            long tmp_0_num_1=(a_num_0*1)%mod;
                            long tmp_0_den_1=(a_den_0*4)%mod;
                            
                            long tmp_1_num_1=(a_num_0*1)%mod;
                            long tmp_1_den_1=(a_den_0*4)%mod;
                            
                            long tmp_1_num_2=(a_num_1*1)%mod;
                            long tmp_1_den_2=(a_den_1*4)%mod;
                            
                            long tmp_1_num_3=(a_num_1*1)%mod;
                            long tmp_1_den_3=(a_den_1*4)%mod;
                            
                            long[] tmp=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            tmp=add_frac(tmp_1_num_3,tmp_1_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp_0_num_1);
                            den_0.push(tmp_0_den_1);
                            num_1.push(tmp[0]);
                            den_1.push(tmp[1]);
                        }
                        if(post.charAt(i)=='^') {
                            long tmp_0_num_1=(a_num_0*1)%mod;
                            long tmp_0_den_1=(a_den_0*4)%mod;
                            
                            long tmp_1_num_1=(a_num_0*1)%mod;
                            long tmp_1_den_1=(a_den_0*4)%mod;
                            
                            long tmp_1_num_2=(a_num_1*1)%mod;
                            long tmp_1_den_2=(a_den_1*4)%mod;
                            
                            long tmp_0_num_2=(a_num_1*1)%mod;
                            long tmp_0_den_2=(a_den_1*4)%mod;
                            
                            long[] tmp_0=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            long[] tmp_1=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            num_0.push(tmp_0[0]);
                            den_0.push(tmp_0[1]);
                            num_1.push(tmp_1[0]);
                            den_1.push(tmp_1[1]);
                        }
                        
                    }
                    else {
                        if(post.charAt(i)=='&') {
                            long tmp_0_num_1=(a_num_0*b_num_0)%mod;
                            long tmp_0_den_1=(a_den_0*b_den_0)%mod;
                            
                            long tmp_0_num_2=(a_num_0*b_num_1)%mod;
                            long tmp_0_den_2=(a_den_0*b_den_1)%mod;
                            
                            long tmp_0_num_3=(a_num_1*b_num_0)%mod;
                            long tmp_0_den_3=(a_den_1*b_den_0)%mod;
                            
                            long tmp_1_num_1=(a_num_1*b_num_1)%mod;
                            long tmp_1_den_1=(a_den_1*b_den_1)%mod;
                            
                            long[] tmp=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            tmp=add_frac(tmp_0_num_3,tmp_0_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp[0]);
                            den_0.push(tmp[1]);
                            num_1.push(tmp_1_num_1);
                            den_1.push(tmp_1_den_1);
                        }
                        if(post.charAt(i)=='|') {
                            long tmp_0_num_1=(a_num_0*b_num_0)%mod;
                            long tmp_0_den_1=(a_den_0*b_den_0)%mod;
                            
                            long tmp_1_num_1=(a_num_0*b_num_1)%mod;
                            long tmp_1_den_1=(a_den_0*b_den_1)%mod;
                            
                            long tmp_1_num_2=(a_num_1*b_num_0)%mod;
                            long tmp_1_den_2=(a_den_1*b_den_0)%mod;
                            
                            long tmp_1_num_3=(a_num_1*b_num_1)%mod;
                            long tmp_1_den_3=(a_den_1*b_den_1)%mod;
                            
                            long[] tmp=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            tmp=add_frac(tmp_1_num_3,tmp_1_den_3,tmp[0],tmp[1],mod);
                            num_0.push(tmp_0_num_1);
                            den_0.push(tmp_0_den_1);
                            num_1.push(tmp[0]);
                            den_1.push(tmp[1]);
                        }
                        if(post.charAt(i)=='^') {
                            long tmp_0_num_1=(a_num_0*b_num_0)%mod;
                            long tmp_0_den_1=(a_den_0*b_den_0)%mod;
                            
                            long tmp_1_num_1=(a_num_0*b_num_1)%mod;
                            long tmp_1_den_1=(a_den_0*b_den_1)%mod;
                            
                            long tmp_1_num_2=(a_num_1*b_num_0)%mod;
                            long tmp_1_den_2=(a_den_1*b_den_0)%mod;
                            
                            long tmp_0_num_2=(a_num_1*b_num_1)%mod;
                            long tmp_0_den_2=(a_den_1*b_den_1)%mod;
                            
                            long[] tmp_0=add_frac(tmp_0_num_1,tmp_0_den_1,tmp_0_num_2,tmp_0_den_2,mod);
                            long[] tmp_1=add_frac(tmp_1_num_1,tmp_1_den_1,tmp_1_num_2,tmp_1_den_2,mod);
                            num_0.push(tmp_0[0]);
                            den_0.push(tmp_0[1]);
                            num_1.push(tmp_1[0]);
                            den_1.push(tmp_1[1]);
                        }
                    }
                }
            }

            long a_1_prob_0_num=num_0.pop();
            long a_1_prob_0_den=den_0.pop();
            long a_1_prob_1_num=num_1.pop();
            long a_1_prob_1_den=den_1.pop();

//            System.out.println("a_0_prob_0_num="+a_0_prob_0_num);
//            System.out.println("a_0_prob_0_den="+a_0_prob_0_den);
//            System.out.println("a_0_prob_1_num="+a_0_prob_1_num);
//            System.out.println("a_0_prob_1_den="+a_0_prob_1_den);
//            System.out.println("a_1_prob_0_num="+a_1_prob_0_num);
//            System.out.println("a_1_prob_0_den="+a_1_prob_0_den);
//            System.out.println("a_1_prob_1_num="+a_1_prob_1_num);
//            System.out.println("a_1_prob_1_den="+a_1_prob_1_den);

            long first_num=a_0_prob_0_num*a_1_prob_0_num;
            first_num%=mod;
            long first_den=a_0_prob_0_den*a_1_prob_0_den;
            first_den%=mod;
            long sec_num=a_0_prob_1_num*a_1_prob_1_num;
            sec_num%=mod;
            long sec_den=a_0_prob_1_den*a_1_prob_1_den;
            sec_den%=mod;
            long third_num=a_0_prob_0_num*a_1_prob_1_num;
            third_num%=mod;
            long third_den=a_0_prob_0_den*a_1_prob_1_den;
            third_den%=mod;
            long four_num=a_0_prob_1_num*a_1_prob_0_num;
            four_num%=mod;
            long four_den=a_0_prob_1_den*a_1_prob_0_den;
            four_den%=mod;

            
            System.out.print(((first_num*Inverse(first_den,mod))%mod)+" ");
            System.out.print(((sec_num*Inverse(sec_den,mod))%mod)+" ");
            System.out.print(((third_num*Inverse(third_den,mod))%mod)+" ");
            System.out.print((four_num*Inverse(four_den,mod))%mod);
            System.out.println();
        }
        
    }
    
    public static long[] prob(int a_val,char opr) {
        int a=a_val;
        int A=1-a_val;
        int arr[]={0,1,a,A};
        int zero=0,one=0;
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                int tmp=-1;
                if(opr=='&') {
                    tmp=arr[i]&arr[j];
                }
                if(opr=='^') {
                    tmp=arr[i]^arr[j];
                }
                if(opr=='|') {
                    tmp=arr[i]|arr[j];
                }
                if(tmp==0) {
                    zero++;
                }
                else {
                    one++;
                }
            }
        }
        return new long[]{zero,one};
    }
    
    public static StringBuilder in_to_pos(StringBuilder str) {
        StringBuilder ans=new StringBuilder(""); 
        Stack<Character> stck=new Stack<>();
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='#') {
                ans.append(str.charAt(i));
            }
            else if(str.charAt(i)=='&' || str.charAt(i)=='|' || str.charAt(i)=='^') {
                if(stck.isEmpty() || compare_greater_than(str.charAt(i), stck.peek())) {
                    stck.push(str.charAt(i));
                }
                else {
                    while(!compare_greater_than(str.charAt(i), stck.peek())) {
                        ans.append(stck.pop());
                    }
                    stck.push(str.charAt(i));
                }
            }
            else if(str.charAt(i)=='(') {
                stck.push(str.charAt(i));
            }
            else if(str.charAt(i)==')') {
                while(stck.peek()!='(') {
                    ans.append(stck.pop());
                }
                stck.pop();
            }
        }
        while(!stck.isEmpty()) {
            ans.append(stck.pop());
        }
        return ans;
    }
    public static boolean compare_greater_than(char c1, char c2) {
        int a1=-1,a2=-1;
        if(c1=='&') {
            a1=3;
        }
        if(c1=='^') {
            a1=2;
        }
        if(c1=='|') {
            a1=1;
        }
        if(c2=='&') {
            a1=3;
        }
        if(c2=='^') {
            a1=2;
        }
        if(c2=='|') {
            a1=1;
        }
        if(a1>=a2) {
            return true;
        }
        return false;
    }
    
    public static long Inverse(long a,long n) {
//        if(a==0) {
//            return 0;
//        }
        long q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            t=t1-(q*t2);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
            if(r2==0) {
                    break;
            }
        }
        if(r1!=1) {
                return -1;
        }
        t1%=n;
        if(t1<0) {
                t1+=n;
        }
        return t1;
    }
    static long[] add_frac(long num1,long den1,long num2, long den2, long mod) {
        long num=(num1*den2)+(num2*den1);
        long den=den1*den2;
        num%=mod;
        den%=mod;
        return new long[]{num,den};
    }
}
