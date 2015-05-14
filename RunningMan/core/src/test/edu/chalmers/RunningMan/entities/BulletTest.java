package edu.chalmers.RunningMan.entities;

import org.junit.Assert;
import org.junit.Before;

/**
 * Created by Armand on 2015-05-14.
 */
public class BulletTest extends Assert {
/*
    Kom ihåg att ändra i enemy så att rätt enum används
    Testa positionen
    Testa farten
    testa setX och setY
    Om bullet rör sig åt ett viss håll ska player ha en viss state
    Det ska inte gå att skjuta oftate än weapons delay
*/
    private LivingState livingState;
    private Player player;
    private Position position;
    private Bullet bullet;
    private Size size;
    private Weapon weapon;

    @Before
    public void setUp(){
        size = new Size(1,1);
        position = new Position(10,10);
        player = new Player(position,size,100);
        weapon = new Weapon(player);
        this.bullet = new Bullet(size, position,player.getLivingState());
        LivingState livingState = LivingState.FACING_RIGHT;
    }

    public void testGetBulletSpeed(){
        assertFalse(400f == bullet.getBulletSpeed());
    }
/*
    public void testMoveBullet(){
        float tmp = bullet.getPosition().getX();
        player.
        bullet.moveBullet(100);
    }
*/
    public void testIsOutOfBounds(){

    }

    public void testAcceptVisitor()  {

    }

}