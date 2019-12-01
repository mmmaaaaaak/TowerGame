/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mark Masone
 */
public abstract class Elevator extends Structure implements Resizable {
    public Elevator(Floor floor,int w,int floors,ElevatorListener el) {
        super(floor,w,floors + 2);
        ec = new ElevatorCar(w);
        this.el = el;
        startCar();
    }
    
    private void startCar() {
        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(floors > 3) {
                    int carY1 = ec.getY();
                    int carY2 = carY1 + ec.getHeight();
                    if(carY1 <= y + Floor.HEIGHT)
                        dy = -1;
                    else if(carY2 >= y + h - Floor.HEIGHT)
                        dy = 1;
                    ec.setY(carY1 - dy);
                    el.repaint();
                }
            }
            
            private int dy = 1;
        }, 1000, 100, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public int getFloorNumber() {
        int dy = y + Floor.HEIGHT;
        if(floors > 3)
            dy += h - (Floor.HEIGHT * 3);
        return Floor.getFloorNumberAtY(dy);
    }
    
    @Override
    public void snapX(int x) {
        super.snapX(x);
        ec.snapX(x);
    }
    
    @Override
    public void snapY(int y) {
        super.snapY(y);
        ec.snapY(y);
    }
    
    @Override
    public void upFloor() {
        this.y = y - Floor.HEIGHT;
    }
    
    @Override
    public void downFloor() {
        this.y = y + Floor.HEIGHT;
    }
    
    @Override
    public void setFloors(int floors) {
        //ses.shutdown();
        this.h = floors * Floor.HEIGHT;
        this.floors = floors;
        int carY1 = ec.getY();
        int carY2 = carY1 + ec.getHeight();
        if(carY2 >= y + h - Floor.HEIGHT)
            ec.setY(y + h - (Floor.HEIGHT * 2));
        else if(carY1 <= y + Floor.HEIGHT)
            ec.setY(y + Floor.HEIGHT);
        //startCar();
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        ElevatorMachineRoom emr = new ElevatorMachineRoom(w);
        emr.snapX(x + (w / 2));
        emr.snapY(y);
        emr.draw(g2d);
        ElevatorPit ep = new ElevatorPit(w);
        ep.snapX(x + (w / 2));
        ep.snapY(y + (h - Floor.HEIGHT));
        ep.draw(g2d);
        ec.draw(g2d);
        g2d.setColor(Color.BLACK);
        Font font = g2d.getFont();
        g2d.setFont(font.deriveFont(9.0f));
        int floor = (y / Floor.HEIGHT - 179) * -1;
        int floors = (h / Floor.HEIGHT) - 2;
        String floorLabel;
        for(int i = 1;i <= floors;i++) {
            if(floor < 1) {
                floorLabel = "B" + (floor - 1) * -1;
            } else {
                floorLabel = "" + floor;
            }
            floor--;
            g2d.drawString(floorLabel, x + 1, y + (Floor.HEIGHT * i) + 17);
        }
        g2d.setFont(font);
    }

    @Override
    public boolean mouseOverResizableEdge(int x,int y) {
        int x1 = this.x;
        int y1 = this.y;
        int x2 = x1 + this.w;
        int y2 = y1 + ElevatorMachineRoom.RESIZING_EDGE_HEIGHT;
        int y3 = y1 + this.h - ElevatorPit.RESIZING_EDGE_HEIGHT;
        int y4 = y3 + ElevatorPit.RESIZING_EDGE_HEIGHT;
        return x1 < x && x < x2 && ((y1 < y && y < y2) || (y3 < y && y < y4));
    }

    @Override
    public Cursor getResizeCursor() {
        return new Cursor(java.awt.Cursor.N_RESIZE_CURSOR);
    }
    
    private final ElevatorCar ec;
    private ScheduledExecutorService ses;
    private final ElevatorListener el;
}
