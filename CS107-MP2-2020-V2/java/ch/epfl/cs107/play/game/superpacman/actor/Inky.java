package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
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
    protected boolean targetStateChange = false;
    protected boolean positionStateChange = false;
    protected boolean scareStateChange = false;

    protected Sprite[][] sprites = RPGSprite.extractSprites ("superpacman/ghost.inky",4, 1, 1, this , 16, 16, new Orientation [] { Orientation.UP , Orientation.RIGHT , Orientation.DOWN , Orientation.LEFT });


    public Inky(Area area, Orientation orientation, DiscreteCoordinates position, Vector positionRefuge, DiscreteCoordinates positionRefugeCoord) {
        super(area, orientation, position, positionRefuge, positionRefugeCoord);
        animationsNotScared = Animation.createAnimations(4,sprites);
        animationNotScared = animationsNotScared[Orientation.DOWN.ordinal()];
        currentAnimation = animationNotScared;
    }

    @Override
    public void update(float deltaTime) {
        if(targetPosition != null && getCurrentMainCellCoordinates() == targetPosition){
            positionStateChange = true;
        }

        if(stateChanges()){
            targetPosition = evaluateTargetPosition();
        }


        super.update(deltaTime);
    }
    private DiscreteCoordinates generateReachableCell(DiscreteCoordinates origine, int radius){
        float distance = 0;
        DiscreteCoordinates potentialDestination;
        do{
            int randomX = RandomGenerator.getInstance().nextInt(ghostCurrentArea.getWidth());
            int randomY = RandomGenerator.getInstance().nextInt(ghostCurrentArea.getHeight());
            potentialDestination = new DiscreteCoordinates(randomX,randomY);
            distance = DiscreteCoordinates.distanceBetween(origine,potentialDestination);
        }while(distance > radius);
        DiscreteCoordinates validDestination = potentialDestination;
        return validDestination;
    }

    protected Orientation getNextOrientation() {
        Queue<Orientation> orientationSequence = null;
        orientationSequence = ghostCurrentArea.getAreaGraph().shortestPath(this.getCurrentMainCellCoordinates(), targetPosition);


        orientation = orientationSequence.poll();
        return orientation;
    }

    private DiscreteCoordinates evaluateTargetPosition() {
        DiscreteCoordinates targetPosition = null;
        if (viewedPlayer == null) {
            targetPosition = generateReachableCell(positionRefugeCoord, MAX_DISTANCE_WHEN_NOT_SCARED);
        }
        else if (!isAfraid && viewedPlayer != null){
            targetPosition = viewedPlayer.getCurrentPosition();
        }
        else if(isAfraid){
            targetPosition = generateReachableCell(positionRefugeCoord, MAX_DISTANCE_WHEN_SCARED);
        }
        return targetPosition;
    }

    private boolean stateChanges(){
        if(targetStateChange || positionStateChange || scareStateChange){
            targetStateChange = false;
            positionStateChange = false;
            scareStateChange = false;
            return true;
        }
        else{
            return false;
        }
    }
}
