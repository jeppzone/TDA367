package edu.chalmers.RunningMan.views;

import edu.chalmers.RunningMan.entities.AbstractLivingObject;

import java.beans.*;

/**
 * Created by Armand on 2015-04-22.
 */
public abstract class AbstractLivingObjectView implements PropertyChangeListener {

    protected AbstractLivingObject alo;

    public AbstractLivingObject getAlo(){
        return alo;
    }

    public void setAlo(AbstractLivingObject a){
        if(a != null)
            a.removePropertyChangeListener(this);
            alo = a;
        if(alo != null) {
            alo.addPropertyChangeListener(this);
        }
    }

    public abstract void propertyChange(PropertyChangeEvent e);
    
}
