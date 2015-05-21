package edu.chalmers.RunningMan.model;

import java.io.*;
import java.util.*;

/**
 * @author Jesper Olsson
 */
public class HighScore {

    private List<Integer> highScores;
    private String levelName;
    private Scanner scanner;
    private boolean isFull;
    /**
     * Constant for the amount of scores on the high score list
     */
    private static final int MAX_HIGHSCORES = 5;

    public HighScore(String levelName){
        highScores = new ArrayList<>();
        isFull = false;
        this.levelName = levelName;
    }

    /**
     * Method to check if the given score makes the high score list
     * @param score the score to check
     * @return true if the amount of highscores are less than 5
     * or the score is larger than one of the current scores on the list
     */
    public boolean isHighScore(int score){
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
     * @param score the score to add
     */
    public void addScore(int score){
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
            final Iterator<Integer> iterator = getHighScores().iterator();
            while(iterator.hasNext()){
                writer.write(iterator.next()+" ");
            }
            final Iterator<Integer> it = getHighScores().iterator();
            while(it.hasNext()){
                System.out.println("Saved score: " + it.next());
            }
        } catch (IOException ex) {
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
    }

    /**
     * Method to read high scores for the given level from text file
     */
    public void loadFromFile() {
        try{
            scanner =  new Scanner(new FileReader("HS"+levelName+".txt"));
            while(scanner.hasNext()){
                highScores.add(new Integer(scanner.nextInt()));
            }
        }catch(FileNotFoundException exception){

        }finally{
            if(scanner != null){
                scanner.close();
            }
            Iterator<Integer> it = highScores.iterator();
            while(it.hasNext())
                System.out.println("Added score: " + it.next());
        }
    }

}