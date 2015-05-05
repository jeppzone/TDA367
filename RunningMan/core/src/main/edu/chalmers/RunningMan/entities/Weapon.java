package edu.chalmers.RunningMan.entities;

/**
 * kommer behöva göra en abstrakt klass sen pga att följa designprinciper
 * Weapon model
 */

public class Weapon extends AbstractPhysicalObject {

    private Bullet bullet;
    private final IBulletCollection bulletCollection;
    private final float FIRE_DELAY = 500f;

    public Weapon(Size size, Position position, IBulletCollection bulletCollection){
        super(size, position);
        this.bulletCollection = bulletCollection;
    }

    public void shoot(Position position){
        bulletCollection.createBullet(position);
        //timern startas
    }
    public float getfireDelay(){
        return FIRE_DELAY;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        // This method doesn't do anything

    }

}
