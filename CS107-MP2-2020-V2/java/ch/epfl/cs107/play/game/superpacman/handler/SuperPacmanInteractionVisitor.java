package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {

    /**
     * Simulate and interaction between RPG Interactor and a SuperPacmanPlayer
     * @param player (SuperPacmanPlayer), not null
     */
    default void interactWith(SuperPacmanPlayer player){

    }



}
