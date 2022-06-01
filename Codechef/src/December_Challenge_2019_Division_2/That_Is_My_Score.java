/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package December_Challenge_2019_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class That_Is_My_Score {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int i=1;i<=t;i++) {
            int n=input.nextInt();
            int total_score=0;
            int score[]=new int[12];
            for(int j=0;j<n;j++) {
                int prob_no=input.nextInt();
                int scr=input.nextInt();
                if(score[prob_no]<scr) {
                    score[prob_no]=scr;
                }
            }
            for(int j=0;j<9;j++) {
                total_score+=score[j];
            }
            System.out.println(total_score);
        }
    }
}
