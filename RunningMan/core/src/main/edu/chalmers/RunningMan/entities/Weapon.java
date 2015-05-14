package edu.chalmers.RunningMan.entities;

/**
 * kommer behöva göra en abstrakt klass sen pga att följa designprinciper
 * Weapon model
 */

public class Weapon{

    private final IBulletCollection bulletCollection;
    private final float FIRE_DELAY = 500f;

    public Weapon(IBulletCollection bulletCollection){
        this.bulletCollection = bulletCollection;
    }

    public void shoot(){
        bulletCollection.placeBullet();
    }
    public float getfireDelay(){
        return FIRE_DELAY;
    }


}
