/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SingleLevelPackage3;
import SingleLevelPackage1.*;
import SingleLevelPackage2.*;
/**
 *
 * @author User
 */
public class Main {
    public static void main(String args[]) {
        SubClass1 sub1=new SubClass1();
        SubClass2 sub2=new SubClass2();
        sub1.show();
        sub2.show();
    }
}
