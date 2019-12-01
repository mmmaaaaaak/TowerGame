/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mark Masone
 */
public class People implements Painter,PersonListener {
  
    public People(ArrayList<Floor> floors) {
        ses = Executors.newSingleThreadScheduledExecutor();
        this.floors = floors;
    }
    
    public void start() {
        PersonListener pl = this;
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ArrayList<Integer> delays = new ArrayList();
                int max_delay = 60;
                for(int i = 1; i <= max_delay;i++)
                    delays.add(i);
                for(Person person : people) {
                    if(!person.isMoving()) {
                        Random random = new Random();
                        int index = random.nextInt(max_delay);
                        int delay = delays.remove(index);
                        person.scheduleArrival(pl, delay);
                    }
                }
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }
    
    public void addPeople(int count, Structure dest) {
        ArrayList<Integer> delays = new ArrayList();
        int max_delay = 60;
        for(int i = 1; i <= max_delay;i++)
            delays.add(i);
        while(count > 0) {
            Random random = new Random();
            int index = random.nextInt(max_delay);
            int delay = delays.remove(index);
            Person person = new Person(dest);
            person.setFloor(floors.get(Game.FLOORS_ABOVE_GROUND - 1));
            person.scheduleArrival(this, delay);
            people.add(person);
            max_delay--;
            count--;
        }
    }
    
    @Override
    public void destinationReached(Person person) {
        people.remove(person);
    }
    
    @Override
    public void personMoved() {
        for(PeopleListener pl : peopleListeners)
            pl.peopleMoved();
    }
    
    public ArrayList<Person> getPeople() {
        return people;
    }
    
    public void addPeopleListener(PeopleListener pl) {
        peopleListeners.add(pl);
    }
    
    public void removePeopleListener(PeopleListener pl) {
        peopleListeners.remove(pl);
    }
    
    @Override
    public ArrayList<Paintable> getPaintables() {
        ArrayList<Paintable> paintables = new ArrayList();
        paintables.addAll(people);
        return paintables;
    }
    
    private final ArrayList<PeopleListener> peopleListeners = new ArrayList();
    private final ArrayList<Person> people = new ArrayList();
    private final ScheduledExecutorService ses;
    private final ArrayList<Floor> floors;
}
