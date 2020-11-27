package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area  {

    private SuperPacmanBehavior behavior;

    protected abstract void createArea();

    protected final static float cameraScaleFactor = 15.f;

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            // Set the behavior map
            behavior = new SuperPacmanBehavior(window, getTitle());
            behavior.registerActors(this);
            setBehavior(behavior);
            createArea();
            return true;
        }
        return false;
    }

}
