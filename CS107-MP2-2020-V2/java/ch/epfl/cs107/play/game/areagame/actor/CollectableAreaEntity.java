package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class CollectableAreaEntity extends AreaEntity {

    private boolean isPicked;
    private boolean isAlreadyCollected = false;

    /**
     * Default CollectableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public CollectableAreaEntity(Area area, DiscreteCoordinates position) {
        super(area, Orientation.UP, position);
        isPicked = false;
    }

    @Override
    public abstract void draw(Canvas canvas);


    public void pickActor(){
        if(!isAlreadyCollected){
            isPicked = true;
            getOwnerArea().unregisterActor(this);
            isAlreadyCollected = true;
        }

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    public boolean isPicked(){ return isPicked; }

}
