package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Gate extends AreaEntity {

    private Logic logic1;

    private boolean isOpen;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic logic) {
        super(area, orientation, position);
        this.logic1 = logic;
        isOpen = false;
        if(!isOpen){
            SuperPacmanArea currentArea= (SuperPacmanArea)(getOwnerArea());
            currentArea.deactivateNode(getCurrentMainCellCoordinates(), logic1);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(!isOpen){
            Sprite sprite;

            if(getOrientation() == Orientation.DOWN || getOrientation() == Orientation.UP){
                sprite = new Sprite("superpacman/gate", 1.f, 1.f, this, new RegionOfInterest(0,0,64,64));
            } else {
                sprite = new Sprite("superpacman/gate", 1.f, 1.f, this, new RegionOfInterest(0,64,64,64));
            }

            sprite.draw(canvas);
        }

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return !isOpen;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }

    @Override
    public void update(float deltaTime) {
        if(logic1.isOff()){
            isOpen = false;
        } else {
            isOpen = true;
        }
    }
}
