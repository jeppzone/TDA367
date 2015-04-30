package edu.chalmers.RunningMan.entities;

/**
 * A class to model a player
 * @author Jesper Olsson
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
        this.velocityX = 200f;
    }

    /**
     * Enum to represent the different horizontal directions a player can move
     */
    public enum MovingDirection{
        RIGHT,
        LEFT;
    }

    /**
     * Enum to represent the different directions a player cna be faced
     */
    public enum FacingDirection{
        RIGHT,
        LEFT;
    }

    public int getScore(){
        return score;
    }

    public float getVelocityX(){
        if(movingDirection == MovingDirection.RIGHT) {
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

    /**
     * Method to make the player move to the left.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void moveLeft(float deltaTime){
        //if(getPosition().getY() == 0) { // replace with isOnGround when collisions are implemented
            movingDirection = MovingDirection.LEFT;
            this.oldX = this.getPosition().getX();
            setNewX(deltaTime, getVelocityX());
            facingDirection = FacingDirection.LEFT;
        //}
    }
    /**
     * Method to make the player move to the right.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void moveRight(float deltaTime){
        //if(getPosition().getY() == 0) { // replace with isOnGround when collisions are implemented
            movingDirection = MovingDirection.RIGHT;
            this.oldX = this.getPosition().getX();
            setNewX(deltaTime, getVelocityX());
            facingDirection = FacingDirection.RIGHT;
        //}
    }
    /**
     * Method to make the player jump.
     * This can only be done if player is on the ground
     * @param deltaTime the time difference
     */
    public void jump(float deltaTime){
        if(getPosition().getY() == 0) {// replace with isOnGround when collisions are implemented
            isOnGround = false;
            setVelocityY(1000f);
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

    public MovingDirection getMovingDirection(){
        return movingDirection;
    }

    public FacingDirection getFacingDirection(){
        return facingDirection;
    }

    /**
     * Method to handle collisions between player and another object on the map
     * @param apo the object to collide with
     */
    public void handleCollision(AbstractPhysicalObject apo){
        isOnGround = true;
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

        if(playerX <= objX || playerX + playerWidth >= objX){
            pos.setX(oldX);
        }else if(playerY <= objY + objHeight && getVelocityY() > 0 ){
            pos.setY(playerHeight + objY);
            setVelocityY(0f);
        }else if(playerY <= objY && getVelocityY() > 0){
            pos.setY(objY - playerHeight);
            setVelocityY(0f);
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