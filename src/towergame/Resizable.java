/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Cursor;

/**
 *
 * @author Mark Masone
 */
public interface Resizable {
    public boolean mouseOverResizableEdge(int x,int y);
    public Cursor getResizeCursor();
    public void upFloor();
    public void downFloor();
    public void setFloors(int floors);
}
