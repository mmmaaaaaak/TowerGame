/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Graphics2D;

/**
 *
 * @author Mark Masone
 */
public interface Tool extends Paintable {
    public void snapX(int x);
    public void snapY(int y);
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public int getFloorNumber();
}
