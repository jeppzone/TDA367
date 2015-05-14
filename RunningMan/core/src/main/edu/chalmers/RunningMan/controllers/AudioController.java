package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.utils.AudioHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by JohanTobin on 2015-05-06.
 */
public class AudioController implements PropertyChangeListener {

    public AudioHandler audio;

    public AudioController(){
        audio = new AudioHandler();
    }

    public void playMusic(){
        audio.playMusic();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String propertyName = evt.getPropertyName();
        if("enemyshotinfront".equals(propertyName)){
            audio.playEnemyShotInFrontSound();
        }
        if("enemyshotinback".equals(propertyName)){
            audio.playEnemyShotInBackSound();
        }
    }
}
