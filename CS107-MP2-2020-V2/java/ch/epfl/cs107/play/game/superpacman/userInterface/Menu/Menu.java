package ch.epfl.cs107.play.game.superpacman.userInterface.Menu;

import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.userInterface.GraphicButton;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanAreaGUIEntity;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIBehavior;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIWindow;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Menu extends SuperPacmanGUIWindow {

    public Menu(SuperPacman game){ super(game); }

    @Override
    protected void createDisplay() {

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
    public String getTitle() {
        return "superpacman/Menu";
    }

    @Override
    protected void createArea() {
        SuperPacmanAreaGUIEntity title = new SuperPacmanAreaGUIEntity(this, new MenuDisplay());
        registerActor(title);
        GraphicButton arcade = new GraphicButton("Mode Arcade", this, new DiscreteCoordinates(6, 11), "superpacman/Level0", new Vector(0.7f, 0.8f));
        registerActor(arcade);
        GraphicButton infinite = new GraphicButton("Mode Infini", this, new DiscreteCoordinates(6, 8), "superpacman/MazeLevel0", new Vector(0.7f, 0.8f));
        registerActor(infinite);
        GraphicButton choix = new GraphicButton("Choix Niveau", this, new DiscreteCoordinates(6, 5), "superpacman/LevelChoice", new Vector(0.7f, 0.8f));
        registerActor(choix);
    }

}
