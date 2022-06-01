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
public class Update_overriding extends Applet{
    static int count=0;
    public void update() {
    }
    public void paint(Graphics g) {
        while(true) {
        for(int i=0;i<10000;i+=10) {
            g.drawString("Hello", count, 100);
            count+=10;
            repaint(1000000);
        }
    }
        
        
        
    }
}
