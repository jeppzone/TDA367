package edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects;

import edu.chalmers.RunningMan.RunningMan;
import edu.chalmers.RunningMan.model.ISize;
import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;
import edu.chalmers.RunningMan.model.objects.Bullet;
import edu.chalmers.RunningMan.model.objects.Enemy;
import edu.chalmers.RunningMan.model.objects.Player;
import edu.chalmers.RunningMan.model.objects.Weapon;
import edu.chalmers.RunningMan.model.objects.Ground;
import org.junit.Assert;
import static edu.chalmers.RunningMan.utils.Constants.*;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by Armand on 2015-05-14.
 */
public class BulletTest extends Assert {

    private Player player;
    private Position position;
    private Weapon weapon;
    private Bullet bullet;
    private Size size;
    private float pos;
    private float posY;
    private float posX;
    private static final float DELTATIME = 5f;
    private static final float EPS =0.0001f;
    private final ISize windowSize = new Size(V_WIDTH * RunningMan.SCALE,V_HEIGHT * RunningMan.SCALE);
    private Enemy enemy;

    public void setBulletDirRight(){

        player.moveLeft(DELTATIME);
        player.moveRight(DELTATIME);
        bullet = new Bullet(size, player.getPosition(), player.getLastMovedDirection(),windowSize);
    }

    public void setBulletDirLeft(){

        player.moveRight(DELTATIME);
        player.moveLeft(DELTATIME);
        bullet = new Bullet(size, player.getPosition(), player.getLastMovedDirection(),windowSize);
    }

    @Before
    public void setUp(){

        size = new Size(1,1);
        position = new Position(10,0);
        player = new Player(position,size,100);
        weapon= new Weapon(player,windowSize);
        // Needs to be done to set haslandedFirsttime = true
        Ground g = new Ground(position,size);
        player.handleCollision(g);

    }

    @Test
    public void testSetX(){

        bullet = new Bullet(size,new Position(0f,0f), player.getLastMovedDirection(),windowSize);
        bullet.setX(position.getX());
        assertEquals(bullet.getPosition().getX(), position.getX(), 0);
    }

    @Test
    public void testSetXNegative(){

        bullet = new Bullet(size,new Position(0f,0f), player.getLastMovedDirection(),windowSize);
        bullet.setX(-10f);
        assertEquals(bullet.getPosition().getX(), 0, 0);

    }

    @Test
    public void testSetY(){

        bullet = new Bullet(size,new Position(0f,20f), player.getLastMovedDirection(),windowSize);
        bullet.setY(-10f);
        assertEquals(bullet.getPosition().getY(), 0, 0);
    }

    @Test
    public void testSetYNegative(){

        bullet = new Bullet(size,new Position(0f,15f), player.getLastMovedDirection(),windowSize);
        bullet.setY(-10f);
        assertEquals(bullet.getPosition().getX(), 0, 0);
    }

    @Test
    public void testSetPosition(){

        bullet = new Bullet(size,new Position(20f,20f),player.getLastMovedDirection(),windowSize);
        bullet.setPosition(position);
        assertEquals(bullet.getPosition(), position);
    }

    @Test
    public void testSetPositionNegative(){

        bullet = new Bullet(size,position,player.getLastMovedDirection(),windowSize);
        bullet.setPosition(new Position(-200, -200));
        assertEquals(bullet.getPosition(), new Position(0f, 0f));
    }

    @Test(expected = NullPointerException.class)
    public void testSetPositionNull(){

        bullet = new Bullet(size,position,player.getLastMovedDirection(),windowSize);
        bullet.setPosition(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetBulletNull(){

        bullet = new Bullet(null,null,0,windowSize);
        bullet.getPosition();
    }

    @Test
    public void testBulletPositiveVelocity(){

        setBulletDirRight();
        assertTrue(bullet.getVelocity() > 0);
    }

    @Test
    public void testBulletNegativeVelocity(){

        setBulletDirLeft();
        assertTrue(bullet.getVelocity() < 0);
    }

    @Test
    public void testBulletVelocityPrecision(){

        setBulletDirRight();
        bullet = new Bullet(size,player.getPosition(),player.getLastMovedDirection(),windowSize);
        assertTrue(Math.abs(400f - bullet.getVelocity()) < EPS);
    }

    @Test
    public void testMoveBulletRight(){

        setBulletDirRight();
        pos = bullet.getPosition().getX();
        bullet.moveBullet(DELTATIME);
        assertTrue(bullet.getPosition().getX() > pos);
    }

    @Test
    public void testMoveBulletLeft(){

        setBulletDirLeft();
        pos = bullet.getPosition().getX();
        bullet.moveBullet(DELTATIME);
        assertTrue(bullet.getPosition().getX() < pos);
    }

    @Test
    public void testMoveBulletLeftFromJump(){

        player.setY(20f);
        setBulletDirLeft();
        posX = bullet.getPosition().getX();
        bullet.moveBullet(DELTATIME);
        assertTrue(bullet.getPosition().getX() < posX);
    }

    @Test
    public void testMoveBulletRightFromJump(){

        player.setY(20f);
        setBulletDirRight();
        posX = bullet.getPosition().getX();
        bullet.moveBullet(DELTATIME);
        assertTrue(bullet.getPosition().getX() > posX);
    }

    @Test
    public void testBulletNotFalling(){

        player.setY(20f);
        setBulletDirRight();
        posY = bullet.getPosition().getY();
        bullet.moveBullet(DELTATIME);
        assertEquals(bullet.getPosition().getY(), posY, 0);
    }

    @Test
    public void testIsOutOfBoundsNegative(){

        bullet = new Bullet(size,position, player.getLastMovedDirection(),windowSize);
        bullet.setX(-10f);
        assertTrue(bullet.isOutOfBounds());
    }

    @Test
    public void testisOutOfBounds(){

        bullet = new Bullet(size, new Position(0f,0f), player.getLastMovedDirection(),windowSize);
        bullet.getPosition().setX(V_WIDTH * RunningMan.SCALE + 1);
        assertTrue(bullet.isOutOfBounds());
    }

    @Test
    public void testAcceptVisitor()  {

        bullet = new Bullet(size,position,player.getLastMovedDirection(),windowSize);
        enemy = new Enemy(position,size,100);
        bullet.acceptVisitor(enemy);
        assertEquals(enemy.getVelocity(),0,0);
    }


}