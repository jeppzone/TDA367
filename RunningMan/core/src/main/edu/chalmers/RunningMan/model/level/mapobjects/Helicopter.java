package edu.chalmers.RunningMan.model.level.mapobjects;

import edu.chalmers.RunningMan.model.AbstractPhysicalObject;
import edu.chalmers.RunningMan.model.IVisitor;
import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * A class to represent the clear level object helicopter.
 */
public class Helicopter extends AbstractPhysicalObject {

    private final PropertyChangeSupport propertyChangeSupport;

    private final static float X_VELOCITY = 200;

    private boolean shouldFlyAway;
    private float chopperXPosition;

    public Helicopter(Position position, Size size){
        super(size, position);
        propertyChangeSupport = new PropertyChangeSupport(this);
    }


    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);

        //if a player visits the helicopter shouldFlyAway will be set to true
        shouldFlyAway = true;
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
        chopperXPosition = getPosition().getX();

        //sets a new x coordinate for the helicopter
        setX(chopperXPosition + X_VELOCITY*delta);
        propertyChangeSupport.firePropertyChange("helicopter", null, null);
    }

    public boolean shouldFlyAway(){
        return shouldFlyAway;
    }
}
