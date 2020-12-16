package ch.epfl.cs107.play.game.superpacman.userInterface;

import ch.epfl.cs107.play.game.actor.SoundAcoustics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Audio;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphicButton extends AreaEntity {

    private final String destination;
    private Sprite sprite;
    private Sprite spriteHoover;
    private TextGraphics buttonName;
    private Vector textCoordinates;

    private final SoundAcoustics click = new SoundAcoustics("sounds/minecraft_click.wav");
    private boolean clicked;

    private final float width = 8.f;
    private final float height = 2.f;

    public GraphicButton(String buttonName, Area area, DiscreteCoordinates coordinates, String destination, Vector textCoordinates){
        super(area, Orientation.UP, coordinates);
        this.textCoordinates = textCoordinates;
        sprite = new Sprite("superpacman/button", width, height, this, new RegionOfInterest(0,0, 720, 308));
        sprite.setDepth(15);
        spriteHoover = new Sprite("superpacman/button", width, height, this, new RegionOfInterest(0,308, 720, 308));
        spriteHoover.setDepth(15);
        this.destination = destination;
        this.buttonName = new TextGraphics(buttonName, 1.f, Color.WHITE);
        this.buttonName.setDepth(16);
        this.buttonName.setBold(true);
        this.buttonName.setParent(this);
        this.buttonName.setAnchor(new Vector(textCoordinates.x, textCoordinates.y));
        clicked = false;
    }


    @Override
    public void draw(Canvas canvas) {
        if(isMouseOver()){
            spriteHoover.draw(canvas);
            this.buttonName.setAnchor(new Vector(textCoordinates.x, textCoordinates.y-0.2f));
        } else {
            sprite.draw(canvas);
            this.buttonName.setAnchor(new Vector(textCoordinates.x, textCoordinates.y));
        }
        buttonName.draw(canvas);


    }

    @Override
    public void update(float deltaTime) {
        if(isMouseOver() && !clicked && getOwnerArea().getMouse().getLeftButton().isPressed()){
            clicked = true;
            click.shouldBeStarted();
            ((SuperPacmanGUIWindow)getOwnerArea()).switchArea(destination);
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        List<DiscreteCoordinates> cells = new ArrayList<>();
        for(int x = 0; x<width; x++){
            for (int y=0; y<height; y++){
                cells.add(getCurrentMainCellCoordinates().jump(new Vector(x,y)));
            }
        }
        return cells;
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
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
    }

    @Override
    public void bip(Audio audio) {
        click.bip(audio);
    }
}
