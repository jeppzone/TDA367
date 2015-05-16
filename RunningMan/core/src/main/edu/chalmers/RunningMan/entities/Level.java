package edu.chalmers.RunningMan.entities;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to model a level
 * @author Jesper Olsson
 */
public class Level implements PropertyChangeListener {
    private final List<AbstractPhysicalObject> mapObjects;
    private final String levelName;
    private static int enemiesKilled;
    private static final int MAX_TIME = 5;
    private boolean hasPlayerMovedMoreThanOneTime = false;
    private boolean hasFiredOnce = false;
    private Time time;
    private PropertyChangeSupport pcs;

    public Level(List<AbstractPhysicalObject> mapObjects, String levelName){
        this.mapObjects = mapObjects;
        this.levelName = levelName;
        this.enemiesKilled = 0;
        time = new Time(MAX_TIME);
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Method to be called continuously to check for
     * collisions all over the level
     */

    public void checkCollisions(List<Bullet> bullets) {
        checkBulletCollisions(bullets);
        for(AbstractPhysicalObject thisApo: mapObjects){
            if(thisApo instanceof IVisitor){
                for(AbstractPhysicalObject otherApo: mapObjects){
                    if(isColliding(thisApo.getHitbox(), otherApo.getHitbox())){
                        IVisitor visitor = (IVisitor) thisApo;
                        otherApo.acceptVisitor(visitor);
                    }
                }
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Method to check whether bullets are colliding with
     * any objects
     * @param bullets
     */
    public void checkBulletCollisions(List<Bullet> bullets){
        int bulletSize = bullets.size();
        int objectSize = mapObjects.size();
        for (int i = 0; i < bulletSize; i++) {
            for (int j = 0; j < objectSize; j++) {
                if (bulletSize > i && objectSize > j) {
                    final Bullet bullet = bullets.get(i);
                    final AbstractPhysicalObject object = mapObjects.get(j);
                    if (isColliding(bullet.getHitbox(), object.getHitbox())) {
                        if (object instanceof Enemy) {
                            final Enemy enemy = (Enemy) object;
                            enemy.visit(bullet);
                            bullets.remove(bullet);
                            mapObjects.remove(enemy);
                            bulletSize--;
                            objectSize--;
                            enemiesKilled++;
                        } else if (object.getClass() == Ground.class ||
                                object.getClass() == Obstacle.class) {
                            bullets.remove(bullet);
                            bulletSize--;
                        }
                    }
                }
            }
        }
    }

    public boolean hasFiredOnce(){
        return hasFiredOnce;
    }
    public void checkTime(){
        if(isTimeUp()){
            pcs.firePropertyChange("time", null, null);
            hasFiredOnce = true;
        }
    }
    /**
     *
     * @return the time object of this class
     */
    public Time getTime(){
        return time;
    }

    public boolean isTimeUp(){
            return time.isTimeUp();
    }

    public static int getEnemiesKilled(){
        return enemiesKilled;
    }

    public String getLevelName(){
        return levelName;
    }

    /**
     * Method to checck whether two objects are colliding or not
     * @param thisObject the first object
     * @param otherObject the other object
     * @return true if the two objects hitboxes are intersecting
     * and they are not the same object, false otherwise
     */
    public boolean isColliding(Rectangle thisObject, Rectangle otherObject){
        if(thisObject == null ||otherObject == null){
            return false;
        }else if(thisObject.equals(otherObject)){
            return false;
        }else{
            return thisObject.intersects(otherObject);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String eventName = evt.getPropertyName();
        if(eventName.equals("moveRight") || eventName.equals("moveLeft") ||
                eventName.equals("jump")){
            if(!hasPlayerMovedMoreThanOneTime){
                time.start();
            }
            hasPlayerMovedMoreThanOneTime = true;
        }
    }
}
