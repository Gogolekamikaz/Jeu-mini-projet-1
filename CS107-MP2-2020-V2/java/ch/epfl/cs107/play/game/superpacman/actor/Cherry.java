package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Cherry extends CollectableAreaEntity {

    private final int POINTS_GIVEN = 200;

    private Sprite sprite;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Cherry(Area area, DiscreteCoordinates position) {
        super(area, position);
        sprite = new Sprite("superpacman/cherry", 1.f, 1.f,this);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public int getPOINTS_GIVEN() {
        return POINTS_GIVEN;
    }
}
