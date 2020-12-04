package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends SuperPacmanCollectableAreaEntity implements Logic {

    private Sprite sprite;

    /**
     * Default CollectableAreaEntity constructor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Key(Area area, DiscreteCoordinates position) {
        super(area, position);
        sprite = new Sprite("superpacman/key", 1.f, 1.f, this);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public int getPOINTS_GIVEN() {
        return 0;
    }

    @Override
    public boolean isOn() {
        return isPicked();
    }

    @Override
    public boolean isOff() {
        return !isPicked();
    }

    @Override
    public float getIntensity() {
        return 0;
    }
}
