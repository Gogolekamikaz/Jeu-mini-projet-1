package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
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

        Gate[] gates = new Gate[14];
        gates[0] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,14), keys[0]);
        gates[1] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(5,12), keys[0]);
        gates[2] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,10), keys[0]);
        gates[3] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,8), keys[0]);
        gates[4] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,14), keys[1]);
        gates[5] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(24,12), keys[1]);
        gates[6] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,10), keys[1]);
        gates[7] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,8), keys[1]);
        gates[8] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(10,2), keys[2], keys[3]);
        gates[9] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(19,2), keys[2], keys[3]);
        gates[10] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(12,8), keys[2], keys[3]);
        gates[11] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(17,8), keys[2], keys[3]);
        gates[12] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,3), this);
        gates[13] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(15,3), this);
        for (Gate gate : gates) {
            registerActor(gate);
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
