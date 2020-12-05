package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;

public class SuperPacman extends RPG {

    private SuperPacmanPlayer player;
    private SuperPacmanArea area;

    private ArrayList<Ghost> ghostActors;

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

        ghostActors = area.getAreaGhostActors();

        if(player.isInvincible() && SuperPacmanBehavior.ghostActorsExist(ghostActors)){
            for(Ghost ghost : ghostActors){
                ghost.setAfraid(player);
            }
        }
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            area = (SuperPacmanArea)setCurrentArea("superpacman/Level0", true);
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
