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

public class Inky extends Ghost{
    private final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
    private final int MAX_DISTANCE_WHEN_SCARED = 5;
    protected DiscreteCoordinates targetPosition;
    protected boolean targetingStateChange = false;
    protected boolean positionStateChange = false;
    protected boolean scareStateChange = false;
    private boolean scareStateChangeAlreadyClaimed = false;
    private boolean targetingStateChangeAlreadyClaimed = false;
    private Queue<Orientation> orientationSequence;

    protected Sprite[][] sprites = RPGSprite.extractSprites ("superpacman/ghost.inky",4, 1, 1, this , 16, 16, new Orientation [] { Orientation.UP , Orientation.RIGHT , Orientation.DOWN , Orientation.LEFT });


    public Inky(Area area, Orientation orientation, DiscreteCoordinates position, Vector positionRefuge, DiscreteCoordinates positionRefugeCoord) {
        super(area, orientation, position, positionRefuge, positionRefugeCoord);
        animationsNotScared = Animation.createAnimations(4,sprites);
        animationNotScared = animationsNotScared[Orientation.DOWN.ordinal()];
        currentAnimation = animationNotScared;

        targetPosition = evaluateTargetPosition();
        orientationSequence = evaluateOrientationSequence();
    }

    @Override
    public void update(float deltaTime) {
        //if(targetPosition != null && (getCurrentMainCellCoordinates().x == targetPosition.x && getCurrentMainCellCoordinates().y == targetPosition.y)){
        //    positionStateChange = true;
        //}

        if(orientationSequence.size() == 0){
            positionStateChange = true;
            System.out.println("Size = 0, finding new one");
        }

        if(isAfraid){
            if(!scareStateChangeAlreadyClaimed){
                scareStateChange = true;
                scareStateChangeAlreadyClaimed = true;
            }
            if(timer < 0){
                scareStateChange = true;
                scareStateChangeAlreadyClaimed = false;
            }
        }

        if(this.viewedPlayer != null){
            if(!targetingStateChangeAlreadyClaimed){
                targetingStateChange = true;
                targetingStateChangeAlreadyClaimed = true;
            }
        }



        if(stateChanges()){
            System.out.println("State changes !");
            targetPosition = evaluateTargetPosition();
            orientationSequence = evaluateOrientationSequence();

            targetingStateChange = false;
            positionStateChange = false;
            scareStateChange = false;
        }

        super.update(deltaTime);
    }
    private DiscreteCoordinates generateReachableCell(DiscreteCoordinates origine, int radius){
        float distance = 0;
        Queue<Orientation> orientationSequenceTried;
        DiscreteCoordinates potentialDestination;
        do{
            int randomX = RandomGenerator.getInstance().nextInt(ghostCurrentArea.getWidth());
            int randomY = RandomGenerator.getInstance().nextInt(ghostCurrentArea.getHeight());
            potentialDestination = new DiscreteCoordinates(randomX,randomY);
            distance = DiscreteCoordinates.distanceBetween(origine,potentialDestination);
            orientationSequenceTried =  ghostCurrentArea.shortestPath(getCurrentMainCellCoordinates(), potentialDestination);
        }while(distance > radius || orientationSequenceTried == null);
        DiscreteCoordinates validDestination = potentialDestination;
        return validDestination;
    }

    protected Orientation getNextOrientation() {
        orientation = orientationSequence.poll();
        return orientation;
    }

    private DiscreteCoordinates evaluateTargetPosition() {
        DiscreteCoordinates outputPosition = null;
        if (viewedPlayer == null && !isAfraid) {
            outputPosition = generateReachableCell(positionRefugeCoord, MAX_DISTANCE_WHEN_NOT_SCARED);
        }
        else if (!isAfraid && viewedPlayer != null){
            outputPosition = viewedPlayer.getCurrentPosition();
        }
        else if(isAfraid){
            outputPosition = generateReachableCell(positionRefugeCoord, MAX_DISTANCE_WHEN_SCARED);
        }
        return outputPosition;
    }

    private Queue<Orientation> evaluateOrientationSequence(){
        return ghostCurrentArea.shortestPath(getCurrentMainCellCoordinates(), targetPosition);
    }

    private boolean stateChanges(){
        if(targetingStateChange || positionStateChange || scareStateChange){
            return true;
        }
        else{
            return false;
        }
    }

    public void forgetPacman() {
        this.viewedPlayer = null;
        targetingStateChange = true;
        targetingStateChangeAlreadyClaimed = false;
    }
}
