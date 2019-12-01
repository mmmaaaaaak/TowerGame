/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Mark Masone
 */
public class ElevatorPit extends Structure {
    ElevatorPit(int w) {
        super(null,w,1);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.drawRect(x, y, w, h);
        g2d.drawRect(x, y + h - RESIZING_EDGE_HEIGHT, w, RESIZING_EDGE_HEIGHT);
        //g2d.drawString("^", x + 7, y + 11);
    }
    
    public static int RESIZING_EDGE_HEIGHT = 8;
}
