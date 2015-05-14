package edu.chalmers.RunningMan.entities;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * A class to represent a moving enemy.
 * @author Jesper
 */
public class Enemy extends AbstractLivingObject {

    private float velocity;
    private LivingState enemyState = LivingState.MOVING_LEFT;
    private boolean isShotInback, isShotInFront;

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
     * Enum to represent the different movement states a enemy can be in
     */

    public LivingState getEnemyState(){
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

    public boolean isShotInback(){
        return this.isShotInback;
    }

    public boolean isShotInFront(){
        return this.isShotInFront;
    }

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
    public void visit(Bullet b){
        // Enemy shall be hurt or killed
        if(b.getBulletSpeed()*velocity < 0){
            isShotInFront = true;
            propertyChangeSupport.firePropertyChange("enemyshotinfront", null, null);
        }else{
            isShotInback = true;
            propertyChangeSupport.firePropertyChange("enemyshotinback", null, null);
        }
        velocity = 0;
       // bullet disappears after hitting an enemy
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
