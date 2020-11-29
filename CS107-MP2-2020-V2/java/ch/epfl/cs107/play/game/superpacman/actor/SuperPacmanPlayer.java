package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import java.util.Collections;
import java.util.List;

public class SuperPacmanPlayer extends Player {

    private Sprite [][] sprites = RPGSprite.extractSprites ("superpacman/pacman",4, 1, 1, this , 64, 64, new Orientation [] { Orientation .DOWN , Orientation.LEFT , Orientation .UP , Orientation.RIGHT });
    // crée un tableau de 4 animation
    private Animation[] animations = Animation.createAnimations(SPEED/2, sprites );
    private Animation currentAnimation;

    private SuperPacmanPlayerStatusGUI status;

    private final static int SPEED = 6;


    private final SuperPacmanPlayerHandler handler = new SuperPacmanPlayerHandler();

    public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
        super(area, orientation, coordinates);
        currentAnimation = animations[Orientation.UP.ordinal()];
        status = new SuperPacmanPlayerStatusGUI(this);
    }

    @Override
    public void draw(Canvas canvas) {
        status.draw(canvas);
        currentAnimation.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard= getOwnerArea().getKeyboard();
        super.update(deltaTime);

        Orientation desiredOrientation = this.getDesiredOrientation(keyboard);
        if(desiredOrientation != null) {
            //System.out.println("Displacement occur ? : " + this.isDisplacementOccurs());
            //System.out.println("Can enter : " + this.getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector()))));
            if (!this.isDisplacementOccurs() && this.getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))) {
                this.orientate(desiredOrientation);
                currentAnimation = animations[desiredOrientation.ordinal()];
                this.move(SPEED);

            }
        }

        if(isDisplacementOccurs()){
            currentAnimation.update(deltaTime);
        } else {
            currentAnimation.reset();
        }
    }


    private Orientation getDesiredOrientation(Keyboard keyboard){
        if(keyboard.get(Keyboard.LEFT).isDown()){
            return Orientation.LEFT;
        }
        else if(keyboard.get(Keyboard.RIGHT).isDown()){
            return Orientation.RIGHT;
        }
        else if(keyboard.get(Keyboard.UP).isDown()){
            return Orientation.UP;
        }
        else if(keyboard.get(Keyboard.DOWN).isDown()){
            return Orientation.DOWN;
        }
        return null;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return false;
    }

    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction (AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this );
    }

    private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {

        @Override
        public void interactWith(Door door) {
            setIsPassingADoor(door);
        }

        @Override
        public void interactWith(CollectableAreaEntity entity){
            entity.pickActor();
            status.increaseScore(entity.getPOINTS_GIVEN());
        }

        @Override
        public void interactWith(Key key){

        }
    }
}
