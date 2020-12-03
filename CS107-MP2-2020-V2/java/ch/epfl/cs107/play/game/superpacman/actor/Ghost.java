package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ghost extends MovableAreaEntity implements Interactor {
    protected final int GHOST_SCORE = 500;
    protected Sprite sprite;
    private final int viewRadius = 5;
    float keepOriented = 2;

    protected Sprite [][] spritesScared = RPGSprite.extractSprites ("superpacman/ghost.afraid",2, 1, 1, this , 16, 16, new Orientation [] { Orientation.UP , Orientation.DOWN, Orientation.LEFT, Orientation.RIGHT});
    Animation[] animationsScared = Animation.createAnimations(4,spritesScared);
    Animation animationScared = animationsScared[Orientation.UP.ordinal()];

    public Animation currentAnimation = null;
    public Animation animationNotScared;
    public Animation[] animationsNotScared = null;

    SuperPacmanPlayer viewedPlayer;
    protected Vector positionRefuge;
    Orientation orientation;
    boolean isAfraid = false;
    float timer = SuperPacmanPlayer.timer;

    public void update(float deltaTime) {
        super.update(deltaTime);

        if(isAfraid){  // Le fantôme reste effrayé autant de temps que le joueur est invicible (
            timer -= deltaTime;
            if(timer < 0){
                isAfraid = false;
                timer = SuperPacmanPlayer.timer;
            }
        }

        if (isDisplacementOccurs()) {
            if(isAfraid){
                currentAnimation = animationScared;
            }
            else{
                currentAnimation = animationNotScared;
            }
            currentAnimation.update(deltaTime);

        } else{
            currentAnimation.reset();
            keepOriented -= deltaTime;
            if (keepOriented < 0){
                orientation = getNextOrientation();
                if(!isAfraid){
                    currentAnimation = animationsNotScared[orientation.ordinal()];
                }
                else{
                    currentAnimation = animationScared;
                }
                this.orientate(orientation);
                keepOriented = 2;
                move(18);
            }
        }
    }

    public Ghost(Area area, Orientation orientation, DiscreteCoordinates position, Vector positionRefuge) {
        super(area, orientation, position);
        this.positionRefuge = positionRefuge;
    }

    public void setPositionRefuge() {
        setCurrentPosition(positionRefuge);
    }

    protected Orientation getNextOrientation() {
        Orientation orientation = null;
        return orientation;
    }

    public void setAfraid(SuperPacmanPlayer player) {
        if(player.isInvincible()){
            isAfraid = true;
        }

    }

    public void setUnAfraid(){
        isAfraid = false;
    }


    public void forgetPacman() {
        this.viewedPlayer = null;
    }

    public int getGHOST_SCORE() {
        return GHOST_SCORE;
    }

    @Override
    public void draw(Canvas canvas) {
        try{
            currentAnimation.draw(canvas);
        }
        catch(Exception e){

        }

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        List<DiscreteCoordinates> viewField = new ArrayList<DiscreteCoordinates>();
        for (int x = getCurrentMainCellCoordinates().x - viewRadius; x <= getCurrentMainCellCoordinates().x + viewRadius; ++x) {
            for (int y = getCurrentMainCellCoordinates().y - viewRadius; y <= getCurrentMainCellCoordinates().y + viewRadius; ++y) {
                DiscreteCoordinates cellInViewRadius = new DiscreteCoordinates(x,y);
                viewField.add(cellInViewRadius);
            }
        }
        return viewField;

    }

    @Override
    public boolean wantsCellInteraction() {
        return false;
    }

    @Override
    public boolean wantsViewInteraction() {
        return true;
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
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this );
    }

    public void interactWith(SuperPacmanPlayer player){
        this.viewedPlayer = player;
        
    }
}
