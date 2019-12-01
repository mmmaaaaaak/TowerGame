/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Mark Masone
 */
public class ElevatorMachineRoom extends Structure {
    ElevatorMachineRoom(int w) {
        super(null,w,1);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawRect(x, y, w, h);
        g2d.drawRect(x, y, w, RESIZING_EDGE_HEIGHT);
    }
    
    public static int RESIZING_EDGE_HEIGHT = 8;
}
