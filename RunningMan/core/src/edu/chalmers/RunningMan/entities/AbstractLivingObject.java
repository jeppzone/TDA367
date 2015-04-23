package edu.chalmers.RunningMan.entities;

import java.beans.PropertyChangeListener;

/**
 * Created by Jesper on 4/22/2015.
 */
public abstract class AbstractLivingObject extends AbstractPhysicalObject implements IVisitor {
    private int hp;
    private int maxHp;
    private boolean isDead;
    public AbstractLivingObject(Size size, Position position, int maxHp) {
        super(size, position);
        this.hp = maxHp;
        isDead = false;
    }

    public void setNewX(float delta, float velocity){
        setX(getPosition().getX() + velocity*delta);
    }

    public int getHp(){
        return this.hp;
    }

    public boolean isDead(){
        return this.isDead;
    }

    public void setHp(int newHp){
        if(newHp <= 0){
            this.hp = 0;
            this.isDead = true;
        }else if(newHp > this.maxHp){
            this.hp = maxHp;
        }else{
            this.hp = newHp;
        }
    }

    public void takeDamage(int damage){
        setHp(this.hp - damage);
    }
}
