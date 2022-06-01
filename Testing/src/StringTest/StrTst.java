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
public class StrTst {
    static int min=Integer.MAX_VALUE;
    public static void main(String args[]) {
        System.out.println(fun(5,6));
    }
    static int fun(int a , int b){
        if (b<=0) return 0;
        else return a+fun(a,b-1);
    }
}
