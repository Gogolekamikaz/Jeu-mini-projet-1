package ch.epfl.cs107.play.game.superpacman.userInterface;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanGUIBehavior extends AreaBehavior {
    /**
     * Default AreaBehavior Constructor
     *
     * @param window (Window): graphic context, not null
     */
    public SuperPacmanGUIBehavior(Window window) {
        super(window, window.getWidth(), window.getHeight());
    }
}
