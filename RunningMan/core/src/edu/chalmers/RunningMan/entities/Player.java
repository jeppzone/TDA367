package edu.chalmers.RunningMan.entities;

import javafx.geometry.Pos;

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
    private boolean isOnGround = true;
    private MovingDirection movingDirection;
    private FacingDirection facingDirection;
    private Gravity gravity = new Gravity(-800f);

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
            return 100f;
        }else{
            return -100f;
        }
    }

    public void update(){

    }

    public float getVelocityY(){
        return velocityY;
    }

    public void moveLeft(float deltaTime){
        if(getPosition().getY() == 0) { // replace with isOnGround when collisions are implemented
            movingDirection = MovingDirection.LEFT;
            this.oldX = this.getPosition().getX();
            setNewX(deltaTime, getVelocityX());
            facingDirection = FacingDirection.LEFT;
        }
    }

    public void moveRight(float deltaTime){
        if(getPosition().getY() == 0) {// replace with isOnGround when collisions are implemented
            movingDirection = MovingDirection.RIGHT;
            this.oldX = this.getPosition().getX();
            setNewX(deltaTime, getVelocityX());
            facingDirection = FacingDirection.RIGHT;
        }
    }

    public void jump(float deltaTime){
        if(getPosition().getY() == 0){// replace with isOnGround when collisions are implemented
            //isOnGround = false;
            setVelocityY(1000f);
        }
    }

    public void applyForce(float deltaTime) {
        setVelocityY(gravity.getNewVelocity(velocityY, deltaTime));
        Position pos = getPosition();
        pos.setY(gravity.getNewYPosition(pos.getY(), getVelocityY(), deltaTime));

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

    public void handleCollision(AbstractPhysicalObject apo){
        isOnGround = true;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Enemy e){

    }
    @Override
    public void visit(Player p){
        // This will never be the case, since there's only one player
    }

}