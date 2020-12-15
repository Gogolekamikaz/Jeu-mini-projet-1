package ch.epfl.cs107.play.game.superpacman.userInterface.Home;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.userInterface.GraphicButton;
import ch.epfl.cs107.play.game.superpacman.userInterface.Pause.PauseScreen;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanAreaGUIEntity;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIBehavior;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIWindow;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Home extends SuperPacmanGUIWindow {

    public Home(SuperPacman game){ super(game); }

    @Override
    public String getTitle() {
        return "superpacman/Home";
    }

    @Override
    protected void createArea() {
        SuperPacmanAreaGUIEntity display = new SuperPacmanAreaGUIEntity(this, new HomeDisplay());
        registerActor(display);
        GraphicButton button = new GraphicButton("Play", this, new DiscreteCoordinates(6, 6), "superpacman/Menu", new Vector(2.9f, 0.8f));
        registerActor(button);
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            setBehavior(new SuperPacmanGUIBehavior(window));
            createArea();
            return true;
        }
        return false;
    }

    @Override
    protected void createDisplay() {

    }
}
