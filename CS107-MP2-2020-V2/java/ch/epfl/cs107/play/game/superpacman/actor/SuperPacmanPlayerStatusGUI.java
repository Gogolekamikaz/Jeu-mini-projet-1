package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class SuperPacmanPlayerStatusGUI implements Graphics {

    private int hp;
    private final int MAX_HEALTH = 5;
    private ImageGraphics[] health = new ImageGraphics[MAX_HEALTH];
    private int score;
    private TextGraphics scoreText;

    private SuperPacmanPlayer player;

    public SuperPacmanPlayerStatusGUI(Area area, SuperPacmanPlayer player){
        this.player = player;
        hp = 3;
        score = 0;
    }

    private void manageHealth(Canvas canvas){

        float width = canvas.getScaledWidth ();
        float height = canvas.getScaledHeight ();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,height/2));

        for (int i = 0; i < MAX_HEALTH; i++) {
            if(i<hp){
                health[i] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(0, 0, 64, 64) , anchor.add((float)(1), canvas.getHeight()-1.375f), 1, 10);
            } else {
                health[i] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(64, 0, 64, 64) , anchor.add((float)(1), canvas.getHeight()-1.375f), 1, 10);
            }
            health[i].draw(canvas);
        }
    }

    private void manageScore(Canvas canvas){
        scoreText = new TextGraphics("Score : "+score, 1.f, Color.YELLOW);
        scoreText.setParent(player);
        scoreText.setAnchor(new Vector(canvas.getWidth()-9, canvas.getHeight()-4));
        scoreText.draw(canvas);
    }


    @Override
    public void draw(Canvas canvas) {
        manageHealth(canvas);
        manageScore(canvas);
    }
}
