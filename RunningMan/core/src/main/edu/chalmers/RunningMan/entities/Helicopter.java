package edu.chalmers.RunningMan.entities;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * A class to represent the clear level object helicopter.
 */
public class Helicopter extends AbstractPhysicalObject {

    private final PropertyChangeSupport propertyChangeSupport;

    private boolean flyaway;

    public Helicopter(Position position, Size size){
        super(size, position);
        propertyChangeSupport = new PropertyChangeSupport(this);
    }


    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor. visit(this);
        flyaway = true;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Makes the helicopter move by updating its position to the right
     * @param  delta the time difference
     */
    public void move(float delta){
        float velocity = 200;
        setX(getPosition().getX() + velocity*delta);
        propertyChangeSupport.firePropertyChange("helicopter", null, null);
    }

    public boolean getFlyaway(){
        return flyaway;
    }
}
