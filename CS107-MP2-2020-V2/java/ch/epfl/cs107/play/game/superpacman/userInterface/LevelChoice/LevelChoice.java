package ch.epfl.cs107.play.game.superpacman.userInterface.LevelChoice;

import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIWindow;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class LevelChoice extends SuperPacmanGUIWindow {
    @Override
    protected void createDisplay() {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected void createArea() {

    }

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return new DiscreteCoordinates(0,0);
    }
}
