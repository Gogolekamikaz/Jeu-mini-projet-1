package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends SuperPacmanArea {

    private final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);


    @Override
    protected void createArea() {
        Key[] keys = new Key[4];
        keys[0] = new Key(this, new DiscreteCoordinates(3,16));
        keys[1] = new Key(this, new DiscreteCoordinates(26,16));
        keys[2] = new Key(this, new DiscreteCoordinates(2,8));
        keys[3] = new Key(this, new DiscreteCoordinates(27,8));

        for (Key key : keys) {
            registerActor(key);
        }
    }

    @Override
    public DiscreteCoordinates getSpawnPoint() {
        return PLAYER_SPAWN_POSITION;
    }

    @Override
    public String getTitle() {
        return "superpacman/Level2";
    }

}
