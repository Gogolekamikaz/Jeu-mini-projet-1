package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class SuperPacmanPlayerStatusGUI implements Graphics {

    private int hp;
    private ImageGraphics[] health = new ImageGraphics[5];
    private int score;
    private TextGraphics scoreText;

    private SuperPacmanPlayer player;

    public SuperPacmanPlayerStatusGUI(Area area, SuperPacmanPlayer player){
        this.player = player;
        initHealth(area);
        initScore(area);
    }

    private void initHealth(Area area){
        hp = 3;
        for (int i = 0; i < health.length; i++) {
            if(i<hp){
                health[i] = new ImageGraphics("superpacman/lifeDisplay", 128, 64, new RegionOfInterest(0,0, 64,64));
            } else {
                health[i] = new ImageGraphics("superpacman/lifeDisplay", 128, 64, new RegionOfInterest(64,0, 64,64));
            }
            health[i].setHeight(1.f);
            health[i].setWidth(1.f);
            health[i].setParent(player);
            health[i].setAnchor(new Vector(9, 4));
        }
    }

    private void initScore(Area area){
        score = 0;
        scoreText = new TextGraphics("Score : "+score, 1.f, Color.YELLOW);
        scoreText.setParent(player);
        scoreText.setAnchor(new Vector(area.getWidth()-9, area.getHeight()-4));
    }


    @Override
    public void draw(Canvas canvas) {
        scoreText.draw(canvas);
        for (ImageGraphics image : health) {
            image.draw(canvas);
        }
    }
}
