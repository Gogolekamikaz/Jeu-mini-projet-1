package ch.epfl.cs107.play.game.superpacman.userInterface.Home;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class HomeDisplay implements Graphics {

    private ImageGraphics background(Canvas canvas){
        float width = canvas.getScaledWidth ();
        float height = canvas.getScaledHeight ();
        //Vector anchor = canvas.getTransform().getOrigin().add(new Vector(width/2,height/2));
        Vector anchor = canvas.getTransform().getOrigin();

        return new ImageGraphics(ResourcePath.getSprite("superpacman/Home"), 1.f, 1.f, new RegionOfInterest(0, 0, 2400, 2400) , anchor.add(new Vector(1.f, - 1.7f)), 1, 10);
    }

    @Override
    public void draw(Canvas canvas) {
        background(canvas).draw(canvas);
    }
}
