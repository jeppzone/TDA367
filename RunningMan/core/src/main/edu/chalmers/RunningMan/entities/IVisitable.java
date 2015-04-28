package edu.chalmers.RunningMan.entities;

/**
 * @author Jesper Olsson
 */
public interface IVisitable {
    void acceptVisitor(IVisitor visitor);
}
