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
import java.awt.*;
public class Frames {
    Frame f1;
    Frames() {
        f1=new Frame();
        Label l1=new Label("Label1");
        f1.add(l1);
        f1.setVisible(true);
        f1.setSize(1000,1000);
    }
    public static void main(String args[]) {
        Frames f=new Frames();
;    }
}
