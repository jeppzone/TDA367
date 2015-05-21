package edu.chalmers.RunningMan.model.level;

import edu.chalmers.RunningMan.model.*;
import edu.chalmers.RunningMan.model.level.mapobjects.Ground;
import edu.chalmers.RunningMan.model.level.mapobjects.Obstacle;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Enemy;
import edu.chalmers.RunningMan.model.level.mapobjects.IVisitor;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Bullet;
import edu.chalmers.RunningMan.model.HighScore;
import edu.chalmers.RunningMan.model.Timer;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Player;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.List;

/**
 * A class to model a level
 * @author Jesper Olsson
 */
public class Level implements PropertyChangeListener {
    private final List<AbstractPhysicalObject> mapObjects;
    private List<Enemy> enemies;
    private final String levelName;
    private int enemiesKilled;
    private static final int MAX_TIME = 100;
    private Timer timer;
    private int playerScore;
    private PropertyChangeSupport pcs;
    private HighScore highScores;
    private Player player;

    public Level(Player player, List<AbstractPhysicalObject> mapObjects, List<Enemy> enemies , String levelName){
        this.mapObjects = mapObjects;
        this.levelName = levelName;
        this.enemies = enemies;
        this.enemiesKilled = 0;
        this.player = player;
        player.addPropertyChangeListener(this);
        timer = new Timer(MAX_TIME);
        pcs = new PropertyChangeSupport(this);
        playerScore = 0;
        highScores = new HighScore(levelName);
        highScores.loadFromFile();
    }

    /**
     * Method to be called continuously to check for
     * collisions all over the level
     */

    public void checkCollisions(List<Bullet> bullets) {
        checkBulletCollisions(bullets);
        for(Enemy enemy: enemies){
            for(AbstractPhysicalObject otherObject: mapObjects){
                if(isColliding(enemy.getHitbox(), otherObject.getHitbox())){
                    otherObject.acceptVisitor(enemy);
                }else if(isColliding(enemy.getHitbox(), player.getHitbox())){
                    enemy.acceptVisitor(player);
                }else if(isColliding(player.getHitbox(), otherObject.getHitbox())){
                    otherObject.acceptVisitor(player);
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
    public void checkBulletCollisions(List<Bullet> bullets) {
        boolean hasRemoved;
        int bulletSize = bullets.size();
        int enemySize = enemies.size();
        Iterator<Bullet> bulletIterator = bullets.iterator();
        Iterator<AbstractPhysicalObject> objectIterator = mapObjects.iterator();
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (bulletIterator.hasNext() && bulletSize > 0) {
            hasRemoved = false;
            final Bullet bullet = bulletIterator.next();
            while (objectIterator.hasNext()) {
                final AbstractPhysicalObject object = objectIterator.next();
                if (isColliding(bullet.getHitbox(), object.getHitbox()) && !hasRemoved) {
                    bulletIterator.remove();
                    hasRemoved = true;

                }
            }
            while (enemyIterator.hasNext() && enemySize > 0) {
                final Enemy enemy = (Enemy) enemyIterator.next();
                if (isColliding(enemy.getHitbox(), bullet.getHitbox())) {
                    bullet.acceptVisitor(enemy);
                    bulletIterator.remove();
                    if (enemy.getHp() <= 0) {
                        enemyIterator.remove();
                        enemySize--;
                    }
                }
            }
        }
    }

    public void setPlayerScore(){
        playerScore = getEnemiesKilled() + getTimeLeft();
    }

    public int getTimeLeft(){
        return timer.getTimeLeftInteger();
    }

    /**
     * Method to be called continiously to check
     * it the level timer is up
     */
    public void checkTime(){
        if(isTimeUp()){
            pcs.firePropertyChange("timer", null, null);
        }
    }
    /**
     *
     * @return the timer object of this class
     */
    public Timer getTimer(){
        return timer;
    }

    /**
     * Method to check if timer is up
     * @return true if getTimeInteger >= getMaxTime
     */
    public boolean isTimeUp(){
            return timer.isTimeUp();
    }
    public int getEnemiesKilled(){
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
        if(eventName.equals("moveRight") || eventName.equals("moveLeft")){
            timer.start();
            pcs.firePropertyChange("startlevel", null, null);
        }
    }

    public void addScore(){
        setPlayerScore();
        highScores.addScore(playerScore);
        highScores.saveToFile();
    }

    public List<Integer> getHighScores(){
        return highScores.getHighScores();
    }
}
