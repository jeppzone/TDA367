package edu.chalmers.RunningMan.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Armand on 2015-05-14.
 */
public class BulletTest extends Assert {
/*

    Testa positionen
    Testa farten
    testa setX och setY
    Om bullet rör sig åt ett viss håll ska player ha en viss state
    Det ska inte gå att skjuta oftate än weapons delay
*/
    private AnimationState animationState;
    private Player player;
    private Position position;
    private Bullet bullet;
    private Size size;
    private Weapon weapon;
    private float pos;
    private float posY;
    private float posX;
    private static final float DELTATIME = 5f;
    private static final float EPS =0.0001f;


    @Before
    public void setUp(){

        size = new Size(1,1);
        position = new Position(10,10);
        player = new Player(position,size,100);
        weapon = new Weapon(player);
        // Needs to be done to set haslandedFirsttime = true
        Ground g = new Ground(position,size);
        player.handleCollision(g);
    }


    public void setBulletDirRight() {

        player.moveLeft(DELTATIME);
        player.moveRight(DELTATIME);
        bullet = new Bullet(size, player.getPosition(), player.getLastMovedDirection());
    }

    public void setBulletDirLeft() {

        player.moveRight(DELTATIME);
        player.moveLeft(DELTATIME);
        bullet = new Bullet(size, player.getPosition(), player.getLastMovedDirection());
    }


    @Test(expected = NullPointerException.class)
    public void SetBulletNull(){
        bullet = new Bullet(null,null,0);
        bullet.getPosition();
    }

    @Test
    public void BulletPositiveVelocity(){

        setBulletDirRight();
        assertTrue(bullet.getVelocity() > 0);
    }

    @Test
    public void BulletNegativeVelocity(){

        setBulletDirLeft();
        assertTrue(bullet.getVelocity() < 0);
    }

    @Test
    public void BulletVelocityPrecision(){

        setBulletDirRight();
        bullet = new Bullet(size,player.getPosition(),player.getLastMovedDirection());
        assertTrue(Math.abs(400f - bullet.getVelocity()) < EPS);
    }

    @Test
    public void MoveBulletRight(){

        setBulletDirRight();
        pos = bullet.getPosition().getX();
        bullet.moveBullet(DELTATIME);
        assertTrue(bullet.getPosition().getX() > pos);
    }
    @Test
    public void MoveBulletLeft(){

        setBulletDirLeft();
        pos = bullet.getPosition().getX();
        bullet.moveBullet(DELTATIME);
        assertTrue(bullet.getPosition().getX() < pos);
    }

    @Test
    public void MoveBulletLeftFromJump(){

        setBulletDirLeft();
        player.jump(DELTATIME);
        posY = bullet.getPosition().getY();
        posX = bullet.getPosition().getX();
        bullet.moveBullet(DELTATIME);
        assertTrue(bullet.getPosition().getY() == posY && bullet.getPosition().getX() < posX);

    }

    @Test
    public void MoveBulletRightFromJump(){

        setBulletDirRight();
        player.jump(DELTATIME);
        posY = bullet.getPosition().getY();
        posX = bullet.getPosition().getX();
        bullet.moveBullet(DELTATIME);
        assertTrue(bullet.getPosition().getY() == posY && bullet.getPosition().getX() > posX);

    }


    public void testIsOutOfBounds(){

    }

    public void testAcceptVisitor()  {

    }

}