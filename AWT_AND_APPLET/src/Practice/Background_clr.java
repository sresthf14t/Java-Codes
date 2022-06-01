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
public class Background_clr extends Applet{
    public void init() {
        
    }
    public void paint(Graphics g) {
        setBackground(Color.GREEN);
        g.setColor(Color.red);
        g.fill3DRect(10, 20, 100, 50, false);
    }
}
