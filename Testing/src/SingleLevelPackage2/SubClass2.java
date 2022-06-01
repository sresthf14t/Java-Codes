/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SingleLevelPackage2;
import SingleLevelPackage1.*;
/**
 *
 * @author User
 */
public class SubClass2 extends SuperClass1{
    public String public1="public";
    String default1="default";
    protected String protected1="protected";
    private String private1="private";
    public void show() {
        System.out.println("Inside SuperClass1 show()");
    }
}
