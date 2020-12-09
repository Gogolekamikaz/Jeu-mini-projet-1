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

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Queue;

public class Pinky extends AgressiveGhost{

    protected Sprite[][] sprites = RPGSprite.extractSprites ("superpacman/ghost.pinky",4, 1, 1, this , 16, 16, new Orientation [] { Orientation.UP , Orientation.RIGHT , Orientation.DOWN , Orientation.LEFT });
    DiscreteCoordinates trivialPosition = new DiscreteCoordinates(0,0);
    private int MAX_DISTANCE_WHEN_NOT_SCARED = ghostCurrentArea.getWidth();
    private int MAX_DISTANCE_WHEN_SCARED_BUT_WITH_PLAYER_UNSEEN = MAX_DISTANCE_WHEN_NOT_SCARED;
    private int MIN_AFRAID_DISTANCE = 10;
    private int MAX_RANDOM_ATTEMPT = 200;

    public Pinky(Area area, Orientation orientation, DiscreteCoordinates position, Vector positionRefuge, DiscreteCoordinates positionRefugeCoord) {
        super(area, orientation, position, positionRefuge, positionRefugeCoord);
        animationsNotScared = Animation.createAnimations(4,sprites);
        animationNotScared = animationsNotScared[Orientation.DOWN.ordinal()];
        currentAnimation = animationNotScared;

        targetPosition = evaluateTargetPosition();
        orientationSequence = evaluateOrientationSequence();
    }

    protected DiscreteCoordinates generateReachableEscapeCell(){
        int attempt = 0;
        float distance = 0;
        Queue<Orientation> orientationSequenceTried;
        DiscreteCoordinates potentialDestination = null;
        do{
            int randomX = RandomGenerator.getInstance().nextInt(ghostCurrentArea.getWidth());
            int randomY = RandomGenerator.getInstance().nextInt(ghostCurrentArea.getHeight());
            potentialDestination = new DiscreteCoordinates(randomX,randomY);
            distance = DiscreteCoordinates.distanceBetween(viewedPlayer.getCurrentPosition() , potentialDestination);
            orientationSequenceTried =  ghostCurrentArea.shortestPath(getCurrentMainCellCoordinates(), potentialDestination);
            attempt += 1;

        }while((distance < MIN_AFRAID_DISTANCE || orientationSequenceTried == null) && attempt < MAX_RANDOM_ATTEMPT);
        DiscreteCoordinates validEscapeDestination = potentialDestination;
        return validEscapeDestination;
    }

        protected DiscreteCoordinates evaluateTargetPosition() {
        DiscreteCoordinates outputPosition = null;
        if (viewedPlayer == null && !isAfraid) {
            outputPosition = generateReachableCell(trivialPosition, MAX_DISTANCE_WHEN_NOT_SCARED);
        }
        else if (!isAfraid && viewedPlayer != null){
            outputPosition = viewedPlayer.getCurrentPosition();
        }
        else if(isAfraid && viewedPlayer == null){
            outputPosition = generateReachableCell(trivialPosition, MAX_DISTANCE_WHEN_SCARED_BUT_WITH_PLAYER_UNSEEN);
        }

        else if(isAfraid && viewedPlayer != null){
            outputPosition = generateReachableEscapeCell();
        }
        return outputPosition;
    }

}
