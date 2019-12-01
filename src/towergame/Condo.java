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
public class Condo extends Structure {
    
    public Condo(Floor floor) {
        super(floor,WIDTH,1);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, w, h);
        g2d.drawString("Condo", x + 30, y + 15);
    }
    
    public static int WIDTH = 20 * Floor.WIDTH;
}
