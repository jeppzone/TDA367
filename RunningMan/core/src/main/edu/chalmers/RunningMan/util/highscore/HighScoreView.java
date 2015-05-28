package edu.chalmers.RunningMan.util.highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chalmers.RunningMan.util.BitmapFontManager;
import edu.chalmers.RunningMan.util.MenuButtonGenerator;

import java.util.List;

/**
 * @author Jesper Olsson
 */
public class HighScoreView extends Stage {

    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton restartButton;
    private TextButton mainMenuButton;
    private BitmapFont whiteFont;
    private BitmapFontManager bitmapFontManager = new BitmapFontManager();
    private MenuButtonGenerator menuButtonGenerator;
    private static final String ATLAS_PATH = "mainmenu/mainmenu_buttonsheet.txt";
    private List<Integer> scores;
    private HighScore highScore;
    private Batch batch;

    public HighScoreView(HighScore highScore) {
        this.highScore = highScore;
        scores = highScore.getHighScores();
        batch = getBatch();
        atlas = new TextureAtlas(ATLAS_PATH);
        skin = new Skin(atlas);

        createFont();
        createButtons();
        createTable();

    }

    private void createFont() {
        whiteFont = bitmapFontManager.createNewMenuButtonFontWhite();
        whiteFont.setColor(Color.WHITE);
    }
    private void createButtons() {
        menuButtonGenerator = new MenuButtonGenerator("", whiteFont);
        restartButton = menuButtonGenerator.createNewTextButton();
        restartButton.setText("RESTART");
        mainMenuButton = menuButtonGenerator.createNewTextButton();
        mainMenuButton.setText("MAIN MENU");
    }

    private void createTable(){
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(restartButton);
        table.align(Align.bottom);
        table.getCell(restartButton).spaceRight(50);
        table.add(mainMenuButton);
        table.getCell(mainMenuButton).spaceLeft(50);
        this.addActor(table);
    }

    private void drawScores(){
        batch.begin();
        int counter = 1;
        whiteFont.draw(batch, "Highscore for " + highScore.getLevelName(), Gdx.graphics.getWidth() / 3.7f, Gdx.graphics.getHeight() - 40);
        for(Integer score: scores){
            whiteFont.draw(batch, counter +")  "+score.toString() , Gdx.graphics.getWidth() / 2.5f, Gdx.graphics.getHeight() - 90 - counter*60);
            counter++;
        }
        batch.end();
    }

    public void draw(float deltaTime) {
        this.act(deltaTime);
        this.draw();
        drawScores();
    }

    public void addListener(ClickListener clickListener){
        restartButton.addListener(clickListener);
        mainMenuButton.addListener(clickListener);
    }

    public String getLevelName(){
        return highScore.getLevelName();
    }
}
