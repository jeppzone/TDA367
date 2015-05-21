package edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects;

import edu.chalmers.RunningMan.model.*;
import edu.chalmers.RunningMan.model.level.mapobjects.*;
import edu.chalmers.RunningMan.model.level.mapobjects.powerups.Steroid;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.AbstractLivingObject;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.AnimationState;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * A class to represent a moving enemy.
 * @author Jesper
 */
public class Enemy extends AbstractLivingObject {

    private float velocity;
    private AnimationState enemyState = AnimationState.MOVING_LEFT;
    private boolean isShotInback, isShotInFront;
    private static final int DAMAGE = 100;

    private final PropertyChangeSupport propertyChangeSupport;

    public Enemy(Position position, Size size, int maxHp){
        super(size, position, maxHp);
        velocity = -100f;
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Returns the current state of the enemy, so that the view
     * will know which animation to use
     */

    public AnimationState getEnemyState(){
        return enemyState;
    }

    /**
     * Method to make the enemy move.
     * Will change direction if it reaches the end of the screen
     * @param delta the time difference
     */
    public void move(float delta){
        setNewX(delta, velocity);
        /*
        if(getPosition().getX() == 0 || getPosition().getX() > 400){//Not dynamically suitable
            changeDirection();
        }
        */
    }

    /**
     * Makes the enenmy move in the opposite direction
     * This method is called whenever an enemy is not able to move
     * in it's current direction
     */
    public void changeDirection(){
        this.velocity = -1 * this.velocity;
        if(velocity > 0){
            enemyState = enemyState.MOVING_RIGHT;
        }else if (velocity < 0){
            enemyState = enemyState.MOVING_LEFT;
        }else{
            enemyState = enemyState.STANDING;
        }
    }

    /**
     * Method to check if enemy is shot in back, for audio purposes
     * @return true if the bullet hits enemy from behind, false otherwise
     */
    public boolean isShotInback(){
        return this.isShotInback;
    }

    /**
     * Method to check if enemy is shot in fron, for audio purposes
     * @return true if the bullet hits in front of enemy, false otherwise
     */
    public boolean isShotInFront(){
        return this.isShotInFront;
    }

    /**
     * @return the current X-velocity of the enemy
     */
    public float getVelocity(){
        return this.velocity;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Enemy e){
        if(this != e) {
            changeDirection();
        }
    }
    @Override
    public void visit(Obstacle g){
        changeDirection();
    }

    @Override
    public void visit(Player p){
        //Nothing should happen with the enemy in this case
    }
    @Override
    public void visit(Bullet b) {
       takeDamage(DAMAGE);
        if (isDead()) {
            if (b.getVelocity() * velocity < 0) {
                isShotInFront = true;
                propertyChangeSupport.firePropertyChange("enemyshotinfront", null, null);
            } else {
                isShotInback = true;
                propertyChangeSupport.firePropertyChange("enemyshotinback", null, null);
            }
            this.velocity = 0;
        }

    }

    @Override
    public void visit(Steroid s){
        //Nothing should happen with the enemy in this case
    }

    @Override
    public void visit(Ground g) {
        changeDirection();
    }

    @Override
    public void visit(Helicopter f){
        //Nothing should happen here
    }

    @Override
    public void visit(Pit pit) {
        // enemy dies
    }
}
