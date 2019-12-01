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
public class Bulldozer implements Tool, Paintable {
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, w, h);
        g2d.drawString("X", x , y);
    }

    @Override
    public int getX() {
        return x;
    }
    
    @Override
    public int getY() {
        return y;
    }
    
    @Override
    public void snapX(int x) {
        this.x = ((x / Floor.WIDTH) * Floor.WIDTH) - (Floor.WIDTH / 2); //- ((w - Floor.WIDTH) / 2);
    }

    @Override
    public void snapY(int y) {
        this.y = (y / Floor.HEIGHT) * Floor.HEIGHT;
    }
    
    @Override
    public int getWidth() {
        return w;
    }
    
    @Override
    public int getHeight() {
        return h;
    }
    
    @Override
    public int getFloorNumber() {
        return Floor.getFloorNumberAtY(y);
    }
    
    private int x;
    private int y;
    private int w = Floor.WIDTH;
    private int h = Floor.HEIGHT;
}
