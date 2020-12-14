package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

// Cet acteur/entité a comme unique but d'être un acteur sur lequel peut se fixer l'écran à l'aide de setViewCandidate, il ne s'agit pas d'un réel objet utilisable.
public class InvisibleMadeForViewCandidate extends CollectableAreaEntity {

    /**
     * Default CollectableAreaEntity constructor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public InvisibleMadeForViewCandidate(Area area, DiscreteCoordinates position) {
        super(area, position);
    }

    @Override
    public void draw(Canvas canvas) {

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
