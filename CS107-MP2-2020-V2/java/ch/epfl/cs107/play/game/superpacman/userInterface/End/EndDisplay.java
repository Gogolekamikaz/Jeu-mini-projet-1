package ch.epfl.cs107.play.game.superpacman.userInterface.End;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class EndDisplay implements Graphics {

    private SuperPacmanArea area;
    private boolean win;
    private int score;

    public EndDisplay(SuperPacmanArea area, boolean win, int score){
        this.area = area;
        this.win = win;
        this.score = score;
    }

    public void textEnd(Canvas canvas){
        float width = canvas.getScaledWidth ();
        float height = canvas.getScaledHeight ();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,height/2));

        String text = win? "You Win!" : "Game Over";

        TextGraphics pauseText = new TextGraphics(text, 2.f, Color.YELLOW);
        pauseText.setOutlineColor(Color.ORANGE);
        pauseText.setThickness(0.05f);
        pauseText.setBold(true);
        pauseText.setDepth(12);
        pauseText.setAnchor(anchor.add(new Vector(win?width/5:width/10, 3*height/5)));
        pauseText.draw(canvas);

        TextGraphics score = new TextGraphics("Score : " + this.score, 1.f, Color.WHITE);
        score.setOutlineColor(Color.ORANGE);
        score.setThickness(0.05f);
        score.setBold(true);
        score.setDepth(12);
        score.setAnchor(anchor.add(new Vector(width/3, height/2)));
        score.draw(canvas);

        TextGraphics instructions = new TextGraphics("Press ENTER to go to Menu", 0.8f, Color.WHITE);
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

    @Override
    public void draw(Canvas canvas) {
        if(area.isPaused()){
            background(canvas);
            textEnd(canvas);
        }
    }

}
