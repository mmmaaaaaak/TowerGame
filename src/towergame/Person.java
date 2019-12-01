/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Graphics2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mark Masone
 */
public class Person implements Paintable {
    public Person(Structure dest) {
        this.dest = dest;
        moving = false;
        away = true;
        w = Floor.WIDTH;
        h = Floor.HEIGHT;
        x = -Floor.WIDTH - 1;
        ses = Executors.newSingleThreadScheduledExecutor();
    }
    
    public void scheduleArrival(PersonListener pl, int delay) {
        Person person = this;
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(dest.getFloorNumber() != floor.getNumber() && !floor.hasElevator())
                    System.out.println("NO ELEVATOR");
                else {
                    moving = true;
                    Floor destFloor = dest.getFloor();
                    if(destFloor.getNumber() != 0) {
                        away(false);
                        x += 2;
                        if(x >= dest.getX()) {
                            ses.shutdown();
                            pl.destinationReached(person);
                        } else
                            pl.personMoved();
                    }
                }
            }
        }, delay * 1000, 100, TimeUnit.MILLISECONDS);
    }
    
    public boolean isMoving() {
        return moving;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setFloor(Floor floor) {
        this.floor = floor;
        y = floor.getY();
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        if(!away) {
            g2d.drawOval(x,y,w,h);
            g2d.fillOval(x,y,w,h);
        }
    }
    
    public boolean isAway() {
        return away;
    }
    
    public void away(boolean away) {
        this.away = away;
    }
    
    private final Structure dest;
    private boolean away;
    private final ScheduledExecutorService ses;
    private int x;
    private int y;
    private final int w;
    private final int h;
    protected int floors;
    private boolean moving;
    private Floor floor;
}
