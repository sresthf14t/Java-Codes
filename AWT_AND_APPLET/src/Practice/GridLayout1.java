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
public class GridLayout1 extends Applet{
    public void init() {
        setLayout(new GridLayout(10,10));
        Button b[]=new Button[100];
        for(int i=0;i<b.length;i++) {
            b[i]=new Button("Button"+i);
            add(b[i]);
        } 
    }
}
