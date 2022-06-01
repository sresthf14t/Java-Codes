/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringTest;

/**
 *
 * @author User
 */
public class Combinations_1 {
    public static void main(String args[]) {
        
        long lim=(long)(Math.pow(10,10));
        for(long i=0L;i<=lim;i++) {
            
        }
        System.exit(0);
        int count=0;
        int strt=(int)Math.pow(10,6);
        int end=(int)Math.pow(10,7);
        for(int i=strt;i<end;i++) {
            if(dig_sum(""+i)==7) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println(count);
    }
    public static int dig_sum(String s) {
        int sum=0;
        for(int i=0;i<s.length();i++) {
            sum+=s.charAt(i)-48;
        }
        return sum;
    }
}
