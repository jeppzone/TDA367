package edu.chalmers.RunningMan.entities;

import edu.chalmers.RunningMan.utils.PlayerState;

/**
 * A class to model a player
 * @author Jesper Olsson
 */
public class Player extends AbstractLivingObject {

    private final int LAST_MOVE_LEFT = -1;
    private final int LAST_MOVE_RIGHT = 1;

    private int killCount = 0;
    private int score = 0;
    private Weapon weapon;

    private float velocityX = 200f;

    private float velocityY;
    private float oldX;
    private int firstCounter = 0;
    private int secondConter = 0;
    private boolean isOnGround = false;
    private PlayerState facingDirection;
    private Gravity gravity = new Gravity(-800f);
    private static float time = 0;
    private static float passedTime= 0;
    private long previousFiredBulletTime = 0;
    private boolean hasShot = false;


    private PlayerState playerState = PlayerState.FACING_RIGHT;
    private int lastMovedDirection = LAST_MOVE_RIGHT;
    private long lastTimeMoved;

    public Player(Weapon weapon, Position position, Size size, int maxHp) {
        super(size, position, maxHp);
        this.weapon = weapon;
        facingDirection = PlayerState.FACING_RIGHT;

    }

    public int getScore(){
        return score;
    }

    public float getVelocityX(){
        if(playerState == PlayerState.MOVING_RIGHT) {
            return this.velocityX;
        }else{
            return -this.velocityX;
        }
    }

    public float getVelocityY(){
        return velocityY;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public PlayerState getPlayerState(){
        return playerState;
    }

    /**
     * Updates the current player state.
     */
    public void update(float deltaTime) {

        if(hasShot){
            passedTime += 1000*deltaTime;

            if(passedTime >= weapon.getfireDelay()){
                hasShot = false;
                passedTime = 0;
            }

        }

        // if jumping to the right
        if(!isOnGround() && lastMovedDirection == LAST_MOVE_RIGHT) {
            playerState = PlayerState.JUMPING_RIGHT;
        }

        // if jumping to the left
        else if(!isOnGround() && lastMovedDirection == LAST_MOVE_LEFT) {
            playerState = PlayerState.JUMPING_LEFT;
        }

        // if player does move
        else if(lastTimeMoved + 150 >= System.currentTimeMillis()){

            if(lastMovedDirection == LAST_MOVE_RIGHT) {
                playerState = PlayerState.MOVING_RIGHT;
            } else {
                playerState = PlayerState.MOVING_LEFT;
            }
        }

        // if player standing still
        else {

            if(lastMovedDirection == LAST_MOVE_RIGHT) {
                playerState = PlayerState.FACING_RIGHT;
            } else {
                playerState = PlayerState.FACING_LEFT;
            }
        }
    }

    /**
     * Method to make the player move to the left.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void moveLeft(float deltaTime){
        //if(getPosition().getY() == 0) { // replace with isOnGround when collisions are implemented
            playerState = PlayerState.MOVING_LEFT;
            this.oldX = this.getPosition().getX();
            setNewX(deltaTime, getVelocityX());
            lastTimeMoved = System.currentTimeMillis();
            lastMovedDirection = LAST_MOVE_LEFT;

            facingDirection = PlayerState.FACING_LEFT;

    }
    
    /**
     * Method to make the player move to the right.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void moveRight(float deltaTime){
        //if(getPosition().getY() == 0) { // replace with isOnGround when collisions are implemented
            playerState = PlayerState.MOVING_RIGHT;
            this.oldX = this.getPosition().getX();
            setNewX(deltaTime, getVelocityX());
            lastTimeMoved = System.currentTimeMillis();
            lastMovedDirection = LAST_MOVE_RIGHT;

            facingDirection = PlayerState.FACING_RIGHT;

    }

    /**
     * Method to make the player jump.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void jump(float deltaTime){
        if(isOnGround) {
            setVelocityY(1000f);
            isOnGround = false;
        }
    }

    /**
     * Method to make the player shoot, has a delay of 0.5 seconds
     *
     */

    public void shoot(){
        if(!hasShot){
            weapon.shoot();
            hasShot =true;
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

    /**
     * Method to increment the players kill count.
     * This is done whenever the player kills an enemy
     */
    public void incrementKillCount() {
        this.killCount += 1;
    }

    public PlayerState getFacingDirection(){
        return facingDirection;
    }

    /**
     * Method to handle collisions between player and another object on the map
     * @param apo the object to collide with
     */
    public void handleCollision(AbstractPhysicalObject apo){
        System.out.println("HANDLE COLLISION");
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

        if(playerY <= objY + objHeight && getVelocityY() < 0 ) {
            pos.setY(objHeight + objY - 1);
            setVelocityY(0f);
            isOnGround = true;
        }else if(playerY <= objY && getVelocityY() > 0){
            pos.setY(objY - playerHeight);
            setVelocityY(0f);
        }else if((playerX <= objX || playerX + playerWidth >= objX) && playerY < objHeight + objY - 1) {
            pos.setX(oldX);
        }
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Enemy e){
        isDead = true;
        System.out.println("Collision with enemy");
    }

    @Override
    public void visit(Player p){
        // This will never be the case, since there's only one player
    }

    public void visit(Obstacle g){
        handleCollision(g);
    }

    public void visit(Steroid s){
        setVelocityX(2f*this.velocityX);
        System.out.println("Collision with steroid");
    }

    @Override
    public void visit(Bullet b){
        //Nothing shall happen here
    }

    public void visit(Ground g) {
        handleCollision(g);
    }

}