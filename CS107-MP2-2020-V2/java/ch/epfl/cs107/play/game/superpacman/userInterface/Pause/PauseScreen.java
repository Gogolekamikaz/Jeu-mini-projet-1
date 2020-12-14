package ch.epfl.cs107.play.game.superpacman.userInterface.Pause;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;


public class PauseScreen implements Graphics {

    private SuperPacmanArea area;

    public PauseScreen(SuperPacmanArea area){
        this.area = area;
    }

    public void textPause(Canvas canvas){
        float width = canvas.getScaledWidth ();
        float height = canvas.getScaledHeight ();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,height/2));

        TextGraphics pauseText = new TextGraphics("PAUSE", 3.f, Color.YELLOW);
        pauseText.setOutlineColor(Color.ORANGE);
        pauseText.setThickness(0.05f);
        pauseText.setBold(true);
        pauseText.setDepth(12);
        pauseText.setAnchor(anchor.add(new Vector(width/5, 3*height/5)));
        pauseText.draw(canvas);

        TextGraphics instructions = new TextGraphics("Press P to resume the game", 0.8f, Color.WHITE);
        instructions.setAnchor(anchor.add(new Vector(width/7, height/3)));
        instructions.setBold(true);
        instructions.setDepth(12);
        instructions.draw(canvas);
    }

    public void background(Canvas canvas) {
        float width = canvas.getScaledWidth();
        float height = canvas.getScaledHeight();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width / 2, height / 2));

        ShapeGraphics background = new ShapeGraphics(new Polygon(anchor.x,anchor.y, anchor.x,anchor.y+height,anchor.x+width,anchor.y+height,anchor.x+width,anchor.y), Color.BLACK, Color.GRAY,0,0.5f, 11);
        background.draw(canvas);
    }


    public void draw(Canvas canvas) {
        if(area.isPaused()){
            background(canvas);
            textPause(canvas);
        }
    }
}
