package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AgressiveGhost extends Ghost {

    private static final boolean DEBUG_PATH = false;

    protected DiscreteCoordinates targetPosition;
    private boolean targetingStateChange = false;
    private boolean positionStateChange = false;
    private boolean scareStateChange = false;
    private boolean scareStateChangeAlreadyClaimed = false;
    private boolean targetingStateChangeAlreadyClaimed = false;
    private boolean unscareStateChangeAlreadyClaimed = true;
    protected Queue<Orientation> orientationSequence;

    Path path;

    public AgressiveGhost(Area area, Orientation orientation, DiscreteCoordinates position, Vector positionRefuge, DiscreteCoordinates positionRefugeCoord) {
        super(area, orientation, position, positionRefuge, positionRefugeCoord);

    }

    @Override
    public void update(float deltaTime) {

        //Control ghost position state

        if(orientationSequence == null || orientationSequence.size() == 0){
            System.out.println("SEQUENCE NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
            positionStateChange = true;
        }
        // Control ghost scare state
        if(isAfraid){
            if(!scareStateChangeAlreadyClaimed){
                scareStateChange = true;
                System.out.println("Scare state changes and ghosts become scared");
                scareStateChangeAlreadyClaimed = true;
                unscareStateChangeAlreadyClaimed = false;
            }
        }
        if(!isAfraid){
            if(!unscareStateChangeAlreadyClaimed){
                scareStateChange = true;
                unscareStateChangeAlreadyClaimed = true;
                scareStateChangeAlreadyClaimed = false;
            }

        }
        //Control ghost targeting state
        if(this.viewedPlayer != null){
            if(!targetingStateChangeAlreadyClaimed){
                targetingStateChange = true;
                targetingStateChangeAlreadyClaimed = true;
            }
        }

        if(stateChanges()){
            System.out.println("Targeting state: " + targetingStateChange + " Scare state: "+ scareStateChange+ " position state change : " + positionStateChange);
            targetPosition = evaluateTargetPosition();
            orientationSequence = evaluateOrientationSequence();
            System.out.println(orientationSequence);
            targetingStateChange = false;
            positionStateChange = false;
            scareStateChange = false;
        }

        super.update(deltaTime);
    }
    protected DiscreteCoordinates generateReachableCell(DiscreteCoordinates origine, int radius){
        float distance = 0;
        Queue<Orientation> orientationSequenceTried;
        DiscreteCoordinates potentialDestination;
        do {
            int randomX = RandomGenerator.getInstance().nextInt(ghostCurrentArea.getWidth());
            int randomY = RandomGenerator.getInstance().nextInt(ghostCurrentArea.getHeight());
            potentialDestination = new DiscreteCoordinates(randomX, randomY);
            distance = DiscreteCoordinates.distanceBetween(origine, potentialDestination);
            orientationSequenceTried = ghostCurrentArea.shortestPath(getCurrentMainCellCoordinates(), potentialDestination);
        } while (distance > radius || orientationSequenceTried == null);
        DiscreteCoordinates validDestination = potentialDestination;
        return validDestination;
    }

    protected Orientation getNextOrientation(){
        if(viewedPlayer != null){
            targetPosition = evaluateTargetPosition();
            orientationSequence = evaluateOrientationSequence();
            System.out.println(orientationSequence);
        }
        Orientation orientation = orientationSequence.poll();

        path = new Path(getPosition(), new LinkedList<>(orientationSequence));
        return orientation;
    }

    abstract DiscreteCoordinates evaluateTargetPosition();

    protected Queue<Orientation> evaluateOrientationSequence(){
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

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(DEBUG_PATH){
            path.draw(canvas);
        }
    }
}
