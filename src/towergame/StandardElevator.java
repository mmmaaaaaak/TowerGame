/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

/**
 *
 * @author Mark Masone
 */
public class StandardElevator extends Elevator {
    public StandardElevator(Floor floor,ElevatorListener el) {
        super(floor,WIDTH,1,el);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, w, h);
    }
    
    public static int WIDTH = 4 * Floor.WIDTH;
}
