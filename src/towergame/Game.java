/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.ButtonModel;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Mark Masone
 */
public class Game extends MouseAdapter implements MouseMotionListener,TowerFrameListener,Painter {
    public Game() {
        tool = null;
        allowAddStructure = true;
        defaultCursor = new Cursor(java.awt.Cursor.DEFAULT_CURSOR);
        floors = new ArrayList();
        for(int i = 0; i < Game.FLOORS_ABOVE_GROUND + Game.FLOORS_BELOW_GROUND; i++) {
            int number = Game.FLOORS_ABOVE_GROUND - i;
            if(i >= Game.FLOORS_ABOVE_GROUND)
                number--;
            floors.add(new Floor(number));
        }
        collisionDetection = new CollisionDetection(floors);
        people = new People(floors);
        gamePanel = new GamePanel();
        canvas = new Canvas(gamePanel);
        towerFrame = new TowerFrame(gamePanel);
        
    }
    
    public void start() {
        canvas.addPainter(this);
        canvas.addPainter(people);
        gamePanel.setCanvas(canvas);
        towerFrame.addTowerFrameListener(this);
        gamePanel.addMouseMotionListener(this);
        gamePanel.addMouseListener(this);
        people.addPeopleListener(canvas);
        towerFrame.setVisible(true);
    }
    
    private Floor getFloor(int floorNumber) {
        int index = 0;
        if(floorNumber != 0) {
            index = Game.FLOORS_ABOVE_GROUND - floorNumber;
            if(floorNumber < 0)
                index--;
        }
        System.out.println("floor index " + index);
        return floors.get(index);
    }
    
    @Override
    public ArrayList<Paintable> getPaintables() {
        ArrayList<Paintable> paintables = new ArrayList();
        for(Floor floor : floors) {
            paintables.addAll(floor.getStructures());
        }
        if(drawTool && tool != null)
            paintables.add(tool);
        return paintables;
    }
    
    @Override
    public void radioButtonSelectionChanged() {
        ButtonModel bm = towerFrame.getSelectedRadioButtonModel();
        String selection = bm.getActionCommand();
        switch(selection) {
            case "condo": tool = new Condo(null); break;
            case "standard elevator": tool = new StandardElevator(null,gamePanel); break;
            case "office": tool = new Office(null); break;
            case "hall": tool = new Hall(null); break;
            case "lobby": tool = new Lobby(null); break;
            case "express elevator": tool = new ExpressElevator(null,gamePanel); break;
            case "demolish": tool = new Bulldozer(); break;
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        resetCursor(x,y);
        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(resizableStructure != null && resizableStructure instanceof Resizable)
            resize(e.getY());
        canvas.repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        pressedX = e.getX();
        pressedY = e.getY();
    }
    @Override
    public void mouseExited(MouseEvent e) {
        drawTool = false;
        canvas.repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        ButtonModel bm = towerFrame.getSelectedRadioButtonModel();
        if(bm != null) {
            String selection = bm.getActionCommand();
            if(selection.equals("demolish"))
                demolish();
            else if(allowAddStructure) {
                Structure structure = null;
                Floor floor = getFloor(tool.getFloorNumber());
                System.out.println("selection " + selection);
                switch(selection) {
                    case "condo": structure = new Condo(floor); break;
                    case "standard elevator": structure = new StandardElevator(floor,gamePanel); break;
                    case "office": 
                        structure = new Office(floor);
                        people.addPeople(10,structure);
                        break;
                    case "hall": structure = new Hall(floor); break;
                    case "lobby": structure = new Lobby(floor); break;
                    case "express elevator": structure = new ExpressElevator(floor,gamePanel); break;
                }
                if(structure != null) {
                    System.out.println("add structure " + selection);
                    structure.snapX(pressedX);
                    structure.snapY(pressedY);
                    addStructure(structure,floor);
                }
            }
        }
        resetCursor(x,y);
        canvas.repaint();
    }
    
    private void resize(int y) {
        int h = resizableStructure.getHeight();
        int y1 = resizableStructure.getY();
        int y2 = y1 + h;
        Resizable resizable = (Resizable)resizableStructure;
        if(y > y2) {
            int floors = h / Floor.HEIGHT + 1;
            resizable.setFloors(floors);
        } else if(y < y1) {
            resizable.upFloor();
            resizable.setFloors(h / Floor.HEIGHT + 1);
        } else if(y1 + Floor.HEIGHT < y && y < y2 - Floor.HEIGHT) {
            if(y2 - (Floor.HEIGHT * 2) < y) {
                int floors = h / Floor.HEIGHT - 1;
                if(floors >= 3)
                    resizable.setFloors(h / Floor.HEIGHT - 1);
            } else if(y1 + (Floor.HEIGHT * 2) > y) {
                int floors = h / Floor.HEIGHT - 1;
                if(floors >= 3) {
                    resizable.downFloor();
                    resizable.setFloors(h / Floor.HEIGHT - 1);
                }
            }
        }
    }
    
    private void resetCursor(int x,int y) {
        drawTool = true;
        allowAddStructure = true;
        gamePanel.setCursor(defaultCursor);
        resizableStructure = null;
        for(Floor floor : floors)
            for(Structure structure : floor.getStructures()) {
                if(structure instanceof Resizable) {
                    Resizable resizable = (Resizable)structure;
                    if(resizable.mouseOverResizableEdge(x,y)) {
                        gamePanel.setCursor(resizable.getResizeCursor());
                        drawTool = false;
                        allowAddStructure = false;
                        resizableStructure = structure;
                    }
                }
            }
        if(tool != null) {
            tool.snapX(x);
            tool.snapY(y);
        }
    }
    
    private void addStructure(Structure structure,Floor floor) {
        if(collisionDetection.getCollidingStructure(structure) == null) {
            floor.addStructure(structure);
            //structures.add(structure);
            System.out.println("structure added to floor " + floor);
        } else
            System.out.println("collision detected");
    }
    
    private void demolish() {
        Structure demolishStructure = collisionDetection.getCollidingStructure(this.tool);
        if(demolishStructure != null) {
            Floor floor = demolishStructure.getFloor();
            System.out.println(demolishStructure.getClass() + " deleted from floor " + floor);
            floor.removeStructure(demolishStructure);
        }
    }
    
    private final ArrayList<Floor> floors;
    private boolean drawTool;
    private final Cursor defaultCursor;
    private Structure resizableStructure;
    private boolean allowAddStructure;
    private final CollisionDetection collisionDetection;
    //private final ArrayList<Structure> structures;
    private Tool tool;
    private final Canvas canvas;
    private final People people;
    private final TowerFrame towerFrame;
    private final GamePanel gamePanel;
    private int pressedX;
    private int pressedY;
    
    public static final int FLOORS_ABOVE_GROUND = 180;
    public static final int FLOORS_BELOW_GROUND = 20;
}
