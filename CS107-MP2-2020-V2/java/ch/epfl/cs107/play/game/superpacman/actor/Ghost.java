package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public class Ghost extends MovableAreaEntity implements Interactor {
    protected final int GHOST_SCORE = 500;
    protected Sprite sprite;

    SuperPacmanPlayer viewedPlayer;
    protected Vector positionRefuge;
    Orientation orientation;
    boolean isAfraid = true;

    public void update(float deltaTime) {
        super.update(deltaTime);

        if(isDisplacementOccurs()){
            
        }
        else{
            orientation = getNextOrientation();
        }
    }

    public Ghost(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);

    }

    public void setPositionRefuge(){
        setCurrentPosition(positionRefuge);
        }

    protected Orientation getNextOrientation(){
        Orientation orientation = null;
        return orientation;
    }

    public void setAfraid(){
        isAfraid = true;
    }

    public void forgetPacman(){
        this.viewedPlayer = null;
    }

    public int getGHOST_SCORE(){
        return GHOST_SCORE;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return null;
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        return false;
    }

    @Override
    public boolean wantsViewInteraction() {
        return true;
    }

    @Override
    public void interactWith(Interactable other) {

    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }

    public void interactWith(SuperPacmanPlayer player){
        this.viewedPlayer = player;
        
    }
}
