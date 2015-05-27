package edu.chalmers.RunningMan.model.gameobjects;

/**
 * Interface for all entities that can be collided with
 * @author Jesper Olsson
 */
public interface IVisitable {
    void acceptVisitor(IVisitor visitor);
}
