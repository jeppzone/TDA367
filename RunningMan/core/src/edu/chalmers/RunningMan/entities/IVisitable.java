package edu.chalmers.RunningMan.entities;

/**
 * @author Jesper Olsson
 */
public interface IVisitable {
    public void acceptVisitor(IVisitor visitor);
}
