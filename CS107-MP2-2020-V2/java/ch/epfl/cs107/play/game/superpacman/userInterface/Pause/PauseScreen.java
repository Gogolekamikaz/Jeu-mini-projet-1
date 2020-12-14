package ch.epfl.cs107.play.game.superpacman.userInterface.Pause;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class PauseScreen implements Graphics {

    private SuperPacmanArea area;

    public PauseScreen(SuperPacmanArea area){ this.area = area; }

    public void textPause(Canvas canvas){
        float width = canvas.getScaledWidth ();
        float height = canvas.getScaledHeight ();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,height/2));

        TextGraphics scoreText = new TextGraphics("PAUSE", 3.f, Color.YELLOW);
        scoreText.setOutlineColor(Color.ORANGE);
        scoreText.setThickness(0.05f);
        scoreText.setBold(true);
        scoreText.setAnchor(anchor.add(new Vector(width/2, - 1.375f)));
        scoreText.draw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        if(area.isPaused()){
            textPause(canvas);
        }
    }
}
