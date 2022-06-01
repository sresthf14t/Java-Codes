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
public class Ques2 extends Applet{
    public void paint(Graphics g) {
        int x=100,y=100;
        int xarr[]={x,x+50,x+0,x+200,x+125,x+150};
        int yarr[]={y,y+50,y+200,y+150,y+100,y+50};
        g.drawPolygon(xarr , yarr, 6);
        for(int i=0;i<xarr.length;i++) {
            xarr[i]+=300;
        }
        g.fillPolygon(xarr, yarr, 6);
    }
}
