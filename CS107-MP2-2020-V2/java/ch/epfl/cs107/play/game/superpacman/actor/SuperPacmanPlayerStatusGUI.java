package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.Color;


class SuperPacmanPlayerStatusGUI implements Graphics {

    private final int MAX_HEALTH = 5;

    private final SuperPacmanPlayer player;

    SuperPacmanPlayerStatusGUI(SuperPacmanPlayer player){
        this.player = player;
    }

    float width;
    float height;
    Vector anchor;
    ImageGraphics health;
    String lifeDisplayString = "superpacman/lifeDisplay";

    public void changeLifeDisplayString(){
        lifeDisplayString = "superpacman/lifeDisplayRed";
    }

   private void manageHealth(Canvas canvas){

        width = canvas.getScaledWidth ();
        height = canvas.getScaledHeight ();
        anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,-height/2));

        for (int i = 0; i < MAX_HEALTH; i++) {
            if(i<player.getHealth()){
                health = new ImageGraphics(ResourcePath.getSprite(lifeDisplayString), 1.f, 1.f, new RegionOfInterest(0, 0, 64, 64) , anchor.add(new Vector(1.f+i, - 1.7f)), 1, 10);
            } else {
                health = new ImageGraphics(ResourcePath.getSprite(lifeDisplayString), 1.f, 1.f, new RegionOfInterest(64, 0, 64, 64) , anchor.add(new Vector(1.f+i, - 1.7f)), 1, 10);
            }
            health.draw(canvas);
        }
    }

    private void manageScore(Canvas canvas){
        float width = canvas.getScaledWidth();
        float height = canvas.getScaledHeight();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,-height/2));

        TextGraphics scoreText = new TextGraphics("Score : "+player.getScore(), 1.f, Color.YELLOW);
        scoreText.setOutlineColor(Color.ORANGE);
        scoreText.setThickness(0.05f);
        scoreText.setBold(true);
        scoreText.setAnchor(anchor.add(new Vector(width/2, - 1.375f)));
        scoreText.draw(canvas);
    }


    @Override
    public void draw(Canvas canvas) {
        manageScore(canvas);
        manageHealth(canvas);
    }

}
