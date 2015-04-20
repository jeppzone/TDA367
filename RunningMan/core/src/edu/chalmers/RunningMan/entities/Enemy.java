package edu.chalmers.RunningMan.entities;

/**
 * Created by Armand on 2015-04-20.
 */
public class Enemy implements ILivingEntity {

    private int maxHp;
    private int hp;
    private boolean isDead;
    private int speed;
    private boolean isFlinching;
    private long flinchTime;


    public Enemy(int hp ){
        setHp(hp);
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHp() {
        return this.maxHp;
    }
    @Override
    public void setHp(int newHp) {
        if (newHp <= 0) {
            hp = 0;
            isDead = true;
        } else {
            hp = newHp;
        }
    }

    @Override
    public void takeDamage(int hpDecreased) {
        hp = hp - hpDecreased;
        setHp(hp);

    }




}
