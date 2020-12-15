package ch.epfl.cs107.play.game.superpacman.userInterface.Home;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class HomeDisplay implements Graphics {

    private void background(Canvas canvas){
        float width = canvas.getScaledWidth ();
        float height = canvas.getScaledHeight ();
        Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2,height/2));

        ImageGraphics home = new ImageGraphics(ResourcePath.getSprite("superpacman/Home"), width, height, new RegionOfInterest(0, 0, 2400, 2400) , anchor.add(new Vector(0, 0)), 1, 11, true);
        home.draw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        background(canvas);
    }
}
