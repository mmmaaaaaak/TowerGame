/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Dimension;

/**
 *
 * @author Mark Masone
 */
public class GamePanel extends JPanel implements ElevatorListener {
    GamePanel() {
        this.setPreferredSize(new Dimension(1200 * Floor.WIDTH,(Game.FLOORS_ABOVE_GROUND + Game.FLOORS_BELOW_GROUND) * Floor.HEIGHT));
    }
    
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if(canvas != null)
            canvas.paint(g2d);
    }
    
    private Canvas canvas;
}
