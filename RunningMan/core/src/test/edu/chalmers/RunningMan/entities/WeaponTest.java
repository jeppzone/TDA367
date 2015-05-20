package edu.chalmers.RunningMan.entities;

import edu.chalmers.RunningMan.utils.WindowSize;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Armand on 2015-05-14.
 */
public class WeaponTest extends Assert {
    private float FIRE_DELAY;
    private Position position;
    private ISize size, windowSize;
    private Player player;
    private Weapon weapon;
    private Bullet bullet;
    private List<Bullet> bullets;

    public Weapon makeWeapon(){

        return new Weapon(player,windowSize);
    }

    public void placeBullet(){


    }


    @Before
    public void setUp(){

        FIRE_DELAY = 500f;
        position = new Position(20,00);
        size = new Size(1,1);
        windowSize = new WindowSize();
        player = new Player(position, size, 100);
    }

    @Test
    public void testGetfireDelay() {

        assertEquals(makeWeapon().getfireDelay(),FIRE_DELAY,0);
    }
    @Test
    public void testGetEmptyBulletList(){

        weapon = makeWeapon();
        weapon.getBullets().clear();
        assertTrue(weapon.getBullets().size() == 0);
    }

    @Test
    public void testGetBulletList(){

        weapon = makeWeapon();
        weapon.getBullets().add(new Bullet(size,position, player.getLastMovedDirection(),windowSize));
        weapon.getBullets().add(new Bullet(size,position, player.getLastMovedDirection(),windowSize));
        weapon.getBullets().add(new Bullet(size,position, player.getLastMovedDirection(),windowSize));
        weapon.getBullets().remove(2);
        assertTrue(weapon.getBullets().size() == 2);
    }

    @Test
    public void testGetBulletCorrectBullet(){

        weapon = makeWeapon();
        weapon.getBullets().add(new Bullet(size, position, player.getLastMovedDirection(), windowSize));
        weapon.getBullets().add(new Bullet(size,new Position(50,50), player.getLastMovedDirection(),windowSize));
        weapon.getBullets().add(new Bullet(size, position, player.getLastMovedDirection(), windowSize));
        weapon.getBullets().remove(2);
        weapon.getBullets().remove(0);
        assertEquals(new Position(50, 50), weapon.getBullets().get(0).getPosition());
    }

    @Test
    public void testShoot(){

        weapon = makeWeapon();
        weapon.shoot();
        assertTrue(weapon.getHasShot());
    }

    @Test
    public void testDidNotShoot() {

        weapon = makeWeapon();
        assertFalse(weapon.getHasShot());
    }

    @Test
    public void testBulletCorrectPlacement(){

        weapon = makeWeapon();
        weapon.placeBullet();
        weapon.getPlayer().moveLeft(0);
        weapon.placeBullet();
        assertEquals(weapon.getBullets().get(0).getPosition().getX(), weapon.getBullets().get(1).getPosition().getX() + 50,0);
    }
}