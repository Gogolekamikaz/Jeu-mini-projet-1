package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG {

    public final static float STEP = 0.05f;

    private SuperPacmanPlayer player;

    /**
     * Add all the areas
     */
    private void createAreas(){

        addArea(new Level0());
        addArea(new Level1());
        addArea(new Level2());

    }

    @Override
    public String getTitle() {
        return "Super Pac-Man";
    }
    @Override
    protected void initPlayer(Player player) {
        super.initPlayer(player);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            SuperPacmanArea area = (SuperPacmanArea)setCurrentArea("superpacman/Level2", true);
            player = new SuperPacmanPlayer(area, Orientation.UP, area.getSpawnPoint());
            initPlayer(player);
            return true;
        }
        return false;
    }

    @Override
    public void end() {
        super.end();
    }
}
