/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package February_Cook_Off_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class PSHOT {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int len=input.nextInt();
            len*=2;
            String str=input.next();
            int chances=len/2,team_a=0,team_b=0,rem_a=len/2,rem_b=len/2,indx=-1;
            for(int i=0;i<len;i++) {
                if(i%2==0) {
                    rem_a--;
                    if(str.charAt(i)=='1') {
                        team_a++;
                    }
                }
                else {
                    rem_b--;
                    if(str.charAt(i)=='1') {
                        team_b++;
                    } 
                }
                if(team_a<team_b) {
                    if(team_b-team_a>rem_a) {
                        indx=i;
                        break;
                    }
                }
                else if(team_a>team_b) {
                    if(team_a-team_b>rem_b) {
                        indx=i;
                        break;
                    }
                }
            }
            if(indx!=-1) {
                System.out.println(indx+1);
            }
            else {
                System.out.println(len);
            }
        }
    }
}
