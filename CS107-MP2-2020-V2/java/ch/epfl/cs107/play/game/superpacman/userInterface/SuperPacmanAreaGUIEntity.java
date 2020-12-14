package ch.epfl.cs107.play.game.superpacman.userInterface;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class SuperPacmanAreaGUIEntity extends AreaEntity {

    private static Graphics[] displays;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     */
    public SuperPacmanAreaGUIEntity(SuperPacmanArea area, Graphics... displays) {
        super(area, Orientation.UP, area.getSpawnPoint());
        this.displays = displays.clone();

    }

    @Override
    public void draw(Canvas canvas) {
        for (int i=0; i<displays.length; i++){
            displays[i].draw(canvas);
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return false;
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
}
