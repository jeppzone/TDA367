package edu.chalmers.RunningMan.entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to model a level
 * @author Jesper Olsson
 */
public class Level {
    private final List<AbstractPhysicalObject> mapObjects;
    private final String levelName;
    private List<AbstractPhysicalObject> levelObjects;

    public Level(List<AbstractPhysicalObject> mapObjects, Player player, String levelName){
        this.mapObjects = mapObjects;
        this.levelName = levelName;
        mapObjects.add(player);
        levelObjects = new ArrayList<>();
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

    public void checkBulletCollisions(List<Bullet> bullets) {

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < mapObjects.size(); j++) {
                if (mapObjects.get(j) instanceof IVisitor) {

                    if (isColliding(bullets.get(i).getHitbox(), mapObjects.get(j).getHitbox())) {
                        if (mapObjects.get(j) instanceof AbstractLivingObject) {

                            bullets.remove(i);
                            mapObjects.remove(j);
                        } else if (bullets.get(i).getClass().equals(Obstacle.class) || bullets.get(i).getClass().equals(Ground.class)) {
                            bullets.remove(i);
                        }
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
    public boolean isColliding(Rectangle thisObject, Rectangle otherObject){
        if(thisObject == null ||otherObject == null){
            return false;
        }else if(thisObject.equals(otherObject)){
            return false;
        }else{
            return thisObject.intersects(otherObject);
        }
    }

}
