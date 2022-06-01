/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codevita;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;

class MyException extends Exception 
{ 
    public MyException(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 
}

class less_age extends Exception 
{ 
    public less_age(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 
}

public class B {
    
     
    
    public static void main(String args[]) {
       Scanner input=new Scanner(System.in);
       String name=input.next();
       int age=input.nextInt();
       int mark=input.nextInt();
       
       try {
          if(age<=0) {
              throw new MyException("Message 1");
          } 
       }
       catch (MyException e) { 
            System.out.println(e.getMessage()); 
       } 
       
       try {
          if(age<18 && age>0) {
              throw new MyException("Message 2");
          } 
       }
       catch (MyException e) { 
            System.out.println(e.getMessage()); 
       } 
       
       
       try {
          if(mark<0) {
              throw new MyException("Message 3");
          } 
       }
       catch (MyException e) { 
            System.out.println(e.getMessage()); 
       } 
       
       try {
          if(mark>=0 && mark<80) {
              throw new MyException("Message 4");
          } 
       }
       catch (MyException e) { 
            System.out.println(e.getMessage()); 
       }
       
       if(age>18 && mark>80) {
           System.out.println("Approved");
       }
    }
}
