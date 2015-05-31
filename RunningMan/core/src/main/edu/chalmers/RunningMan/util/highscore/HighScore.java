package edu.chalmers.RunningMan.util.highscore;

import edu.chalmers.RunningMan.model.gameobject.Level;

import java.io.*;
import java.util.*;

/**
 * A class to represent a list of highscores
 * @author Jesper Olsson
 */
public class HighScore {

    private final List<Integer> highScores;
    private final Level level;
    private final String levelName;
    private Scanner scanner;
    private boolean isFull;
    /**
     * Constant for the amount of scores on the high score list
     */
    private static final int MAX_HIGHSCORES = 5;

    public HighScore(Level level){
        this.level = level;
        this.levelName = level.getLevelName();
        highScores = new ArrayList<>();
        isFull = false;
    }

    /**
     * Method to check if the given score makes the high score list
     * @param score the score to check
     * @return true if the amount of highscores are less than 5
     * or the score is larger than one of the current scores on the list
     */
    private boolean isHighScore(int score){
        final Iterator iterator = highScores.iterator();
        if (highScores.size() < MAX_HIGHSCORES){
            return true;
        }
        isFull = true;
        int counter = 0;
        while(iterator.hasNext() && counter < 5){
            if(score > (int)iterator.next()){
                return true;
            }
            counter ++;
        }
        return false;
    }

    /**
     * Add score to the high score list if it's large enough
     */
    public void addCurrentScore(){
        level.setPlayerScore();
        final int score = level.getPlayerScore();
        if(isHighScore(score)){
            if(isFull) {
                highScores.remove(MAX_HIGHSCORES - 1);
            }
            highScores.add(score);
        }
    }

    public List<Integer> getHighScores(){
        Collections.sort(highScores, Collections.reverseOrder());
        return highScores;
    }

    /**
     * Method to save the current high scores for the given level
     * to a text file, which can be loaded at any time
     */
    public void saveToFile(){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("HS"+levelName+".txt"), "utf-8"));
            for (Integer integer : getHighScores()) {
                writer.write(integer + " ");
            }
        } catch (IOException ex) {
            System.out.println("HighScore: saveToFile()");
        } finally {
            try {writer.close();} catch (Exception ex) {
                System.out.println("HighScore: saveToFile(): writer");
            }
        }
    }

    /**
     * Method to read high scores for the given level from text file
     */
    public void loadFromFile() {
        try{
            scanner =  new Scanner(new FileReader("HS"+"level1"+".txt"));
            while(scanner.hasNext()){
                highScores.add(scanner.nextInt());
            }
        }catch(FileNotFoundException exception){
            System.out.println("HighScore: loadFromFile()");
        }finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public String getLevelName(){
        return levelName;
    }

}
