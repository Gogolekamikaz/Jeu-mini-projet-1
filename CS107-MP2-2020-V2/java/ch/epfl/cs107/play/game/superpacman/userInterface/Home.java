package ch.epfl.cs107.play.game.superpacman.userInterface;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Home extends SuperPacmanGUI {

    private Background background;
    private GraphicButton playButton;

    @Override
    public String getTitle() {
        return "superpacman/Home";
    }


    @Override
    protected void createDisplay() {

        background = new Background("Accueil", getWindowWidth(), getWindowHeight(), null);
        registerActor(background);

        playButton = new GraphicButton("play",this, new DiscreteCoordinates(1,1), "Play");
        registerActor(playButton);

    }
}
