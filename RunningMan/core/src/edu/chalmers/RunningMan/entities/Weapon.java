package edu.chalmers.RunningMan.entities;

/**
 * kommer behöva göra en abstrakt klass sen pga att följa designprinciper
 * Weapon model
 */
public class Weapon {

    private Bullet bullet;
    boolean IsFired;
    double fireRate;

    public enum MovingDirection{
        RIGHT,
        LEFT;
    }

    public enum FacingDirection{
        RIGHT,
        LEFT;
    }








}
