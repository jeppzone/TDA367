package edu.chalmers.RunningMan.entities;


import java.util.ArrayList;
import java.util.List;


public class Weapon implements IBulletCollection{

    private Player player;
    private List<Bullet> bullets;
    private final float FIRE_DELAY = 500f;
    private boolean hasShot = false;
    private static float passedTime = 0;

    public Weapon(Player player){
        this.player = player;
        bullets = new ArrayList<>();

    }

    public float getfireDelay(){
        return FIRE_DELAY;
    }

    public List<Bullet> getBullets(){
        return bullets;
    }

    /**
     * Method to make the player shoot, has a delay of 0.5 seconds
     *
     */
    public void shoot(){
        if(!hasShot){
            placeBullet();
            hasShot =true;
        }
    }

    /**
     * Places a bullet correctly in front of the gun when a bullet is fired
     */
    public void placeBullet(){
        if(player.getFacingDirection().xDirection > 0)
            createBullet(24);
        else
            createBullet(-34);
    }

    /**
     * A helper class for placeBullet(), creates the bullet on the position where the bullet is supposed to be placed
     * @param pos a value that changes depends on the players facing direction
     */
    private void createBullet(int pos){
        System.out.println(bullets);
        bullets.add(new Bullet(new Size(10, 10),
                new Position(player.getPosition().getX() + pos + (player.getSize().getWidth()) / 2, player.getPosition().getY() - 6 + (player.getSize().getHeight()) / 2),
                player.getFacingDirection()));
    }

    public void update(float deltaTime) {
        if(hasShot){
            passedTime += 1000*deltaTime;

            if(passedTime >= getfireDelay()){
                hasShot = false;
                passedTime = 0;
            }

        }
    }
}
