package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Blinky;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.Queue;

public abstract class SuperPacmanArea extends Area implements Logic {

    protected abstract void createArea();
    private SuperPacmanBehavior behavior;
    private ArrayList<Ghost> areaGhostActors;
    private AreaGraph areaGraph;
    protected final static float cameraScaleFactor = 15.f;

    private boolean pause;

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            // Set the behavior map
            behavior = new SuperPacmanBehavior(window, getTitle());
            setBehavior(behavior);
            behavior.registerActors(this);
            pause = false;

            createArea();
            return true;
        }
        return false;
    }

    public ArrayList<Ghost> getAreaGhostActors(){
        return areaGhostActors;
    }

    public Queue<Orientation> shortestPath(DiscreteCoordinates origine, DiscreteCoordinates arrivee){
        return(behavior.shortestPath(origine, arrivee));
    }

    public void scareCheck(SuperPacmanPlayer player){
        behavior.scareCheck(player);
    }

    public AreaGraph getAreaGraph(){return areaGraph;}

    public final float getCameraScaleFactor(){return cameraScaleFactor;}

    public abstract DiscreteCoordinates getSpawnPoint();

    @Override
    public boolean isOn() {
        if(contains(new Diamond(this, new DiscreteCoordinates(0,0)))){
            return false;
        }
        return true;
    }

    @Override
    public boolean isOff() {
        if(contains(new Diamond(this, new DiscreteCoordinates(0,0)))){
            return true;
        }
        return false;
    }

    @Override
    public float getIntensity() {
        return 0;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Keyboard keyboard= this.getKeyboard();
        if(keyboard.get(Keyboard.P).isPressed()){
            suspend();
        }
    }

    @Override
    public void suspend(){
        pause = true;
        System.out.println("pause");
    }

}
