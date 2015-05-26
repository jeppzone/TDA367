package edu.chalmers.RunningMan.model.level;

import edu.chalmers.RunningMan.RunningMan;
import edu.chalmers.RunningMan.model.AbstractPhysicalObject;
import edu.chalmers.RunningMan.model.ISize;
import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;
import edu.chalmers.RunningMan.model.level.mapobjects.Obstacle;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Enemy;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Bullet;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static edu.chalmers.RunningMan.utils.Constants.V_HEIGHT;
import static edu.chalmers.RunningMan.utils.Constants.V_WIDTH;

/**
 * @author Jesper Olsson
 */
public class LevelTest extends Assert {

    private Level level;
    private Player player;
    private final String name = "Beach";
    private List<AbstractPhysicalObject> mapObjects;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private final ISize windowSize = new Size(V_WIDTH * RunningMan.SCALE,V_HEIGHT * RunningMan.SCALE);


    @Before
    public void setUp(){
        this.player = new Player( new Position(400, 0), new Size(50,50),100);
        mapObjects = new ArrayList<>();
        enemies = new ArrayList<>();
        this.level = new Level(player, mapObjects, enemies, name);
        bullets = new ArrayList<>();
    }

    @Test
    public void testGetName(){
        final Level level = new Level(player, mapObjects, enemies, name);
        assertEquals(name, level.getLevelName());
    }

    @Test
    public void testIsCollidingSameObject(){
        final Rectangle rectangle = new Rectangle(20, 20);

        assertFalse(level.isColliding(rectangle, rectangle));
    }

    @Test
    public void testIsCollidingObjectNull(){
        final Rectangle rectangle = new Rectangle(20, 20);

        assertFalse(level.isColliding(rectangle, null));
    }

    @Test
    public void testIsCollidingBothNull(){
        assertFalse(level.isColliding(null, null));
    }

    @Test
    public void testIsCollidingDifferentObjects(){
        final Rectangle firstRectangle = new Rectangle(20, 20);
        final Rectangle secondRectangle = new Rectangle(20, 39);

        assertTrue(level.isColliding(firstRectangle, secondRectangle));
    }

    @Test
    public void testCheckCollisionsWithCollisions(){
        final Enemy enemy = new Enemy(new Position(0, 0), new Size(20 ,20), 1);
        final float velocityBeforeCollision = enemy.getVelocity();
        final Obstacle obstacle = new Obstacle(new Position(0, 0), new Size(15, 15));
        final List<AbstractPhysicalObject> mapObjects1 = new ArrayList<>();
        final List<Enemy> enemies1 = new ArrayList<>();
        mapObjects1.add(obstacle);
        enemies1.add(enemy);
        final Level level1 = new Level(player, mapObjects1, enemies1, name);
        level1.checkCollisions(bullets);

        assertNotEquals(velocityBeforeCollision, enemy.getVelocity());
    }

    @Test
    public void testCheckCollisionsWithoutCollisions(){
        final Enemy enemy = new Enemy(new Position(0, 0), new Size(20 ,20), 1);
        final float velocityBeforeCollision = enemy.getVelocity();
        final Obstacle obstacle = new Obstacle(new Position(100, 100), new Size(15, 15));
        final List<AbstractPhysicalObject> mapObjects1 = new ArrayList<>();
        final List<Enemy> enemies1 = new ArrayList<>();
        mapObjects1.add(obstacle);
        enemies1.add(enemy);
        final Level level1 = new Level(player, mapObjects1, enemies1, name);
        level1.checkCollisions(bullets);

        assertEquals(velocityBeforeCollision, enemy.getVelocity(), 0);
    }

    @Test
    public void testCheckBulletCollisionsWithCollisions(){
        final Bullet b1 = new Bullet(new Size(2,2),new Position(0,0),1,windowSize);
        final Bullet b2 = new Bullet(new Size(2,2),new Position(20,0),1,windowSize);
        final Obstacle obstacle = new Obstacle(new Position(0,0),new Size(3,3));
        final Enemy enemy = new Enemy(new Position(20, 0), new Size(20 ,20), 1);
        bullets.clear();
        bullets.add(b1);
        bullets.add(b2);
        mapObjects.clear();
        mapObjects.add(obstacle);
        enemies.clear();
        enemies.add(enemy);
        level = new Level(player,mapObjects,enemies,name);
        level.checkBulletCollisions(bullets);

        assertTrue(enemies.isEmpty() && bullets.isEmpty()
                && level.getEnemiesKilled() == 1);

    }

    @Test
    public void testCheckBulletCollisionsWithoutWCollisions(){
        final Bullet b1 = new Bullet(new Size(2,2),new Position(0,0),1,windowSize);
        final Bullet b2 = new Bullet(new Size(2,2),new Position(20,0),1,windowSize);
        final Obstacle obstacle = new Obstacle(new Position(5,0),new Size(3,3));
        final Enemy enemy = new Enemy(new Position(15, 0), new Size(1 ,1), 1);
        bullets.clear();
        bullets.add(b1);
        bullets.add(b2);
        mapObjects.clear();
        mapObjects.add(obstacle);
        enemies.clear();
        enemies.add(enemy);
        level = new Level(player,mapObjects,enemies,name);
        level.checkBulletCollisions(bullets);

        assertTrue(bullets.size() == 2 && mapObjects.size() == 1 && enemies.size() == 1);

    }


}
