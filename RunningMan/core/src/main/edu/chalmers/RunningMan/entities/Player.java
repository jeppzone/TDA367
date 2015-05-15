package edu.chalmers.RunningMan.entities;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * A class to model a player
 * @author Jesper Olsson
 */
public class Player extends AbstractLivingObject  {

    private final int LAST_MOVE_LEFT = -1;
    private final int LAST_MOVE_RIGHT = 1;
    private int score = 0;

    private float velocityX = 120f;
    private float velocityY;
    private float oldX;
    private Time timeSinceJump;
    private boolean finishedLevel = false;
    private boolean isOnGround = false;
    private boolean hasLandedFirsTime = false;

    private LivingState facingDirection;
    private Gravity gravity = new Gravity(-800f);
    private List<AbstractPowerUp> powerUps;

    private LivingState livingState = LivingState.FACING_RIGHT;
    private int lastMovedDirection = LAST_MOVE_RIGHT;
    private long lastTimeMoved;
    public boolean isDeadByPitfall;

    private final PropertyChangeSupport propertyChangeSupport;

    public Player(Position position, Size size, int maxHp) {
        super(size, position, maxHp);
        facingDirection = LivingState.FACING_RIGHT;
        powerUps = new ArrayList<>();
        propertyChangeSupport = new PropertyChangeSupport(this);
        timeSinceJump = new Time(0.3f);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public int getKillCount(){
        return Level.getEnemiesKilled();
    }

    public int getScore(){
        return score;
    }

    public float getVelocityX(){

        if(livingState == LivingState.MOVING_RIGHT || facingDirection == LivingState.FACING_RIGHT ) {
            return this.velocityX;
        }else{
            return -this.velocityX;
        }
    }
    public void setOnGround(boolean isOnGround){
        this.isOnGround = isOnGround;
    }
    public float getVelocityY(){
        return velocityY;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public boolean isDeadByPitfall(){
        return isDeadByPitfall;
    }

    public LivingState getLivingState(){
        return livingState;
    }

    /**
     * Updates the current player state.
     */
    public void update(float deltaTime) {
        System.out.println(isOnGround);
        checkPowerUpsTime(deltaTime);
        // if jumping to the right
        if(!isOnGround() && lastMovedDirection == LAST_MOVE_RIGHT) {
            livingState = LivingState.JUMPING_RIGHT;
        }

        // if jumping to the left
        else if(!isOnGround() && lastMovedDirection == LAST_MOVE_LEFT) {
            livingState = LivingState.JUMPING_LEFT;
        }

        // if player does move
        else if(lastTimeMoved + 150 >= System.currentTimeMillis()){

            if(lastMovedDirection == LAST_MOVE_RIGHT) {
                livingState = LivingState.MOVING_RIGHT;
            } else {
                livingState = LivingState.MOVING_LEFT;
            }
        }

        // if player standing still
        else {

            if(lastMovedDirection == LAST_MOVE_RIGHT) {
                livingState = LivingState.FACING_RIGHT;
            } else {
                livingState = LivingState.FACING_LEFT;
            }
        }
    }

    /**
     * Method to make the player move to the left.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void moveLeft(float deltaTime){
        if(hasLandedFirsTime) {
            livingState = LivingState.MOVING_LEFT;
            this.oldX = this.getPosition().getX();
            setNewX(deltaTime, getVelocityX());
            lastTimeMoved = System.currentTimeMillis();
            lastMovedDirection = LAST_MOVE_LEFT;
            facingDirection = LivingState.FACING_LEFT;
        }
    }
    
    /**
     * Method to make the player move to the right.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void moveRight(float deltaTime){
        if(hasLandedFirsTime) {
            livingState = LivingState.MOVING_RIGHT;
            this.oldX = this.getPosition().getX();
            setNewX(deltaTime, getVelocityX());
            lastTimeMoved = System.currentTimeMillis();
            lastMovedDirection = LAST_MOVE_RIGHT;
            facingDirection = LivingState.FACING_RIGHT;
        }

    }

    /**
     * Method to make the player jump.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void jump(float deltaTime){
        if(isOnGround) {
            setVelocityY(300f);
            isOnGround = false;
            propertyChangeSupport.firePropertyChange("jump", null, null);
        }
    }



    /**
     * Method to apply gravity force to the player.
     * This is called every time a player moves
     * @param deltaTime the time difference
     */
    public void applyForce(float deltaTime) {
            setVelocityY(gravity.getNewVelocity(velocityY, deltaTime));
            Position pos = getPosition();
            pos.setY(gravity.getNewYPosition(pos.getY(), getVelocityY(), deltaTime));

    }

    public void setVelocityX(float newVelocityX){
       this.velocityX = newVelocityX;
    }

    public void setVelocityY(float newVelocityY){
        this.velocityY = newVelocityY;
    }

    public LivingState getFacingDirection(){
        return facingDirection;
    }

    /**
     * Method to handle collisions between player and another object on the map
     * @param apo the object to collide with
     */
    public void handleCollision(AbstractPhysicalObject apo){
        hasLandedFirsTime = true;
        final Position pos = getPosition();
        final float playerX = pos.getX();
        final float playerY = pos.getY();
        final Size size = getSize();
        final float playerWidth = size.getWidth();
        final float playerHeight = size.getHeight();

        final Position objPos = apo.getPosition();
        final float objX = objPos.getX();
        final float objY = objPos.getY();
        final Size objSize = apo.getSize();
        final float objHeight = objSize.getHeight();
        final float newHeight = objHeight + objY - 1;

        if(playerY <= objY && getVelocityY() > 0) {
            pos.setY(objY - playerHeight);
            setVelocityY(0f);
        }else if(playerY <= objY + objHeight && getVelocityY() < 0) {
            pos.setY(newHeight);
            setVelocityY(0f);
            isOnGround = true;
        }else if((playerX <= objX || playerX + playerWidth >= objX) && (playerY < newHeight)) {
            pos.setX(oldX);
        }
    }

    private void checkPowerUpsTime(float deltaTime){
        List<AbstractPowerUp> removedPowerUps = new ArrayList<>();
        for(AbstractPowerUp powerUp: powerUps){
            powerUp.updateTime(deltaTime);
            if(powerUp.isTimeUp()) {
                if (powerUp instanceof Steroid) {
                    setVelocityX(getVelocityX() / 2f);

                    removedPowerUps.add(powerUp);
                }
            }
        }
        powerUps.removeAll(removedPowerUps);
    }

    public boolean hasFinishedLevel(){
        return finishedLevel;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Enemy e){
        isDead = true;
        propertyChangeSupport.firePropertyChange("suicide", null, null);
    }

    @Override
    public void visit(Player p){
        // This will never be the case, since there's only one player
    }

    @Override
    public void visit(Obstacle g){
        handleCollision(g);
    }

    @Override
    public void visit(Steroid s){
        powerUps.add(s);
        setVelocityX(2f * getVelocityX());
        propertyChangeSupport.firePropertyChange("pickupsteroid", null, null);
    }

    @Override
    public void visit(Bullet b){
        //Nothing shall happen here
    }

    @Override
    public void visit(Ground g) {
        handleCollision(g);
    }

    @Override
    public void visit(Helicopter f){
        setX(f.getPosition().getX());
        setY(f.getPosition().getY() - 20);
        finishedLevel = true;
    }

    @Override
    public void visit(Pit pit) {
        handleCollision(pit);
        isDeadByPitfall = true;
        propertyChangeSupport.firePropertyChange("die", null, null);
    }
}