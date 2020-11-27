package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class SuperPacmanPlayer extends Player {



    private Sprite sprite;
    private final static int SPEED = 6;

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard= getOwnerArea().getKeyboard();
        super.update(deltaTime);



        Orientation desiredOrientation = this.getDesiredOrientation(keyboard);
        if(desiredOrientation != null) {
            System.out.println("Displacement occur ? : " + this.isDisplacementOccurs());
            System.out.println("Can enter : " + this.getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector()))));
            if (this.isDisplacementOccurs() == false && this.getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector()))) == true) {
                this.orientate(desiredOrientation);
                this.move(SPEED);

            }
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

    public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
        super(area, orientation, coordinates);
        sprite = new Sprite("superpacman/bonus", 1.f, 1.f,this);
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
    public void acceptInteraction ( AreaInteractionVisitor v) {
        (( SuperPacmanInteractionVisitor )v). interactWith (this );
    }

    class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {

        @Override
        public void interactWith(Door door) {
            setIsPassingADoor(door);
        }
    }
}
