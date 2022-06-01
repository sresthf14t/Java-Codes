/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concentric_Circles;

/**
 *
 * @author User
 */
import java.applet.*;
import java.awt.*;
public class Concentric_Circle extends Applet{
    public void paint(Graphics g) {
        int n=100;
        int width=10;
        int x=250,y=250;
        for(int i=1;i<=n;i++) {
            g.drawOval(x, y, width, width);
            x=x+3;
            y=y+3;
            width+=3;
        }
    }
}
