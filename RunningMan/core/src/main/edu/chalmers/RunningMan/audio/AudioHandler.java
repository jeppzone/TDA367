package edu.chalmers.RunningMan.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * A class which handles the music
 */
public class AudioHandler {
    private final static String AUDIO_LOCATION = "audio/";

    private Sound jumpSound;
    private Sound shootSound;
    private Sound dieSound;
    private Sound enemyShotInFrontSound;
    private Sound enemyShotInBackSound;
    private Sound bossShotInFrontSound;
    private Sound bossShotInBackSound;
    private Sound bossIsHitByBulletSound;
    private Sound steroidSound;
    private Sound startLevelSound;
    private Sound helicopterSound;
    private Music failedMusic;
    private Music level1Music;
    private Music level2Music;
    private Music successMusic;

    public AudioHandler() {
        loadAudio();
    }

    /*
    *   Loads specific audio
     */
    private void loadAudio() {
        try {
            jumpSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "jump.wav"));
            shootSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "shoot.mp3"));
            dieSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "die.mp3"));
            enemyShotInFrontSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "enemyshotinfront.mp3"));
            enemyShotInBackSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "enemyshotinback.mp3"));
            bossShotInFrontSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "bossshotinfront.wav"));
            bossShotInBackSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "bossshotinback.wav"));
            bossIsHitByBulletSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "bossishitbybullet.wav"));
            steroidSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "steroid.mp3"));
            startLevelSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "startlevel.mp3"));
            helicopterSound = Gdx.audio.newSound(Gdx.files.internal(AUDIO_LOCATION + "helicopter.mp3"));
            failedMusic = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "failed.wav"));
            successMusic = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "success.wav"));
            level1Music = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "music.mp3"));
            level2Music = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_LOCATION + "level2music.mp3"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playJumpSound() {
        jumpSound.play();
    }

    public void playShootSound() {
        shootSound.play();
    }

    public void playDieSound() {
        dieSound.play();
    }

    public void playEnemyShotInFrontSound() {
        enemyShotInFrontSound.play();
    }

    public void playEnemyShotInBackSound(){
        enemyShotInBackSound.play();
    }

    public void playBossShotInBackSound(){
        bossShotInBackSound.play();
    }

    public void playBossShotInFrontSound(){
        bossShotInFrontSound.play();
    }

    public void playBossIsHitByBulletSound(){
        bossIsHitByBulletSound.play();
    }

    public void  playSteroidPickUpSound (){
        steroidSound.play();
    }

    public void playStartLevelSound() {
        startLevelSound.play();
    }

    public void playHelicopterSound(){
        helicopterSound.play();
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
