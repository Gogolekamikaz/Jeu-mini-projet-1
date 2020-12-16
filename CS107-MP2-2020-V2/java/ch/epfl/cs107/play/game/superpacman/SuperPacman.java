package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.InvisibleMadeForViewCandidate;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.*;
import ch.epfl.cs107.play.game.superpacman.userInterface.Home.Home;
import ch.epfl.cs107.play.game.superpacman.userInterface.LevelChoice.LevelChoice;
import ch.epfl.cs107.play.game.superpacman.userInterface.Menu.Menu;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;

public class SuperPacman extends RPG {

    private SuperPacmanPlayer player;
    private SuperPacmanPlayer player2;
    private SuperPacmanArea area;
    private SuperPacmanArea currentPacmanTypeArea;
    private float zoomMin = 15.f;
    private boolean coopGameStarted = false;
    private float lastUpdateDistance = 0;
    private boolean coopGameAlreadyStarted = false;

    private ArrayList<Ghost> ghostActors;

    private boolean infiniteGame = false;
    private MazeLevel maze;
    private int mazeCount = 0;

    /**
     * Add all the areas
     */
    private void createAreas(){
        addArea(new Home(this));
        addArea(new Menu(this));
        addArea(new LevelChoice(this));
        addArea(new Level0());
        addArea(new Level1());
        addArea(new Level2());
        addArea(new MazeLevel(mazeCount));
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
        currentPacmanTypeArea.scareCheck(player);           //Check si les fantômes doivent être effrayés du joueur 1
        if(coopGameStarted){
            currentPacmanTypeArea.scareCheck(player2);     //Check si les fantômes doivent être effrayés du joueur 2
            updateCameraTarget(); // On adapte perpetuellement la caméra en fonction de la distance entre les joueurs (voir la fonction)

            // Si un des joueurs passent une porte, on souhaite que l'autre joueur le suive dans sa nouvelle aire.

            //if(player.isPassingADoor()){
            //    player2.passesDoor(player.passedDoor());
            //}
            //else if(player2.isPassingADoor()){
            //    player.passesDoor(player2.passedDoor());
            //}
        }

        Keyboard keyboard= currentPacmanTypeArea.getKeyboard();
        if(keyboard.get(Keyboard.SPACE).isDown()){
            if(!coopGameAlreadyStarted){
                startCooperationGame();
                coopGameAlreadyStarted = true;          //On veuille à ce que l'utilisateur ne puisse créer qu'un seul autre joueur coopératif.
            }
        }

        if(((SuperPacmanArea)getCurrentArea()).getBackToMenu()){
            setNextArea("superpacman/Menu");
        }

        if(getCurrentArea() instanceof MazeLevel && ((MazeLevel)getCurrentArea()).getMazeNum() == mazeCount){
            nextMaze();
        }
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            if(infiniteGame){
                maze = (MazeLevel)setCurrentArea("superpacman/MazeLevel"+mazeCount, true);
                player = new SuperPacmanPlayer(maze, Orientation.UP, maze.getSpawnPoint());
            } else {
                area = (SuperPacmanArea)setCurrentArea("superpacman/Home", true);
                player = new SuperPacmanPlayer(area, Orientation.UP, area.getSpawnPoint());
            }
            initPlayer(player);
            return true;
        }
        return false;
    }


    private void startCooperationGame(){

        coopGameStarted = true;
        player2 = new SuperPacmanPlayer(currentPacmanTypeArea,Orientation.UP,generatePlayer2Spawn());
        initPlayer(player2);
        currentPacmanTypeArea.setViewCandidate(player);
        player2.changeButtons();
        player2.changeSprite();
        player2.adaptGUI();


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

    private float evaluatePlayersDistance(){
        float distance = DiscreteCoordinates.distanceBetween(player.getCurrentPosition(), player2.getCurrentPosition());
        return distance;
    }

    private DiscreteCoordinates evaluateMiddlePositionBetweenPlayers(){
        int middleX = (player.getCurrentPosition().x + player2.getCurrentPosition().x)/2;
        int middleY = (player.getCurrentPosition().y + player2.getCurrentPosition().y)/2;
        return (new DiscreteCoordinates(middleX,middleY));
    }
    private void updateCameraTarget(){

        float distanceBetweenPlayer = evaluatePlayersDistance();
        if((distanceBetweenPlayer > lastUpdateDistance) && (distanceBetweenPlayer > 6.5)){
            DiscreteCoordinates middlePosition = evaluateMiddlePositionBetweenPlayers();
            InvisibleMadeForViewCandidate viewObject = new InvisibleMadeForViewCandidate(getCurrentArea(), middlePosition);
            getCurrentArea().setViewCandidate(viewObject);
        }
        lastUpdateDistance = evaluatePlayersDistance();


        //SuperPacmanArea.cameraScaleFactor = 15.f;
        //for(float i = 0.f ; i <= distanceBetweenPlayer; ++i){
        //    SuperPacmanArea.cameraScaleFactor += 1;
        //}
    }

    public void setNextArea(String destination) {
        this.area = (SuperPacmanArea)setCurrentArea(destination, true);
        if(area instanceof MazeLevel){
            player = new SuperPacmanPlayer((MazeLevel)area, Orientation.UP, ((MazeLevel)area).getSpawnPoint());
        } else {
            player = new SuperPacmanPlayer(area, Orientation.UP, area.getSpawnPoint());
        }
        initPlayer(player);
    }

    public void nextMaze(){
        mazeCount++;
        addArea(new MazeLevel(mazeCount));
    }

    @Override
    public void end() {
        super.end();
    }
}
