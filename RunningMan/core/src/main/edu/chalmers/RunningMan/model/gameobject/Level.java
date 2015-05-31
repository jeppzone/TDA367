package edu.chalmers.RunningMan.model.gameobject;

import edu.chalmers.RunningMan.model.Timer;

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
    private final List<Enemy> enemies;
    private final String levelName;
    private int enemiesKilled;
    private static final int MAX_TIME = 100;
    private final Timer timer;
    private int playerScore;
    private final PropertyChangeSupport pcs;
    private final Player player;

    public Level(Player player, List<AbstractPhysicalObject> mapObjects, List<Enemy> enemies, String levelName) {
        this.mapObjects = mapObjects;
        this.levelName = levelName;
        this.enemies = enemies;
        this.enemiesKilled = 0;
        this.player = player;
        player.addPropertyChangeListener(this);
        timer = new Timer(MAX_TIME);
        pcs = new PropertyChangeSupport(this);
        playerScore = 0;
    }

    /**
     * Method to be called continuously to check for
     * collisions all over the level between mapbjects and player,
     * player and enemies, enemies and mapobjects.
     */

    public void checkCollisions(List<Bullet> bullets) {
        checkBulletCollisions(bullets);
        for (AbstractPhysicalObject otherObject : mapObjects) {
            for (Enemy enemy : enemies) {
                if (isColliding(enemy.getHitbox(), otherObject.getHitbox())) {
                    otherObject.acceptVisitor(enemy);
                } else if (isColliding(enemy.getHitbox(), player.getHitbox())) {
                    enemy.acceptVisitor(player);
                }
            }
            if(isColliding(player.getHitbox(), otherObject.getHitbox())){
                otherObject.acceptVisitor(player);
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Method to check whether bullets are colliding with
     * any gameobject. The bullet should disappear if it collides with another
     * object. If it collides with an enemy, the enemy should disappear as well.
     * @param bullets
     */
    public void checkBulletCollisions(List<Bullet> bullets) {
        boolean hasRemoved; // Want to make sure that the iterator doesn't call remove twice
        final Iterator<Bullet> bulletIterator = bullets.iterator();
        final Iterator<AbstractPhysicalObject> objectIterator = mapObjects.iterator();
        final Iterator<Enemy> enemyIterator = enemies.iterator();
        while (bulletIterator.hasNext()) {
            hasRemoved = false;
            final Bullet bullet = bulletIterator.next();
            while (objectIterator.hasNext()) {
                final AbstractPhysicalObject object = objectIterator.next();
                if (isColliding(bullet.getHitbox(), object.getHitbox()) && !hasRemoved) {
                    bulletIterator.remove();
                    hasRemoved = true;
                }
            }
            if(!hasRemoved) {
                while (enemyIterator.hasNext()) {
                    final Enemy enemy = enemyIterator.next();
                    if (isColliding(enemy.getHitbox(), bullet.getHitbox()) && !hasRemoved) {
                        bullet.acceptVisitor(enemy);
                        bulletIterator.remove();
                        hasRemoved = true;
                        if (enemy.isDead()) {
                            enemyIterator.remove();
                            enemiesKilled++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Calculates the total player score for this level
     */
    public void setPlayerScore() {
        playerScore = getEnemiesKilled() + getTimeLeft();
    }

    /**
     *
     * @return the total player score for this level
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Metod used by the HUD to display the amount of time that is left
     * @return the amount of time that is left for this level
     */
    private int getTimeLeft() {
        return timer.getTimeLeftInteger();
    }

    /**
     * Method to be called y to check
     * it the level timer is up
     */
    public void checkTime() {
        if (isTimeUp()) {
            pcs.firePropertyChange("mainMenu", null, null);
        }
    }

    /**
     * @return the timer object of this class
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Method to check if timer is up
     * @return true if getTimeInteger >= getMaxTime
     */
    private boolean isTimeUp() {
        return timer.isTimeUp();
    }

    /**
     * Method used by the hud to display the current kill count for the player
     * @return the total number of enemies killed
     */

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    /**
     * @return the name of this level
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Method to check whether two gameobject are colliding or not
     * @param thisObject  the first object
     * @param otherObject the other object
     * @return true if the two gameobject hitboxes are intersecting
     * and they are not the same object, false otherwise
     */
    public boolean isColliding(Rectangle thisObject, Rectangle otherObject) {
        return !thisObject.equals(otherObject) && thisObject.intersects(otherObject);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String eventName = evt.getPropertyName();
        if (eventName.equals("startTimer")){
            timer.start();
            pcs.firePropertyChange(eventName, null, null);
        }
    }

}
