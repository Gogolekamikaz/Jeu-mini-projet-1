package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.Color;


public class SuperPacmanPlayerStatusGUI implements Graphics {

    private int hp;
    private final int MAX_HEALTH = 5;
    private int score;

    private final SuperPacmanPlayer player;

    public SuperPacmanPlayerStatusGUI(SuperPacmanPlayer player){
        this.player = player;
        hp = 3;
        score = 0;
    }

    private void manageHealth(Canvas canvas){

        float width = canvas.getScaledWidth ();
        float height = canvas.getScaledHeight ();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,height/2));

        for (int i = 0; i < MAX_HEALTH; i++) {
            ImageGraphics health;
            if(i<hp){
                health = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(0, 0, 64, 64) , anchor.add((float)(1), canvas.getHeight()-1.375f), 1, 10);
            } else {
                health = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(64, 0, 64, 64) , anchor.add((float)(1), canvas.getHeight()-1.375f), 1, 10);
            }
            //health.setParent(player);
            health.draw(canvas);
        }
    }

    private void manageScore(Canvas canvas){
        float width = canvas.getScaledWidth();
        float height = canvas.getScaledHeight();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,height/2));

        TextGraphics scoreText = new TextGraphics("Score : "+score, 1.f, Color.YELLOW);
        //scoreText.setParent(player);
        scoreText.setAnchor(anchor.add(canvas.getWidth()-9, canvas.getHeight()-4));
        scoreText.draw(canvas);
    }


    @Override
    public void draw(Canvas canvas) {
        manageHealth(canvas);
        manageScore(canvas);
    }

    public void increaseScore(int scorePoint){
        score += scorePoint;
    }

    public int getScore(){ return score; }

}
