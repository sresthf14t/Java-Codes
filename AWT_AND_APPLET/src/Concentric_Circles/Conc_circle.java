/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concentric_Circles;

import java.awt.Graphics;
import java.applet.*;
/**
 *
 * @author User
 */
public class Conc_circle extends Applet{
    public void paint(Graphics g) {
        int n=25;
        int width=100,width_prev=width,x=1000,y=400,x_prev=x,y_prev=y;
        g.drawOval(x, y, width, width);
        for(int i=1;i<=n;i++) {
            width+=30;
            x+=10;
            y+=10;
            x=x_prev-((width-width_prev)/2);
            y=y_prev-((width-width_prev)/2);
            g.drawOval(x, y, width, width);
            x_prev=x;
            y_prev=y;
            width_prev=width;
        }
        
        
    }
}
