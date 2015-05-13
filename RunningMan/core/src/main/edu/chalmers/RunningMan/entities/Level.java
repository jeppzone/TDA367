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
    private static int enemiesKilled;

    public Level(List<AbstractPhysicalObject> mapObjects, String levelName){
        this.mapObjects = mapObjects;
        this.levelName = levelName;
        this.enemiesKilled = 0;
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

}
