package ch.epfl.cs107.play.game.superpacman.userInterface.LevelChoice;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class LevelChoiceDisplay implements Graphics {

    public void draw(Canvas canvas) {
        float width = canvas.getScaledWidth ();
        float height = canvas.getScaledHeight ();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,height/2));

        ShapeGraphics background = new ShapeGraphics(new Polygon(anchor.x,anchor.y, anchor.x,anchor.y+height,anchor.x+width,anchor.y+height,anchor.x+width,anchor.y), Color.WHITE, Color.GRAY,0,1f, 11);
        background.draw(canvas);

        TextGraphics text = new TextGraphics("Level Choice", 2.f, Color.YELLOW);
        text.setOutlineColor(Color.ORANGE);
        text.setThickness(0.05f);
        text.setBold(true);
        text.setDepth(12);
        text.setAnchor(anchor.add(new Vector(width/10, 4*height/5)));
        text.draw(canvas);
    }
}
