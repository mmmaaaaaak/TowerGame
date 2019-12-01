/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Mark Masone
 */
public class Canvas implements PeopleListener {
    public Canvas(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void peopleMoved() {
        repaint();
    }
    
    public void repaint() {
        gamePanel.repaint();
    }
    
    public void paint(Graphics2D g2d) {
        drawGround(g2d);
        drawPaintables(g2d);
    }
    
    public void drawGround(Graphics2D g2d) {
        int y = gamePanel.getHeight() - Floor.HEIGHT * Game.FLOORS_BELOW_GROUND;
        int x2 = gamePanel.getWidth();
        g2d.drawLine(0,y,x2,y);
    }
    
    public void drawPaintables(Graphics2D g2d) {
        for(Painter painter : painters)
            for(Paintable paintable : painter.getPaintables())
                paintable.draw(g2d);
    }
    
    public void addPainter(Painter painter) {
        painters.add(painter);
    }
    
    private final GamePanel gamePanel;
    private final ArrayList<Painter> painters = new ArrayList();
}
