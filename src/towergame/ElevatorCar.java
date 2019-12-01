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
public class ElevatorCar extends Structure {
    ElevatorCar(int w) {
        super(null,w,1);
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + 2, y + 2, w - 4, h - 4);
    }
}
