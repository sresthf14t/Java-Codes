import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
public class A {
    public static void main(String[] args) {
        float a=28,b=32,c=42;
        a=1/a;
        b=1/b;
        c=1/c;
        float d=a+c;
        float total=0;
        int cnt=0;
        while(total<1) {
            cnt++;
            if(cnt%4==0) {
                total+=c;
            }
            if(cnt%4==1) {
                total+=d;
            }
            if(cnt%4==2) {
                total+=a;
            }
            if(cnt%4==3) {
                total+=b;
            }
        }
        System.out.println(total+" "+cnt);
    }
}