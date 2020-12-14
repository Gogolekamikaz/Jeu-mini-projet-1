package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;

public class SuperPacman extends RPG {

    private SuperPacmanPlayer player;
    private SuperPacmanArea area;
    private SuperPacmanArea currentPacmanTypeArea;

    private ArrayList<Ghost> ghostActors;

    boolean infiniteGame = false;
    private MazeLevel maze;

    /**
     * Add all the areas
     */
    private void createAreas(){
        addArea(new Level0());
        addArea(new Level1());
        addArea(new Level2());
        addArea(new MazeLevel());
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
        currentPacmanTypeArea = (SuperPacmanArea)(getCurrentArea());
        currentPacmanTypeArea.scareCheck(player);

        Keyboard keyboard= currentPacmanTypeArea.getKeyboard();
        if(keyboard.get(Keyboard.SPACE).isDown()){
            System.out.println("Starting coop game");
            startCooperationGame();
        }
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            if(infiniteGame){
                maze = (MazeLevel)setCurrentArea("superpacman/MazeLevel", true);
                player = new SuperPacmanPlayer(maze, Orientation.UP, maze.getSpawnPoint());
            } else {
                area = (SuperPacmanArea)setCurrentArea("superpacman/Level0", true);
                player = new SuperPacmanPlayer(area, Orientation.UP, area.getSpawnPoint());
            }

            initPlayer(player);
            return true;
        }
        return false;
    }


    private void startCooperationGame(){

        SuperPacmanPlayer player2 = new SuperPacmanPlayer(currentPacmanTypeArea,Orientation.UP,generatePlayer2Spawn());
        initPlayer(player2);
        currentPacmanTypeArea.setViewCandidate(player);
        player2.changeButtons();

    }

    private DiscreteCoordinates generatePlayer2Spawn(){
        DiscreteCoordinates player1Position = player.getCurrentPosition();
        DiscreteCoordinates player2SpawnPosition;
        if(currentPacmanTypeArea.isCellWalkable(player1Position, "UP")){
            player2SpawnPosition = new DiscreteCoordinates(player1Position.x,player1Position.y + 1);
            return player2SpawnPosition;
        }
        else if(currentPacmanTypeArea.isCellWalkable(player1Position, "DOWN")){
            player2SpawnPosition = new DiscreteCoordinates(player1Position.x,player1Position.y -1 );
            return player2SpawnPosition;
        }
        else if(currentPacmanTypeArea.isCellWalkable(player1Position, "RIGHT")){
            player2SpawnPosition = new DiscreteCoordinates(player1Position.x + 1,player1Position.y);
            return player2SpawnPosition;
        }
        else if(currentPacmanTypeArea.isCellWalkable(player1Position, "LEFT")){
            player2SpawnPosition = new DiscreteCoordinates(player1Position.x - 1 ,player1Position.y);
            return player2SpawnPosition;
        }

        else{
            return null;
        }
    }

    @Override
    public void end() {
        super.end();
    }
}
