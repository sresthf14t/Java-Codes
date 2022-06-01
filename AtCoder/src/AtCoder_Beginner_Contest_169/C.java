/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_169;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
import java.math.*;
public class C {
   public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        long a=input.nextLong();
        float b=input.nextFloat();
        System.out.println(solve(a,b));
    }
   public static long solve(long a,float b) {
       BigDecimal A=new BigDecimal(""+a);
       BigDecimal B=new BigDecimal(""+b);
       BigDecimal C=A.multiply(B);
       return C.longValue();
   }
}
