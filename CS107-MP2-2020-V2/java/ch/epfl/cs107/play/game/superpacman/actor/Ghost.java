package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ghost extends MovableAreaEntity implements Interactor {
    protected final int GHOST_SCORE = 500;
    protected Sprite sprite;
    private final int viewRadius = 5;

    SuperPacmanPlayer viewedPlayer;
    protected Vector positionRefuge;
    Orientation orientation;
    boolean isAfraid = false;

    public void update(float deltaTime) {
        super.update(deltaTime);


        if (isDisplacementOccurs()) {
            if (isAfraid) {
                sprite = new Sprite("superpacman/ghost.afraid", 1.f, 1.f, this);
            }
        } else {
            orientation = getNextOrientation();
            move(18);
        }
    }

    public Ghost(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    public void setPositionRefuge() {
        setCurrentPosition(positionRefuge);
    }

    protected Orientation getNextOrientation() {
        Orientation orientation = null;
        return orientation;
    }

    public void setAfraid() {
        isAfraid = true;
    }

    public void forgetPacman() {
        this.viewedPlayer = null;
    }

    public int getGHOST_SCORE() {
        return GHOST_SCORE;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        List<DiscreteCoordinates> viewField = new ArrayList<DiscreteCoordinates>();
        for (int x = getCurrentMainCellCoordinates().x - viewRadius; x <= getCurrentMainCellCoordinates().x + viewRadius; ++x) {
            for (int y = getCurrentMainCellCoordinates().y - viewRadius; y <= getCurrentMainCellCoordinates().y + viewRadius; ++y) {
                DiscreteCoordinates cellInViewRadius = new DiscreteCoordinates(x,y);
                viewField.add(cellInViewRadius);
            }
        }
        return viewField;

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
        ((SuperPacmanInteractionVisitor)v).interactWith(this );
    }

    public void interactWith(SuperPacmanPlayer player){
        this.viewedPlayer = player;
        
    }
}
