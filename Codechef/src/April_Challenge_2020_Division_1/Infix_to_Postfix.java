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
public class Infix_to_Postfix {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        StringBuilder str=new StringBuilder(input.next());
        System.out.println(in_to_pos(str));
    }
    public static StringBuilder in_to_pos(StringBuilder str) {
        StringBuilder ans=new StringBuilder(""); 
        Stack<Character> stck=new Stack();
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
    public static void postfix_evaluation(StringBuilder str) {
        
    }
}
