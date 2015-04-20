package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 4/20/2015.
 */
public interface ILivingEntity {
    /**
     * @return      the current health of this entity
     */
    public int getHp();

    /**
     * @param hp    the new health of this entity
     */
    public void setHp(int hp);

    /**
     * @return      the maximum health that this entity is allowed
     */
    public int getMaxHp();

    /**
     * @param hpDecreased   the amount of damage that this entity will accumulate
     */
    public void takeDamage(int hpDecreased);
}
