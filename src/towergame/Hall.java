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
public class Hall extends Structure {
    public Hall(Floor floor) {
        super(floor,WIDTH,4);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, w, h);
        g2d.drawString("Hall", x + 155, y + 25);
    }
    
    public static int WIDTH = 50 * Floor.WIDTH;
}
