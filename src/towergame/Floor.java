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
public class Floor {
    
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        elevators = new ArrayList();
        structures = new ArrayList();
    }
    
    public boolean hasElevator() {
        for(Structure structure : structures) {
            if(structure instanceof Elevator)
                return true;
        }
        return false;
    }
    
    public void addStructure(Structure structure) {
        structures.add(structure);
    }
    
    public void removeStructure(Structure structure) {
        structures.remove(structure);
    }
    
    public ArrayList<Structure> getStructures() {
        return structures;
    }
    
    @Override
    public String toString() {
        return Integer.toString(floorNumber);
    }
    
    public int getNumber() {
        return floorNumber;
    }
    
    public int getY() {
        int index = Game.FLOORS_ABOVE_GROUND - floorNumber;
        if(floorNumber < 0)
            index--;
        return index * Floor.HEIGHT;
    }
    
    public static int getFloorNumberAtY(int y) {
        int floorsFromTop = y / Floor.HEIGHT;
        int floorNumber = Game.FLOORS_ABOVE_GROUND - floorsFromTop;
        if(floorsFromTop >= Game.FLOORS_ABOVE_GROUND)
            floorNumber--;
        System.out.println("Floor: floor number " + floorNumber);
        return floorNumber;
    }
    
    private final ArrayList<Structure> structures;
    private final ArrayList<Elevator> elevators;
    private final int floorNumber;
    
    public static int WIDTH = 6;
    public static int HEIGHT = 24;
}
