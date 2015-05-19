package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.utils.AudioHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by JohanTobin on 2015-05-06.
 */
public class AudioController implements PropertyChangeListener {

    private AudioHandler audio;
    private boolean hasPlayedHelicopter = false;
    private boolean hasPlayedDie = false;
    private boolean hasPlayedSuicide = false;

    public AudioController(){
        audio = new AudioHandler();
    }

    public void playIntroMusic(){
        audio.playIntroMusic();
    }

    public void stopIntroMusic(){
        audio.stopMusic();
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
        if("jump".equals(propertyName)){
            audio.playJumpSound();
        }
        if("suicide".equals(propertyName) && !hasPlayedSuicide){
            hasPlayedSuicide = true;
            audio.playFailedMusic();
            audio.playShootSound();
        }
        if("die".equals(propertyName) && !hasPlayedDie){
            hasPlayedDie = true;
            audio.playFailedMusic();
            audio.playDieSound();
        }
        if("pickupsteroid".equals(propertyName)){
            audio.playSteroidPickUpSound();
        }
        if("shoot".equals(propertyName)){
            audio.playShootSound();
        }
        if("helicopter".equals(propertyName) && !hasPlayedHelicopter){
            hasPlayedHelicopter = true;
            audio.playHelicopterSound();
            audio.playSuccessMusic();
        }
        if("startlevel".equals(propertyName)){
            audio.playStartLevelSound();
        }
        if("time".equals(propertyName)){
            audio.playFailedMusic();
            audio.stopMusic();
        }
    }
}
