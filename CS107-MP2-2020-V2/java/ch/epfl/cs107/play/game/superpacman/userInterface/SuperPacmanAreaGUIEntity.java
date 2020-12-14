package ch.epfl.cs107.play.game.superpacman.userInterface;

import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.userInterface.Pause.PauseScreen;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class SuperPacmanAreaGUIEntity extends AreaEntity {

    private PauseScreen pauseScreen;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     */
    public SuperPacmanAreaGUIEntity(SuperPacmanArea area) {
        super(area, Orientation.UP, area.getSpawnPoint());

        pauseScreen = new PauseScreen(area);
    }

    @Override
    public void draw(Canvas canvas) {
        pauseScreen.draw(canvas);
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
