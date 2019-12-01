/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.util.ArrayList;

/**
 *
 * @author Mark Masone
 */
public class CollisionDetection {
    
    public CollisionDetection(ArrayList<Floor> floors) {
        this.floors = floors;
    }
    
    public Structure getCollidingStructure(Tool cursor) {
        int cursorX1 = cursor.getX();
        int cursorY1 = cursor.getY();
        int cursorX2 = cursorX1 + cursor.getWidth();
        int cursorY2 = cursorY1 + cursor.getHeight();
        for(Floor floor : floors) {
            for(Structure structure : floor.getStructures()) {
                if(
                        ((structure instanceof Elevator) && (cursor instanceof Elevator)) ||
                        (!(structure instanceof Elevator) && !(cursor instanceof Elevator))
                ) {
                    if(collision(structure,cursorX1,cursorY1,cursorX2,cursorY2))
                        return structure;
                }
            }
        }
        return null;
    }
    
    private boolean collision(Structure structure,int cursorX1,int cursorY1,int cursorX2,int cursorY2) {
        int x1 = structure.getX();
        int y1 = structure.getY();
        int x2 = x1 + structure.getWidth();
        int y2 = y1 + structure.getHeight();
        return (
                (y1 < cursorY1 && cursorY1 < y2) ||
                (y1 < cursorY2 && cursorY2 < y2) ||
                cursorY1 == y1 ||
                cursorY2 == y2
        ) &&
        (
                (x1 < cursorX1 && cursorX1 < x2) ||
                (x1 < cursorX2 && cursorX2 < x2) ||
                (cursorX1 < x1 && x2 < cursorX2) ||
                cursorX1 == x1 ||
                cursorX2 == x2
        );
    }
    
    private final ArrayList<Floor> floors;
}
