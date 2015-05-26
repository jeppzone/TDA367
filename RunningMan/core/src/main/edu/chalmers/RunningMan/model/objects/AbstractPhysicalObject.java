package edu.chalmers.RunningMan.model.objects;

import edu.chalmers.RunningMan.model.IPhysicalObject;
import edu.chalmers.RunningMan.model.ISize;
import edu.chalmers.RunningMan.model.Position;

import java.awt.*;

/**
 * Abstract class for physical objects
 * @author Jesper Olsson
 */
public abstract class AbstractPhysicalObject implements IVisitable, IPhysicalObject {

    private ISize size;
    private Position position;

    public AbstractPhysicalObject(ISize size, Position position){
        this.size = size;
        this.position = position;
    }

    public ISize getSize(){
        return this.size;
    }

    public Position getPosition(){
        return this.position;
    }

    public void setX(float x){
        this.position.setX(x);
    }

    public void setY(float y){
        this.position.setY(y);
    }

    /**
     * A Method to fetch the hitbox of a physical object
     * @return a rectangle that represents the area of an object
     * that may collide with other objects
     */
    public Rectangle getHitbox(){
        return new Rectangle(Math.round(position.getX()), Math.round(position.getY()),
                Math.round(size.getWidth()), Math.round(size.getHeight()));
    }

    public void setPosition(Position position){
        if(position == null){
            throw new NullPointerException("AbstractPhysicalObject: setPosition: parameter position null");
        }else{
            this.position.setX(position.getX());
            this.position.setY(position.getY());
        }
    }
}
