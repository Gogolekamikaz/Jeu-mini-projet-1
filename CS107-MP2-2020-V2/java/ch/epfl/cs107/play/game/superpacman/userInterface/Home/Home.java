package ch.epfl.cs107.play.game.superpacman.userInterface.Home;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.superpacman.userInterface.GraphicButton;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanAreaGUIEntity;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIBehavior;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIWindow;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Window;

public class Home extends SuperPacmanGUIWindow {

    private SuperPacmanAreaGUIEntity display;
    private GraphicButton playButton;

    @Override
    public String getTitle() {
        return "superpacman/Home";
    }

    @Override
    protected void createArea() {

    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            setBehavior(new SuperPacmanGUIBehavior(window));
            return true;
        }
        return false;
    }

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return new DiscreteCoordinates(0,0);
    }

    @Override
    protected void createDisplay() {
        display = new SuperPacmanAreaGUIEntity(this, new HomeDisplay());
        registerActor(display);


        //playButton = new GraphicButton("play",this, new DiscreteCoordinates(1,1), "Play");
        //registerActor(playButton);

    }
}
