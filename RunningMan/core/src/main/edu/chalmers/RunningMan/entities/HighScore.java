package edu.chalmers.RunningMan.entities;

import java.io.*;
import java.util.*;

/**
 * Created by Jesper on 5/17/2015.
 */
public class HighScore {

    private List<Integer> highScores;
    private Map<String, List<Integer>> highScoreMap;
    private String levelName;
    private Scanner scanner;
    private static final int MAX_HIGHSCORES = 5;

    public HighScore(String levelName){
        highScores = new ArrayList<>();
        highScoreMap = new HashMap<>();
        this.levelName = levelName;
    }

    public boolean isHighScore(int score){
        final Iterator iterator = highScores.iterator();
        if (highScores.size() < MAX_HIGHSCORES){
            return true;
        }
        while(iterator.hasNext()){
            if(score > (int)iterator.next()){
                return true;
            }
        }
        return false;
    }

    public void addScore(int score){
        if(isHighScore(score)){
            highScores.add(score);
        }
    }

    public List<Integer> getHighScores(){
        return highScores;
    }

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
