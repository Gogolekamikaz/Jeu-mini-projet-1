package ch.epfl.cs107.play.game.superpacman.userInterface;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.superpacman.userInterface.Home.Home;
import ch.epfl.cs107.play.game.superpacman.userInterface.LevelChoice.LevelChoice;
import ch.epfl.cs107.play.game.superpacman.userInterface.Menu.Menu;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanGUI extends AreaGame {

    private Area area;

    @Override
    public String getTitle() {
        return "Super Pac-Man";
    }

    private void createAreas(){
        addArea(new Home());
        addArea(new Menu());
        addArea(new LevelChoice());
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            area = setCurrentArea("superpacman/Home", true);
            return true;
        }
        return false;
    }

    @Override
    public void end() {
        super.end();
    }

}
