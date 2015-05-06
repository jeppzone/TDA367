package edu.chalmers.RunningMan.entities;

import java.awt.*;
import java.util.List;

/**
 * A class to model a level
 * @author Jesper Olsson
 */
public class Level {
    private final List<AbstractPhysicalObject> mapObjects;
    private final String levelName;

    public Level(List<AbstractPhysicalObject> mapObjects, Player player, String levelName){
        this.mapObjects = mapObjects;
        this.levelName = levelName;
        mapObjects.add(player);
    }

    /**
     * Method to be called continuously to check for
     * collisions all over the level
     */
    public void checkCollisions(){
        for(AbstractPhysicalObject thisApo: mapObjects){
            if(thisApo instanceof IVisitor){
                for(AbstractPhysicalObject otherApo: mapObjects){
                    if(isColliding(thisApo.getHitbox(), otherApo.getHitbox())){
                        System.out.println(thisApo.getClass());
                        System.out.println(otherApo.getClass());
                        IVisitor visitor = (IVisitor) thisApo;
                        otherApo.acceptVisitor(visitor);
                    }
                }
            }
        }
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
    private boolean isColliding(Rectangle thisObject, Rectangle otherObject){
        if(thisObject == null ||otherObject == null){
            return false;
        }else if(thisObject.equals(otherObject)){
            return false;
        }else{
            return thisObject.intersects(otherObject);
        }
    }

}
