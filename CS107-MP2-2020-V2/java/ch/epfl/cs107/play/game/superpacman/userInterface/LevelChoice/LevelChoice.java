package ch.epfl.cs107.play.game.superpacman.userInterface.LevelChoice;

import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.userInterface.GraphicButton;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanAreaGUIEntity;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIWindow;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;


public class LevelChoice extends SuperPacmanGUIWindow {

    public LevelChoice(SuperPacman game){ super(game); }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            return true;
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "superpacman/LevelChoice";
    }

    @Override
    protected void createArea() {
        SuperPacmanAreaGUIEntity title = new SuperPacmanAreaGUIEntity(this, new LevelChoiceDisplay());
        registerActor(title);
        GraphicButton level0 = new GraphicButton("Niveau 1", this, new DiscreteCoordinates(6, 11), "superpacman/Level0", new Vector(1.5f, 0.8f));
        registerActor(level0);
        GraphicButton level1 = new GraphicButton("Niveau 2", this, new DiscreteCoordinates(6, 8), "superpacman/Level1", new Vector(1.5f, 0.8f));
        registerActor(level1);
        GraphicButton level2 = new GraphicButton("Niveau 3", this, new DiscreteCoordinates(6, 5), "superpacman/Level2", new Vector(1.5f, 0.8f));
        registerActor(level2);
    }
}
