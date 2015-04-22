package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import edu.chalmers.RunningMan.entities.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by JohanTobin on 2015-04-20.
 */
public class PlayerView extends AbstractLivingObjectView {

    public PlayerView(Player pm){
        Image img = new Image();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
