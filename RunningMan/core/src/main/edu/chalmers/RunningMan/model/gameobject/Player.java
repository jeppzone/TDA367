package edu.chalmers.RunningMan.model.gameobject;

import edu.chalmers.RunningMan.model.*;

import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * A class to model a player
 * @author Jesper Olsson
 */
public class Player extends AbstractLivingObject {

    private final int LAST_MOVE_LEFT = -1;
    private final int LAST_MOVE_RIGHT = 1;

    private float velocityX = 120f;
    private float velocityY;
    private float oldX;
    private boolean finishedLevel;
    private boolean isOnGround;
    private boolean hasLandedFirstTime;
    private boolean hasMovedFirstTime;

    private Gravity gravity = new Gravity(-800f);
    private List<AbstractPowerUp> powerUps;

    private LivingState animationState = LivingState.FACING_RIGHT;
    private int lastMovedDirection = LAST_MOVE_RIGHT;
    private long lastTimeMoved;
    public boolean isDeadByPitfall;

    private final PropertyChangeSupport propertyChangeSupport;

    public Player(Position position, ISize size, int maxHp) {
        super(size, position, maxHp);
        powerUps = new ArrayList<>();
        propertyChangeSupport = new PropertyChangeSupport(this);
        isDead = false;
        finishedLevel = false;
        hasLandedFirstTime = false;
        isOnGround = false;
        hasMovedFirstTime = false;
    }

    /**
     * Adds a Property change listener to the player
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener from the player
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * The current X-velocity of the player
     * @return a negative value if player is moving left, positive otherwise
     */
    public float getVelocityX(){
        if(lastMovedDirection == LAST_MOVE_RIGHT ) {
            return this.velocityX;
        }else{
            return -this.velocityX;
        }
    }

    /**
     * Tells the player if it is on ground or not
     * @param isOnGround true if player is on ground, false otherwise
     */
    public void setOnGround(boolean isOnGround){
        this.isOnGround = isOnGround;
    }

    /**
     *
     * @return the current Y-velocity of the player
     */
    public float getVelocityY(){
        return this.velocityY;
    }

    /**
     *
     * @return true if player is on ground, false otherwise
     */
    public boolean isOnGround() {
        return isOnGround;
    }

    /**
     * Method to tell if the player has been killed by falling down into a pit
     * @return
     */
    public boolean isDeadByPitfall(){
        return isDeadByPitfall;
    }

    /**
     *
     * @return the current state of the player so that the view will know
     * what animation to use
     */
    public LivingState getAnimationState(){
        return animationState;
    }

    /**
     * Updates the current player state
     * @param deltaTime the difference in tme
     */
    public void update(float deltaTime) {
        checkPowerUpsTime(deltaTime);
        // if jumping to the right
        if(!isOnGround() && lastMovedDirection == LAST_MOVE_RIGHT) {
            animationState = LivingState.JUMPING_RIGHT;
        }
        // if jumping to the left
        else if(!isOnGround() && lastMovedDirection == LAST_MOVE_LEFT) {
            animationState = LivingState.JUMPING_LEFT;
        }
        // if player does move
        else if(lastTimeMoved + 150 >= System.currentTimeMillis()){
            if(lastMovedDirection == LAST_MOVE_RIGHT) {
                animationState = LivingState.MOVING_RIGHT;
            } else {
                animationState = LivingState.MOVING_LEFT;
            }
        }
        // if player standing still
        else {
            if(lastMovedDirection == LAST_MOVE_RIGHT) {
                animationState = LivingState.FACING_RIGHT;
            } else {
                animationState = LivingState.FACING_LEFT;
            }
        }
    }

    /**
     * Method to make the player move to the left.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void moveLeft(float deltaTime){
        lastMovedDirection = LAST_MOVE_LEFT;
        this.oldX = this.getPosition().getX();
        setNewX(deltaTime, getVelocityX());
        lastTimeMoved = System.currentTimeMillis();
        if(!hasMovedFirstTime){
            propertyChangeSupport.firePropertyChange("startTimer" , null, null);
            hasMovedFirstTime = true;
        }
    }
    
    /**
     * Method to make the player move to the right.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void moveRight(float deltaTime){
        lastMovedDirection = LAST_MOVE_RIGHT;
        this.oldX = this.getPosition().getX();
        setNewX(deltaTime, getVelocityX());
        lastTimeMoved = System.currentTimeMillis();
        if(!hasMovedFirstTime){
            propertyChangeSupport.firePropertyChange("startTimer" , null, null);
            hasMovedFirstTime = true;
        }
    }

    /**
     * Method to make the player jump.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void jump(float deltaTime){
        if(isOnGround && hasMovedFirstTime){
            setVelocityY(300f);
            isOnGround = false;
            propertyChangeSupport.firePropertyChange("jump", null, null);
        }
    }

    public boolean hasLandedFirstTime(){
        return hasLandedFirstTime;
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

    /**
     * Sets a new X-velocity for the player
     * @param newVelocityX the new velocity, should always be >= 0
     */
    public void setVelocityX(float newVelocityX){
       this.velocityX = newVelocityX;
    }

    /**
     * Sets a new Y-velocity for the player
     * @param newVelocityY the new velocity
     */
    public void setVelocityY(float newVelocityY){
        this.velocityY = newVelocityY;
    }

    /**
     * Gets the last direction that the player moved in. Initially set to 1
     * @return 1 if last movement was right, -1 if it was left
     */
    public int getLastMovedDirection(){
        return lastMovedDirection;
    }

    /**
     * Method to handle collisions between player and another object on the map
     * @param apo the object to collide with
     */
    public void handleCollision(AbstractPhysicalObject apo){
        hasLandedFirstTime = true;
        final Position pos = getPosition();
        final float playerX = pos.getX();
        final float playerY = pos.getY();
        final ISize size = getSize();
        final float playerWidth = size.getWidth();
        final float playerHeight = size.getHeight();

        final Position objPos = apo.getPosition();
        final float objX = objPos.getX();
        final float objY = objPos.getY();
        final ISize objSize = apo.getSize();
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

    /**
     * Method to check if the time of the player's powerups are done
     * @param deltaTime the time difference
     */
    private void checkPowerUpsTime(float deltaTime){
        List<AbstractPowerUp> removedPowerUps = new ArrayList<>();
        for(AbstractPowerUp powerUp: powerUps){
            powerUp.updateTime(deltaTime);
            if(powerUp.isTimeUp()) {
                if (powerUp instanceof Steroid) {
                    setVelocityX(this.velocityX / 2f);

                    removedPowerUps.add(powerUp);
                }
            }
        }
        powerUps.removeAll(removedPowerUps);
    }

    /**
     * Method to check if player has finished the current level
     * @return true if player has reached the helicopter, false otherwise
     */
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
        setVelocityX(2f * this.velocityX);
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
        isDead = true;
        propertyChangeSupport.firePropertyChange("die", null, null);
    }
}