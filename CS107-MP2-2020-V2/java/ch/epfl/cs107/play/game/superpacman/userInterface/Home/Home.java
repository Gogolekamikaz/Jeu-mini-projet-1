package ch.epfl.cs107.play.game.superpacman.userInterface.Home;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.superpacman.userInterface.GraphicButton;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIBehavior;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIWindow;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Window;

public class Home extends SuperPacmanGUIWindow {

    private Background background;
    private GraphicButton playButton;

    @Override
    public String getTitle() {
        return "superpacman/Home";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            setBehavior(new SuperPacmanGUIBehavior(window, "Accueil"));
            return true;
        }
        return false;
    }

    @Override
    protected void createDisplay() {
        //background = new Background("Accueil", getWindowWidth(), getWindowHeight(), new RegionOfInterest(0,0,2400,2400));
        //registerActor(background);

        Graphics homePage = new HomeDisplay();

        //playButton = new GraphicButton("play",this, new DiscreteCoordinates(1,1), "Play");
        //registerActor(playButton);

    }
}
