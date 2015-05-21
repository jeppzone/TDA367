package edu.chalmers.RunningMan.model.level.mapobjects;

import edu.chalmers.RunningMan.model.IPhysicalObject;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Bullet;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Enemy;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Player;
import edu.chalmers.RunningMan.model.level.mapobjects.powerups.Steroid;

/**
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