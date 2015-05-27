package edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects;

import edu.chalmers.RunningMan.RunningMan;
import edu.chalmers.RunningMan.model.ISize;
import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;
import edu.chalmers.RunningMan.model.LivingState;
import edu.chalmers.RunningMan.model.gameobjects.Bullet;
import edu.chalmers.RunningMan.model.gameobjects.Enemy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static edu.chalmers.RunningMan.utils.Constants.V_HEIGHT;
import static edu.chalmers.RunningMan.utils.Constants.V_WIDTH;

public class EnemyTest extends Assert {

    private Enemy enemy;
    private float testStartVelocity, velocity = -100;
    private Position position = new Position(10,10);
    private static final float DELTATIME = 2f;
    private final Size size = new Size(10f, 10f);
    private Bullet bullet;
    private final ISize windowSize = new Size(V_WIDTH * RunningMan.SCALE,V_HEIGHT * RunningMan.SCALE);

    @Before
    public void setUp(){
        enemy = new Enemy(position, size, 10);
        testStartVelocity = enemy.getVelocity();
    }

    @Test
    public void testGetPosition(){
        assertEquals(position, enemy.getPosition());
    }

    @Test
    public void testMove() {
        final float posX = enemy.getPosition().getX();
        enemy.move(DELTATIME);
        assertTrue(enemy.getPosition().getX() < posX);
    }

    @Test
    public void testChangeVelocity(){
        enemy.changeDirection();
        assertEquals(enemy.getVelocity(), velocity * -1, 0);
    }

    @Test
    public void testGetVelocity() {
        assertEquals(testStartVelocity, velocity,0);
    }

    @Test
    public void testSetPosition(){
        position = new Position(40f, 40f);
        enemy.setPosition(position);
        assertEquals(position, enemy.getPosition());
    }

    @Test
    public void testGetSize(){
        assertEquals(size, enemy.getSize());
    }

    @Test
    public void testSetX(){
        enemy.setX(10f);
        enemy.setX(position.getX());
        assertEquals(position.getX(), enemy.getPosition().getX(), 0);
    }

    @Test
    public void testSetY(){
        enemy.setY(15f);
        enemy.setY(position.getY());
        assertEquals(position.getY(), enemy.getPosition().getY(), 0);
    }

    @Test
    public void testGetHitbox(){
        final Rectangle rec = new Rectangle((int)position.getX(),
                (int)position.getY(),
                (int)size.getWidth(),
                (int)size.getHeight());
        assertEquals(rec, enemy.getHitbox());
    }
    @Test
    public void isBoss(){
        enemy = new Enemy(position,size,151);
        assertTrue(enemy.isBoss());
    }

    @Test
    public void isNormal(){
        enemy = new Enemy(position,size,150);
        assertFalse(enemy.isBoss());
    }

    @Test
    public void changeDirectionToRight(){
        enemy.changeDirection();
        assertTrue(enemy.getEnemyState() == LivingState.MOVING_RIGHT);
    }

    @Test
    public void changeDirectionToLeft(){
        enemy.changeDirection();
        enemy.changeDirection();
        assertTrue(enemy.getEnemyState() == LivingState.MOVING_LEFT);
    }

    @Test
    public void DeadEnemyNotMoving(){
        bullet = new Bullet(new Size(1,1),new Position(0,10),1,windowSize);
        enemy.visit(bullet);
        enemy.changeDirection();
        assertTrue(enemy.getEnemyState() == LivingState.STANDING);
    }

    @Test
    public void isDead(){
        bullet = new Bullet(new Size(1,1),new Position(0,10),1,windowSize);
        enemy.visit(bullet);
        assertTrue(enemy.isDead());
    }

    @Test
    public void IsShotInFront(){
        bullet = new Bullet(new Size(1,1),new Position(0,10),1,windowSize);
        enemy.visit(bullet);
        assertTrue(enemy.isShotInFront());
    }

    @Test
    public void IsShotInBack(){
        bullet = new Bullet(new Size(1,1),new Position(0,10),-1,windowSize);
        enemy.visit(bullet);
        assertTrue(enemy.isShotInBack());
    }
}