package edu.chalmers.RunningMan.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A class to model a player
 */
public class Player extends AbstractLivingObject {

    private int killCount = 0;
    private int score = 0;
    private Weapon weapon;
    private float velocityX;
    private float velocityY;
    private float oldX;
    private boolean isOnGround;
    private long lastMovement;
    private MovingDirection movingDirection;
    private FacingDirection facingDirection;
    private PropertyChangeSupport pcs;

    public Player(Weapon weapon, Position position, Size size, int maxHp) {
        super(size, position, maxHp);
        this.weapon = weapon;
        facingDirection = FacingDirection.RIGHT;
        pcs = new PropertyChangeSupport(this);
    }

    public enum MovingDirection{
        RIGHT,
        LEFT;
    }

    public enum FacingDirection{
        RIGHT,
        LEFT;
    }

    public int getScore(){
        return score;
    }

    public float getVelocityX(){
        if(movingDirection == MovingDirection.RIGHT){
            return 0.5f;
        }else{
            return -0.5f;
        }
    }

    public void update(){
        if(lastMovement + 100 >= System.currentTimeMillis()){
            pcs.firePropertyChange(movingDirection.toString(), null, getPosition());
        }else{
            pcs.firePropertyChange(facingDirection.toString(), null, getPosition());
        }
    }

    public float getVelocityY(){
        return velocityY;
    }

    public void moveLeft(int delta){
        movingDirection = MovingDirection.LEFT;
        this.oldX = this.getPosition().getX();
        setNewX(delta, getVelocityX());
        facingDirection = FacingDirection.LEFT;
        lastMovement = System.currentTimeMillis();
    }

    public void moveRight(int delta){
        movingDirection = MovingDirection.RIGHT;
        this.oldX = this.getPosition().getX();
        setNewX(delta, getVelocityX());
        facingDirection = FacingDirection.RIGHT;
        lastMovement = System.currentTimeMillis();
    }

    public void jump(int delta){
        if(isOnGround){
            setVelocityY(-1f);
            pcs.firePropertyChange("jump", null, null);
            isOnGround = false;
        }
    }

    public void applyForce(int deltaTime){
        setVelocityY(Physics.getNewVelocity(getVelocityY(), deltaTime));
    }

    public void die(){
        pcs.firePropertyChange("die", null, null);
    }

    public void setVelocityY(float newVelocityY){
        this.velocityY = newVelocityY;
    }

    public void incrementScore(int amount){
        this.score += amount;
    }

    public void incrementKillCount() {
        this.killCount += 1;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcs){
        this.pcs.addPropertyChangeListener(pcs);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pcs){
        this.pcs.removePropertyChangeListener(pcs);
    }
}