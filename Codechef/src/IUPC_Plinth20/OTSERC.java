/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IUPC_Plinth20;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class OTSERC {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
         for(int test=1;test<=t;test++) {
             String colour[]={"Blue","Yellow","Red","Red","Yellow","Blue","Blue","Yellow","Red","Red","Yellow","Blue"};
             long n=input.nextLong();
             long seat_rem=n%12L;
             long seat_quo=n/12L;
             long seat_opp_rem;
             if(seat_rem!=0) {
                 seat_opp_rem=13-seat_rem;
             }
             else {
                 seat_opp_rem=-11;
             }
             long seat_opp_quo=12L*seat_quo+seat_opp_rem;
             if(seat_rem==0) {
                 seat_rem+=12L;
             }
             System.out.println(seat_opp_quo+"\n"+colour[(int)(seat_rem-1L)]);
         }
    }
}
