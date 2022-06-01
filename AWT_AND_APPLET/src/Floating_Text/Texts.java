/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floating_Text;

/**
 *
 * @author User
 */
import java.awt.*;
import java.applet.*;
public class Texts extends Applet{
    public void paint(Graphics g) {
        for(int i=0;i<1000;i=i+15) {
            g.drawString("Welcome", 100, i);
            for(int j=0;j<1000000000;j++) {
                for(int k=0;k<1000000000;k++);
            }
        }
    }
}
