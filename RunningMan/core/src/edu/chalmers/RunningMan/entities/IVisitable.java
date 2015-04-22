package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 4/22/2015.
 */
public interface IVisitable {
    public void acceptVisitor(IVisitor visitor);
}
