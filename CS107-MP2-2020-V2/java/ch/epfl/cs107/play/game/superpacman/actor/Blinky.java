package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

public class Blinky extends Ghost {

    public Blinky(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        sprite = new Sprite("superpacman/ghost.blinky", 1.f, 1.f,this);
    }

    @Override
    protected Orientation getNextOrientation() {
        int randomInt = RandomGenerator.getInstance().nextInt(3);
        for(int i =0; i <=3; ++i){
            if(randomInt == i){
                orientation = Orientation.fromInt(i);
            }
        }
        return orientation;
    }
}
