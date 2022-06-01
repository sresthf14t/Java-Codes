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
public class Border_Layout extends Applet{
    public void init() {
        setLayout(new BorderLayout());
        //setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        Label l1=new Label("Label1");
        l1.setSize(100, 100);
        add(l1,BorderLayout.WEST);
        Label l2=new Label("Label2");
        l2.setSize(100, 100);
        add(l2,BorderLayout.EAST);
        Label l3=new Label("Label3");
        l3.setSize(100, 100);
        add(l3,BorderLayout.CENTER);
        Label l4=new Label("Label4");
        l4.setSize(100, 100);
        add(l4,BorderLayout.NORTH);
        Label l5=new Label("Label5");
        l5.setSize(100, 100);
        add(l5,BorderLayout.SOUTH);
    }
}
