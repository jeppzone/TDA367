package edu.chalmers.RunningMan.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by JohanTobin on 2015-05-06.
 */
public class AudioHandler {
    private final static String AUDIO_LOCATION = "core/assets/audio/";

    private Sound jump;
    private Sound shoot;
    private Sound die;
    private Sound killed;
    private Sound steroid;
    private Sound startLevel;
    private Music levelMusic;

    public AudioHandler() {
        loadAudio();
    }

    private void loadAudio() {
        jump = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "jump.wav"));
        shoot = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "shoot.wav"));
        die = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "die.wav"));
        killed = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "killed.wav"));
        steroid = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "steroid.wav"));
        startLevel = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "startLevel.mp3"));
        levelMusic = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "music.mp3"));
    }

    public void playJumpSound() {
        jump.play();
    }

    public void playShootSound() {
        shoot.play();
    }

    public void playDieSound() {
        die.play();
    }

    public void playKilledSound() {
        die.play();
    }

    public void  playSteroid (){
        steroid.play();
    }

    public void playStartLevelSound() {
        startLevel.play();
    }

    public void playMusic(){
        levelMusic.play();
    }
}
