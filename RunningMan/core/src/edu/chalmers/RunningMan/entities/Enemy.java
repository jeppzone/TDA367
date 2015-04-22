package edu.chalmers.RunningMan.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by Jesper on 4/22/2015.
 */
public class Enemy extends AbstractLivingObject {

    private PropertyChangeSupport pcs;
    private float velocity;

    public Enemy(Position position, Size size){
        super(size, position, 10);
        pcs = new PropertyChangeSupport(this);
        velocity = 0.25f;
    }

    public void move(int delta){
        setNewX(delta, velocity);
        if(getPosition().getX() == 0){
            changeDirection();
        }
    }

    public void changeDirection(){
        this.velocity = -1 * this.velocity;
    }

    public float getVelocity(){
        return this.velocity;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcs) {
        this.pcs.addPropertyChangeListener(pcs);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pcs) {
        this.pcs.addPropertyChangeListener(pcs);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {

    }
}
