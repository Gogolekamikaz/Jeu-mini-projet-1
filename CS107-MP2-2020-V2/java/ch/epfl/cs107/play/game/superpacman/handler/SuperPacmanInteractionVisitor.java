package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.*;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {

    /**
     * Simulate and interaction between RPG Interactor and a SuperPacmanPlayer
     * @param player (SuperPacmanPlayer), not null
     */
    default void interactWith(SuperPacmanPlayer player){
    }

    /**
     * Simulate and interaction between RPG Interactor and a Bonus (coin)
     * @param bonus (Bonus), not null
     */
    default void interactWith(Bonus bonus){
    }

    /**
     * Simulate and interaction between RPG Interactor and a Cherry
     * @param cherry (Cherry), not null
     */
    default void interactWith(Cherry cherry){
    }

    /**
     * Simulate and interaction between RPG Interactor and a Diamond
     * @param diamond (Diamond), not null
     */
    default void interactWith(Diamond diamond){
    }

    /**
     * Simulate and interaction between RPG Interactor and a CollectableAreaEntity
     * @param entity (CollectableAreaEntity), not null
     */
    default void interactWith(CollectableAreaEntity entity){
    }

    /**
     * Simulate and interaction between RPG Interactor and a Key
     * @param key (Key), not null
     */
    default void interactWith(Key key){
    }

}
