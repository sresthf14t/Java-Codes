/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2019;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.Scanner;
public class Contention {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int q=input.nextInt();
            int l[]=new int[q];
            int r[]=new int[q];
            int len[]=new int[q];
            int seats_got[]=new int[q];
            int min_l=200000000,max_r=-1;
            for(int i=0;i<q;i++) {
                l[i]=input.nextInt();
                r[i]=input.nextInt();
                l[i]--;
                r[i]--;
                len[i]=r[i]-l[i]+1;
                if(l[i]<min_l) {
                    min_l=l[i];
                }
                if(r[i]>max_r) {
                    max_r=r[i];
                }
            }
            for(int i=min_l;i<=max_r;i++) {
                int min_indx=-1,min_seats=2000000000;
                for(int j=0;j<q;j++) {
                    if(i>=l[j] && i<=r[j]) {
                        if(seats_got[j]<min_seats) {
                            min_seats=seats_got[j];
                            min_indx=j;
                        }
                        else if(seats_got[j]==min_seats && r[j]<r[min_indx]) {
                            min_seats=seats_got[j];
                            min_indx=j;
                        }
                    }
                }
                if(min_indx!=-1) {
                    seats_got[min_indx]++;
                }
            }
            int min=2000000000;
            for(int i=0;i<q;i++) {
                if(seats_got[i]<min) {
                    min=seats_got[i];
                }
            }
            System.out.println("Case #"+t+": "+min);
        }
    }
}
