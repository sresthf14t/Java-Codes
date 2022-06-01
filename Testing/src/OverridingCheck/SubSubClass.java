/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OverridingCheck;

/**
 *
 * @author User
 */
public class SubSubClass extends SubClass{
    SubSubClass(int a) {
        super(10);
        System.out.println("Inside SubSubClass Constructor"+a);
    }
    public void show() {
        System.out.println("Inside SubSubClass");
        super.show();
    }
}
