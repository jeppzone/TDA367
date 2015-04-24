package edu.chalmers.RunningMan.entities;


/**
 * A class to represent a moving enemy.
 * @author Jesper
 */
public class Enemy extends AbstractLivingObject {

    private float velocity;

    public Enemy(Position position, Size size){
        super(size, position, 10);
        velocity = -2f;
    }

    /**
     * Method to make the enemy move.
     * Will change direction if it reaches the end of the screen
     * @param delta the time difference
     */
    public void move(int delta){
        setNewX(delta, velocity);
        if(getPosition().getX() == 0){
            changeDirection();
        }
    }

    /**
     * Makes the enenmy move in the opposite direction
     * This method is called whenever an enemy is not able to move
     * in it's current direction
     */
    public void changeDirection(){
        this.velocity = -1 * this.velocity;
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

    }

    @Override
    public void visit(Player p){

    }
}
