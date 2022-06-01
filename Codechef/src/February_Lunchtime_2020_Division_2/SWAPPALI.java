/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package February_Lunchtime_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class SWAPPALI {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            StringBuilder str=new StringBuilder(input.next());
            if(n==1) {
                System.out.println("YES\n0");
                continue;
            }
            int count=0;
            boolean is_pal=true;
            boolean moved[]=new boolean[n];
            for(int i=0,j=n-1;j>i;i++,j--) {
                if(str.charAt(i)!=str.charAt(j)) {
                    if(str.charAt(i+1)==str.charAt(j)) {
                        char tmp=str.charAt(i+1);
                        str.setCharAt(i+1, str.charAt(i));
                        str.setCharAt(i, tmp);
                        count++;
                        i++;
                        j--;
                        moved[i]=moved[j]=moved[i+1]=moved[j-1]=true;
                    }
                    else if(str.charAt(i)==str.charAt(j-1)) {
                        char tmp=str.charAt(j-1);
                        str.setCharAt(j-1, str.charAt(j));
                        str.setCharAt(j, tmp);
                        count++;
                        i++;
                        j--;
                        moved[i]=moved[j]=moved[i+1]=moved[j-1]=true;
                    }
                    else {
                        is_pal=false;
                        break;
                    }
                }
            }
            if(n%2==1)  {
                if(str.charAt((n/2)-1)==str.charAt((n/2)+1));
                else if(moved[(n/2)+1] && moved[(n/2)-1]) {
                    is_pal=false;
                }
                else if(str.charAt((n/2))==str.charAt((n/2)+1)) {
                    str.setCharAt((n/2),str.charAt((n/2)-1));
                    str.setCharAt((n/2)-1,str.charAt((n/2)+1));
                    count++;
                }
                else if(str.charAt((n/2))==str.charAt((n/2)-1)) {
                    str.setCharAt((n/2),str.charAt((n/2)+1));
                    str.setCharAt((n/2)+1,str.charAt((n/2)-1));
                    count++;
                }
                else {
                    is_pal=false;
                }
            }
            if(is_pal) {
                System.out.println("YES\n"+count);
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
