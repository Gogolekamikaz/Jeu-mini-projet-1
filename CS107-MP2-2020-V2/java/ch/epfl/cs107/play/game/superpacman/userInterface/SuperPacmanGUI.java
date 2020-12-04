package ch.epfl.cs107.play.game.superpacman.userInterface;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanGUI extends Area {

    protected final static float cameraScaleFactor = 15.f;
    private int width, height;

    protected abstract void createDisplay();

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            this.width = window.getWidth();
            this.height = window.getHeight();
            createDisplay();
            return true;
        }
        return false;
    }

    @Override
    public float getCameraScaleFactor() {
        return cameraScaleFactor;
    }

    protected int getWindowWidth(){ return width; }

    protected int getWindowHeight(){ return height; }
}
