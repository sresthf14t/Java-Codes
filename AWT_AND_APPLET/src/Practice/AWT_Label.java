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
public class AWT_Label extends Applet{
    public void init() {
        Label l1=new Label("Hello1");
        Label l2=new Label("Hello2");
        Label l3=new Label("Hello3");
        add(l1);
        add(l2);
        add(l3);
        
    }
}
