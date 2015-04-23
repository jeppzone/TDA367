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
    private MovingDirection movingDirection;
    private FacingDirection facingDirection;

    public Player(Weapon weapon, Position position, Size size, int maxHp) {
        super(size, position, maxHp);
        this.weapon = weapon;
        facingDirection = FacingDirection.RIGHT;
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

    }

    public float getVelocityY(){
        return velocityY;
    }

    public void moveLeft(float deltaTime){
        movingDirection = MovingDirection.LEFT;
        this.oldX = this.getPosition().getX();
        setNewX(deltaTime, getVelocityX());
        facingDirection = FacingDirection.LEFT;
    }

    public void moveRight(float deltaTime){
        movingDirection = MovingDirection.RIGHT;
        this.oldX = this.getPosition().getX();
        setNewX(deltaTime, getVelocityX());
        facingDirection = FacingDirection.RIGHT;
    }

    public void jump(int delta){
        if(isOnGround){
            setVelocityY(-1f);
            isOnGround = false;
        }
    }

    public void applyForce(float deltaTime){
        if(deltaTime < 0) {
            setVelocityY(Physics.getNewVelocity(getVelocityY(), deltaTime));
            Position position = getPosition();
            position.setY(Physics.getNewYPosition(position.getY(), getVelocityY(), deltaTime));
        }
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

}