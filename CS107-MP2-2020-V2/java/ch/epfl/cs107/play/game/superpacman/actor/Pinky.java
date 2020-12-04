package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.math.Vector;

public class Pinky extends Ghost{

    protected Sprite[][] sprites = RPGSprite.extractSprites ("superpacman/ghost.pinky",4, 1, 1, this , 16, 16, new Orientation [] { Orientation.UP , Orientation.RIGHT , Orientation.DOWN , Orientation.LEFT });

    public Pinky(Area area, Orientation orientation, DiscreteCoordinates position, Vector positionRefuge) {
        super(area, orientation, position, positionRefuge);
        animationsNotScared = Animation.createAnimations(4,sprites);
        animationNotScared = animationsNotScared[Orientation.DOWN.ordinal()];
        currentAnimation = animationNotScared;
    }
    protected Orientation getNextOrientation() {
        int randomInt = RandomGenerator.getInstance().nextInt(4);
        for(int i =0; i <=4; ++i){
            if(randomInt == i){
                orientation = Orientation.fromInt(i);
            }
        }
        return orientation;
    }

}
