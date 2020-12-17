package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.math.Vector;

public class Blinky extends Ghost{

    protected Sprite [][] sprites = RPGSprite.extractSprites ("superpacman/ghost.blinky",2, 1, 1, this , 16, 16, new Orientation [] { Orientation.UP , Orientation.RIGHT , Orientation.DOWN , Orientation.LEFT });

    public Blinky(Area area, Orientation orientation, DiscreteCoordinates position, Vector positionRefuge, DiscreteCoordinates positionRefugeCoord) {
        super(area, orientation, position, positionRefuge, positionRefugeCoord);
        animationsNotScared = Animation.createAnimations(4,sprites);
        animationNotScared = animationsNotScared[Orientation.DOWN.ordinal()];
        currentAnimation = animationNotScared;
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public Orientation getNextOrientation() {
        int randomInt = RandomGenerator.getInstance().nextInt(4);
        for(int i =0; i <=4; ++i){
            if(randomInt == i){
                orientation = Orientation.fromInt(i);
            }
        }
        return orientation;
    }

    public void forgetPacman() {
        viewedPlayer = null;
    }
}
