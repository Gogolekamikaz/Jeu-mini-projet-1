package ch.epfl.cs107.play.game.superpacman.userInterface;

import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanGUIWindow extends SuperPacmanArea {

    private int width, height;
    private SuperPacman game;

    protected abstract void createDisplay();

    public SuperPacmanGUIWindow(SuperPacman game){ this.game = game; }

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem, new SuperPacmanGUIBehavior(window))) {
            this.width = window.getWidth();
            this.height = window.getHeight();
            createDisplay();
            return true;
        }
        return false;
    }

    protected int getWindowWidth(){ return width; }

    protected int getWindowHeight(){ return height; }

    public void switchArea(String destination){
        game.setNextArea(destination);
    }

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return new DiscreteCoordinates(10,10);
    }
}
