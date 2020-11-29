package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;

public abstract class SuperPacmanArea extends Area implements Logic {

    protected abstract void createArea();

    protected final static float cameraScaleFactor = 15.f;

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            // Set the behavior map
            SuperPacmanBehavior behavior = new SuperPacmanBehavior(window, getTitle());
            setBehavior(behavior);
            behavior.registerActors(this);
            createArea();
            return true;
        }
        return false;
    }

    public final float getCameraScaleFactor(){return cameraScaleFactor;}

    public abstract DiscreteCoordinates getSpawnPoint();

    @Override
    public boolean isOn() {
        if(contains(new Diamond(this, new DiscreteCoordinates(0,0)))){
            return false;
        }
        return true;
    }

    @Override
    public boolean isOff() {
        if(contains(new Diamond(this, new DiscreteCoordinates(0,0)))){
            return true;
        }
        return false;
    }

    @Override
    public float getIntensity() {
        return 0;
    }

}
