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
public class Border_Layout1 extends Applet{
    public void init() {
        setLayout(new BorderLayout());
        Panel p1=new Panel();
        Button b1=new Button("Button1");
        Button b2=new Button("Button2");
        Button b3=new Button("Button3");
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        Label l1=new Label("Label1");
        l1.setSize(100, 100);
        add(l1,BorderLayout.WEST);
        Label l2=new Label("Label2");
        l2.setSize(100, 100);
        add(l2,BorderLayout.EAST);
        add(p1,BorderLayout.CENTER);
        Label l4=new Label("Label4");
        l4.setSize(100, 100);
        add(l4,BorderLayout.NORTH);
        Label l5=new Label("Label5");
        l5.setSize(100, 100);
        add(l5,BorderLayout.SOUTH);
    }
}