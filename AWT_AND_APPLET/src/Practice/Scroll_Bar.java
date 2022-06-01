/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class Scroll_Bar  extends Applet{
    public void init() {
        TextArea a1=new TextArea();
        a1.setBounds(100, 100, 100, 100);
        add(a1);
        a1.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ\nABCDEFGHIJKLMNOPQRSTUVWXYZ\nABCDEFGHIJKLMNOPQRSTUVWXYZ\nABCDEFGHIJKLMNOPQRSTUVWXYZ\nABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }
}
