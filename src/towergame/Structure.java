/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Graphics2D;
import java.awt.Cursor;

/**
 *
 * @author Mark Masone
 */
public abstract class Structure implements Tool, Paintable {
    
    Structure(Floor floor,int w,int floors) {
        this.w = w;
        this.h = floors * Floor.HEIGHT;
        this.floors = floors;
        this.floor = floor;
    }
    
    @Override
    public int getX() {
        return x;
    }
    
    @Override
    public int getY() {
        return y;
    }
    
    public Floor getFloor() {
        return floor;
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
    public void snapX(int x) {
        this.x = ((x / Floor.WIDTH) * Floor.WIDTH) - ((w - Floor.WIDTH) / 2);
    }
    
    @Override
    public void snapY(int y) {
        int dy = (h - Floor.HEIGHT) / 2;
        int dy1 = 0;
        int dy2 = 0;
        if(floors % 2 == 0)
            dy1 = dy;
        else
            dy2 = dy;
        this.y = (((y - dy1) / Floor.HEIGHT) * Floor.HEIGHT) - dy2;
    }
    
    @Override
    public int getFloorNumber() {
        int dy = y;
        if(floors > 1)
            dy += h - Floor.HEIGHT;
        return Floor.getFloorNumberAtY(dy);
    }
    
    private final Floor floor;
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected int floors;
    @Override
    public abstract void draw(Graphics2D g2d);
}
