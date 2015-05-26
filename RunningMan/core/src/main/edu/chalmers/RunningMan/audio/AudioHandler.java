package edu.chalmers.RunningMan.audio;

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
    private Sound bossShotInFront;
    private Sound bossShotInBack;
    private Sound bossIsHitByBullet;
    private Sound steroid;
    private Sound startLevel;
    private Sound helicopter;
    private Music failedMusic;
    private Music level1Music;
    private Music level2Music;
    private Music successMusic;

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
            bossShotInFront = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "bossshotinfront.wav"));
            bossShotInBack = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "bossshotinback.wav"));
            bossIsHitByBullet = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "bossishitbybullet.wav"));
            steroid = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "steroid.mp3"));
            startLevel = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "startLevel.mp3"));
            helicopter = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "helicopter.mp3"));
            failedMusic = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "failed.wav"));
            successMusic = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "success.mp3"));
            level1Music = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "music.mp3"));
            level2Music = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "level2music.mp3"));
        }catch (Exception e){
            throw new NullPointerException("Missing soundfile in AudioHandler");
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

    public void playBossShotInBackSound(){
        bossShotInBack.play();
    }

    public void playBossShotInFrontSound(){
        bossShotInFront.play();
    }

    public void playBossIsHitByBulletSound(){
        bossIsHitByBullet.play();
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

    public void playSuccessMusic(){
        successMusic.play();
    }

    public void playMusic(String levelName){
        if(levelName.equals("level1")) {
            level1Music.play();
            level1Music.setVolume(0.5f);
        }else if(levelName.equals("level2")){
            level2Music.play();
            level2Music.setVolume(0.5f);
        }
    }

    public void stopMusic(String levelName){
        if(levelName.equals("level1")) {
            level1Music.stop();
        }else if(levelName.equals("level2")){
            level2Music.stop();
        }
    }
}
