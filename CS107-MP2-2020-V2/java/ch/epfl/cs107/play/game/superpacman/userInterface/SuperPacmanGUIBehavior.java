package ch.epfl.cs107.play.game.superpacman.userInterface;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanGUIBehavior extends AreaBehavior {
    /**
     * Default AreaBehavior Constructor
     *
     * @param window (Window): graphic context, not null
     * @param name   (String): name of the behavior image, not null
     */
    public SuperPacmanGUIBehavior(Window window, String name) {
        super(window, name);
    }
}
