package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.actor.SoundAcoustics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.MazeLevel;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.GhostInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.userInterface.SuperPacmanGUIWindow;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.*;

import java.util.Collections;
import java.util.List;

public class SuperPacmanPlayer extends Player {

    private Sprite [][] sprites = RPGSprite.extractSprites ("superpacman/pacman",4, 1, 1, this , 64, 64, new Orientation [] { Orientation.DOWN , Orientation.LEFT , Orientation.UP , Orientation.RIGHT });
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
    private DiscreteCoordinates spawnPosition;

    private SoundAcoustics pacPac = new SoundAcoustics("sounds/pacman_chomp.wav");
    private double startOfSound = 0;
    private SoundAcoustics pacEatFruit = new SoundAcoustics("sounds/pacman_eatfruit.wav");
    private SoundAcoustics pacDeath = new SoundAcoustics("sounds/pacman_death.wav");
    private SoundAcoustics pacEatGhost = new SoundAcoustics("sounds/pacman_eatghost.wav");
    private SoundAcoustics pacEatCoin = new SoundAcoustics("sounds/collectcoin.wav");
    private SoundAcoustics pacEatKey = new SoundAcoustics("sounds/key_sound.wav");
    int buttonUP = Keyboard.UP;
    int buttonDOWN = Keyboard.DOWN;
    int buttonRIGHT = Keyboard.RIGHT;
    int buttonLEFT = Keyboard.LEFT;


    private final SuperPacmanPlayerHandler handler = new SuperPacmanPlayerHandler();

    /**
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial player orientation, not null
     * @param coordinates (Coordinates): Initial position, not null
     */
    public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
        super(area, orientation, coordinates);
        currentAnimation = animations[Orientation.UP.ordinal()];
        desiredOrientation = Orientation.UP;
        status = new SuperPacmanPlayerStatusGUI(this);
        hp = 3;
        score = 0;
        pacPac.shouldBeStarted();
    }

    @Override
    public void draw(Canvas canvas) {
        if(!((SuperPacmanArea)getOwnerArea()).isGameEnd()){
            status.draw(canvas);
        }
        currentAnimation.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {
        if(!((SuperPacmanArea)getOwnerArea()).isPaused() && !(getOwnerArea() instanceof SuperPacmanGUIWindow)) {
            Keyboard keyboard = getOwnerArea().getKeyboard();
            super.update(deltaTime);

            startOfSound += deltaTime;

            if (this.getDesiredOrientation(keyboard) != null) {
                desiredOrientation = this.getDesiredOrientation(keyboard);
            }

            if (!this.isDisplacementOccurs()) {
                if (desiredOrientation != getOrientation() && this.getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))) {
                    //System.out.println(this.getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector()))));
                    this.orientate(desiredOrientation);
                    currentAnimation = animations[desiredOrientation.ordinal()];
                }
                this.move(SPEED);
            }
            if (isDisplacementOccurs()) {
                currentAnimation.update(deltaTime);
                if (startOfSound % 0.7 < 0.05) {
                    pacPac.shouldBeStarted();
                }
                startOfSound = startOfSound > 0.8 ? 0 : startOfSound;
            } else {
                currentAnimation.reset();
            }

            if (isInvincible == true) {
                timer -= deltaTime;
                if (timer < 0) {
                    isInvincible = false;
                    timer = 10;
                }
            }

            if(getOwnerArea() instanceof Level2 && (getCurrentPosition().equals(new DiscreteCoordinates(14,0)) || getCurrentPosition().equals(new DiscreteCoordinates(15,0)))){
                ((SuperPacmanArea)getOwnerArea()).finish(true, score);
            }
        }
    }

    private void respawnPlayer(){

        this.leaveArea();
        if (getOwnerArea().getTitle() == "superpacman/Level1"){
            spawnPosition = new DiscreteCoordinates(15,6);
        }
        else if(getOwnerArea().getTitle() == "superpacman/Level2") {
            spawnPosition = new DiscreteCoordinates(15,29);
        }
        else if (getOwnerArea() instanceof MazeLevel){
            spawnPosition = ((MazeLevel)getOwnerArea()).getSpawnPoint();
        }
        this.enterArea(getOwnerArea(), spawnPosition);
    }
    public DiscreteCoordinates getCurrentPosition(){ return this.getCurrentMainCellCoordinates(); }

    private Orientation getDesiredOrientation(Keyboard keyboard){
        if(keyboard.get(buttonLEFT).isDown()){
            return Orientation.LEFT;
        }
        else if(keyboard.get(buttonRIGHT).isDown()){
            return Orientation.RIGHT;
        }
        else if(keyboard.get(buttonUP).isDown()){
            return Orientation.UP;
        }
        else if(keyboard.get(buttonDOWN).isDown()){
            return Orientation.DOWN;
        }
        return null;
    }

    public int getScore(){ return score;}

    public void increaseScore(int scorePoint){
        score += scorePoint;
    }

    public int getHealth(){ return hp;}

    public void changeButtons(){
        buttonDOWN = Keyboard.S;
        buttonUP = Keyboard.W;
        buttonLEFT = Keyboard.A;
        buttonRIGHT = Keyboard.D;
    }

    public void changeSprite(){
        sprites = RPGSprite.extractSprites ("superpacman/pacmanRed",4, 1, 1, this , 64, 64, new Orientation [] { Orientation.DOWN , Orientation.LEFT , Orientation.UP , Orientation.RIGHT });
        animations = Animation.createAnimations(SPEED/2, sprites );
    }

    public void adaptGUI(){
        status.adaptGUI();
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    public void passesDoor(Door door){      //Méthode dédiée au mode multijoueur
        setIsPassingADoor(door);
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
        ((GhostInteractionVisitor)v).interactWith(this );
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
        public void interactWith(SuperPacmanCollectableAreaEntity entity){
            entity.pickActor();
            increaseScore(entity.getPOINTS_GIVEN());
            if(entity instanceof Bonus){
                isInvincible = true;
                pacEatCoin.shouldBeStarted();
            } else if (entity instanceof Cherry){
                pacEatFruit.shouldBeStarted();
            } else if (entity instanceof Key){
                pacEatKey.shouldBeStarted();
            } else if (entity instanceof Diamond){
                ((SuperPacmanArea)getOwnerArea()).removeDiamond();
            }

        }

        public void interactWith(Ghost ghost) {
            if(isInvincible()){
                pacEatGhost.shouldBeStarted(); //Eat Sound
                ghost.setPositionRefuge();
                ghost.forgetPacman();
                increaseScore(ghost.getGHOST_SCORE()); //Si invicible, on mange le fantôme il nous rapporte donc des points
            }
            else{
                pacDeath.shouldBeStarted(); //Death Sound
                hp -= 1;
                ((SuperPacmanArea)(getOwnerArea())).BackToRefugeAndForget();  // Tous les fantômes reviennent à leur position refuge et oublient le Player.
                respawnPlayer();

                if(hp <= 0){
                    ((SuperPacmanArea)getOwnerArea()).finish(false, score);
                }
            }
        }
        @Override
        public void interactWith(Key key){

        }
    }

    @Override
    public void bip(Audio audio) {
        //pacPac.bip(audio);
        pacEatFruit.bip(audio);
        pacEatGhost.bip(audio);
        pacDeath.bip(audio);
        pacEatCoin.bip(audio);
        pacEatKey.bip(audio);
    }
}
