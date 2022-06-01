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
public class Ques1 extends Applet{
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fill3DRect(200*2, 200*2, 50*2, 50*2, true);
        g.setColor(Color.BLACK);
        g.drawRect(200*2, 200*2, 50*2, 50*2);
        g.setColor(Color.BLUE);
        g.fill3DRect(200*2, 180*2, 50*2, 20*2, true);
        g.fill3DRect(250*2, 200*2, 20*2, 50*2, true);
        g.fill3DRect(200*2, 250*2, 50*2, 20*2, true);
        g.fill3DRect(180*2, 200*2, 20*2, 50*2, true);
        g.setColor(Color.red);
    }
 }
