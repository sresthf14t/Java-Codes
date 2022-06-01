/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedy;

/**
 *
 * @author User
 */
/*
Given three stack of the positive numbers, the task is to find the possible 
equal maximum sum of the stacks with removal of top elements allowed. 
Stacks are represented as array, and the first index of the array represent
the top element of the stack.
*/
import java.util.*;
import java.io.*;
class Stack {
    ArrayList<Integer> stack;
    public Stack() {
        stack=new ArrayList<>();
    }
    public void push(int n) {
        stack.add(n);
    }
    public int pop() {
        if(stack.size()==0) {
            return -1;
        }
        int top=stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return top;
    }
    public int sum() {
        int sum=0;
        for(int i=0;i<stack.size();i++) {
            sum+=stack.get(i);
        }
        return sum;
    }
}
public class Maximum_equal_sum_possible_in_three_stacks {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        //Initializing the stacks
        Stack s1=new Stack();
        Stack s2=new Stack();
        Stack s3=new Stack();
        //putting elements in stack
        int n1=input.nextInt();
        int n2=input.nextInt();
        int n3=input.nextInt();
        for(int i=0;i<n1;i++) {
            s1.push(input.nextInt());
        }
        for(int i=0;i<n2;i++) {
            s2.push(input.nextInt());
        }
        for(int i=0;i<n3;i++) {
            s3.push(input.nextInt());
        }
        while(true) {
            int sum1=s1.sum(),sum2=s2.sum(),sum3=s3.sum();
            if(sum1==sum2 && sum2==sum3) {
                break;
            }
            else if(sum1>sum2 && sum1>sum3) {
                s1.pop();
            }
            else if(sum2>sum3) {
                s2.pop();
            }
            else {
                s3.pop();
            }
        }
        System.out.println("sum1="+s1.sum());
        System.out.println("sum2="+s2.sum());
        System.out.println("sum3="+s3.sum());
    }
}
