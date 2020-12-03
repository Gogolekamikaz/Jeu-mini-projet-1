package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.rpg.actor.Sign;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import java.util.Collections;
import java.util.List;

public class SuperPacmanPlayer extends Player {

    private Sprite [][] sprites = RPGSprite.extractSprites ("superpacman/pacman",4, 1, 1, this , 64, 64, new Orientation [] { Orientation .DOWN , Orientation.LEFT , Orientation .UP , Orientation.RIGHT });
    // crée un tableau de 4 animation
    private Animation[] animations = Animation.createAnimations(SPEED/2, sprites );
    private Animation currentAnimation;

    private boolean isInvincible = false;

    private SuperPacmanPlayerStatusGUI status;
    private int hp;
    private int score;
    public static float timer = 20;
    private final static int SPEED = 6;
    private Orientation desiredOrientation;


    private final SuperPacmanPlayerHandler handler = new SuperPacmanPlayerHandler();

    public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
        super(area, orientation, coordinates);
        currentAnimation = animations[Orientation.UP.ordinal()];
        status = new SuperPacmanPlayerStatusGUI(this);
        hp = 3;
        score = 0;
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
            if (!this.isDisplacementOccurs() && this.getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))) {
                this.orientate(desiredOrientation);
                currentAnimation = animations[desiredOrientation.ordinal()];
                this.move(SPEED);

            }
        } else {
            this.move(SPEED);
        }
        if(isDisplacementOccurs()){
            currentAnimation.update(deltaTime);
        } else {
            currentAnimation.reset();
        }

        if(isInvincible == true){
            timer -= deltaTime;
            if(timer < 0){
                isInvincible = false;
                timer = 10;
            }
        }
    }

    private void respawnPlayer(){

        if(getOwnerArea().getTitle() == "superpacman/Level0"){
            Vector spawnPosition = new Vector(10,1);
            setCurrentPosition(spawnPosition);
        }
        else if(getOwnerArea().getTitle() == "superpacman/Level1"){
            Vector spawnPosition = new Vector(15,6);
            setCurrentPosition(spawnPosition);
        }
        else if(getOwnerArea().getTitle() == "superpacman/Level2") {
            Vector spawnPosition = new Vector(15, 29);
            setCurrentPosition(spawnPosition);
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

    public int getScore(){ return score;}

    public void increaseScore(int scorePoint){
        score += scorePoint;
    }

    public int getHealth(){ return hp;}


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

    public boolean isInvincible(){
        if(isInvincible == true){
            return true;
        }
        else{
            return false;
        }
    }

    private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {

        @Override
        public void interactWith(Door door) {
            setIsPassingADoor(door);
        }

        @Override
        public void interactWith(CollectableAreaEntity entity){
            entity.pickActor();
            increaseScore(entity.getPOINTS_GIVEN());
            if(entity instanceof Bonus){
                isInvincible = true;
            }

        }

        public void interactWith(Ghost ghost) {
            if(isInvincible()){
                ghost.forgetPacman();
                ghost.setPositionRefuge();
                increaseScore(ghost.getGHOST_SCORE()); //Si invicible, on mange le fantôme il nous rapporte donc des points
            }
            else{
                hp -= 1;
                respawnPlayer();
            }
        }

        @Override
        public void interactWith(Key key){

        }
    }
}
