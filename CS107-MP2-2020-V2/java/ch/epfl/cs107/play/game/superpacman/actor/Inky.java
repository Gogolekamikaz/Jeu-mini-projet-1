package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.math.Vector;

import java.util.Queue;

public class Inky extends AgressiveGhost {

    private final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
    private final int MAX_DISTANCE_WHEN_SCARED = 5;

    protected Sprite[][] sprites = RPGSprite.extractSprites("superpacman/ghost.inky", 4, 1, 1, this, 16, 16, new Orientation[]{Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});

    public Inky(Area area, Orientation orientation, DiscreteCoordinates position, Vector positionRefuge, DiscreteCoordinates positionRefugeCoord) {
        super(area, orientation, position, positionRefuge, positionRefugeCoord);
        animationsNotScared = Animation.createAnimations(4, sprites);
        animationNotScared = animationsNotScared[Orientation.DOWN.ordinal()];
        currentAnimation = animationNotScared;

        targetPosition = evaluateTargetPosition();
        orientationSequence = evaluateOrientationSequence();

    }

    protected DiscreteCoordinates evaluateTargetPosition() {
        DiscreteCoordinates outputPosition = null;
        if (viewedPlayer == null && !isAfraid) {
            outputPosition = generateReachableCell(positionRefugeCoord, MAX_DISTANCE_WHEN_NOT_SCARED);
        } else if (!isAfraid && viewedPlayer != null) {
            outputPosition = viewedPlayer.getCurrentPosition();
        } else if (isAfraid) {
            outputPosition = generateReachableCell(positionRefugeCoord, MAX_DISTANCE_WHEN_SCARED);
        }
        return outputPosition;
    }

}
