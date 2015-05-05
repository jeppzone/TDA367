package edu.chalmers.RunningMan.entities;

import edu.chalmers.RunningMan.utils.PlayerState;

/**
 * Created by Armand on 2015-04-27.
 */

public class Bullet extends AbstractPhysicalObject implements IVisitor  {

    private final float BULLET_SPEED = 400f;
    private boolean bulletExists = true;
    private final PlayerState playerState;

    public Bullet(Size size, Position position, PlayerState playerState){
        super(size,position);
        this.playerState = playerState;
    }

    public float getBulletSpeed(){
        return BULLET_SPEED;
    }

    private void setBulletGone(){
        bulletExists = false;
    }

    /**
     * Moves the bullet in the players direction
     * @param deltaTime the time difference
     */
    public void moveBullet(float deltaTime){
            System.out.println("BulletMoving");
            setX(getPosition().getX() + playerState.xDirection * getBulletSpeed() * deltaTime);
    }
    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Enemy e) {
        //Call for some animation of the anime dying here
        //e.dieAnim();

        //Destroy the enemy Object
        e = null;

        // Enemy will disappear, in EnemyClass the bullet will disappear.
    }

    @Override
    public void visit(Player p) {
        //not possible
    }

    @Override
    public void visit(Obstacle g) {
        // bullet shall disappear in the obstacle.visit(Bullet b)

    }

    @Override
    public void visit(Bullet b) {
        //nothing should happen

    }

    @Override
    public void visit(Steroid s) {
        //nothing should happen

    }

    @Override
    public void visit(Ground g) {
        //not possible

    }
}
