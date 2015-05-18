package edu.chalmers.RunningMan.utils;

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
    private Sound enemyShotInFront;
    private Sound enemyShotInBack;
    private Sound steroid;
    private Sound startLevel;
    private Sound helicopter;
    private Music failedMusic;
    private Music levelMusic;

    public AudioHandler() {
        loadAudio();
    }

    private void loadAudio() {
        try {
            jump = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "jump.wav"));
            shoot = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "shoot.mp3"));
            die = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "die.mp3"));
            enemyShotInFront = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "enemyshotinfront.mp3"));
            enemyShotInBack = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "enemyshotinback.mp3"));
            steroid = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "steroid.mp3"));
            startLevel = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "startLevel.mp3"));
            helicopter = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "helicopter.mp3"));
            failedMusic = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "failed.mp3"));
            levelMusic = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "music.mp3"));
        }catch (Exception e){

        }
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

    public void playEnemyShotInFrontSound() {
        enemyShotInFront.play();
    }

    public void playEnemyShotInBackSound(){
        enemyShotInBack.play();
    }

    public void  playSteroidPickUpSound (){
        steroid.play();
    }

    public void playStartLevelSound() {
        startLevel.play();
    }

    public void playHelicopterSound(){
        helicopter.play();
    }

    public void playFailedMusic(){
        failedMusic.play();
    }

    public void playMusic(){
        levelMusic.setVolume(0.5f);
        levelMusic.play();
    }

    public void stopMusic(){
        levelMusic.stop();
    }
}
