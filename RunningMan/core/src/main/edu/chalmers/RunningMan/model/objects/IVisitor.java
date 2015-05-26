package edu.chalmers.RunningMan.model.objects;

import edu.chalmers.RunningMan.model.IPhysicalObject;

/**
 * Interface for entities that are able to move
 * @author Jesper Olsson
 */
public interface IVisitor extends IPhysicalObject {
    void visit(Enemy e);
    void visit(Player p);
    void visit(Obstacle g);
    void visit(Bullet b);
    void visit(Steroid s);
    void visit(Ground g);
    void visit(Helicopter f);
    void visit(Pit pit);
}
