/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package March_Cook_Off_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*; 
public class GIFTSRC {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            StringBuilder str=new StringBuilder(input.next());
            int x=0,y=0;
            for(int i=1;i<str.length();i++) {
                if((str.charAt(i)=='L' || str.charAt(i)=='R') && (str.charAt(i-1)=='L' || str.charAt(i-1)=='R')) {
                    str.deleteCharAt(i);
                    i--;
                    continue;
                }
                if((str.charAt(i)=='U' || str.charAt(i)=='D') && (str.charAt(i-1)=='U' || str.charAt(i-1)=='D')) {
                    str.deleteCharAt(i);
                    i--;
                    continue;
                }
                if(str.charAt(i)=='L') {
                    x--;
                }
                else if(str.charAt(i)=='R') {
                    x++;
                }
                else if(str.charAt(i)=='D') {
                    y--;
                }
                else if(str.charAt(i)=='U') {
                    y++;
                }
            }
            if(str.charAt(0)=='L') {
                    x--;
                }
                else if(str.charAt(0)=='R') {
                    x++;
                }
                else if(str.charAt(0)=='D') {
                    y--;
                }
                else if(str.charAt(0)=='U') {
                    y++;
                }
            System.out.println(x+" "+y);
        }
    }
}
