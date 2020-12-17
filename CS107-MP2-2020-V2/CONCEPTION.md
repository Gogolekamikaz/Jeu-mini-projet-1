# CONCEPTION File - SuperPacman

## Part I : Basis of SuperPacman

We logically begin the programming of the project by creating all the basic classes of a surface game as described in the statement : 

In the Superpacman package, there firstly are : 

- SuperPacmanPlayer (sub-package **Actor**)
- Level0,  Level1, Level2, SuperPacmanArea, SuperpacmanBehavior (sub package **Area**)
- SuperPacman (sub-package **SuperPacman**)

The choice of these classes location is justified by their nature, classes related to the playground go to the package related to the playground and the same applies to Pacman actor class. Finally, SuperPacman being the class in which the game is strictly speaking developed, putting it apart from other classes in the root of SuperPacman package seems the best choice.

## Part II : Item Collection

This part mainly focuses on new actors creation, they will be our items the player can freely collect. Collecting these items are done by interacting with them, in order to handle it, a specific file has also be made. 

Classes are : 
- Bonus ( sub-package **Actor**, deriving from *SuperPacmanCollectableAreaEntity*)
- Cherry ( sub-package **Actor**, deriving from *SuperPacmanCollectableAreaEntity*)
- Diamond ( sub-package **Actor**, deriving from *SuperPacmanCollectableAreaEntity*)
- Gate ( sub-package **Actor**, deriving from *AreaEntity*)
- Key ( sub-package **Actor**, deriving from *SuperPacmanCollectableAreaEntity*)
- SuperPacmanInteractionVisitor (sub-package **handler** deriving from *Handler*)

Since collectable entities have very similar behaviors, it totally made sense to factorize it in a super class *SuperPacmanCollectableAreaEntity*. It makes the process of interaction easier and prevents code from being redundant.

However, *SuperPacmanCollectableAreaEntity* doesn't do all the work itself, it simply allows us to create a communication bridge between *SuperPacmanInteractionVisitor* and *CollectableAreaEntity*, it's a sort of adaptation.

Unlike every other actors created here, Gate is the only non collectable one. Why ? Have you ever tried to collect your home front door ? 

Finally, the last class which need to be discussed here is *SuperPacmanPlayerStatusGUI* (implementing *Graphics*). Creating a graphical interface of this sort is needed in order to print to the screen the HP bar & the score.

## Part 3 : Ghosts

This part consists of creating the three, both interactable and interactor, ghost entities : Blinky, Inky and Pinky.
Since these actors obviously have common properties, they all derive from an abstract ghost class which put them in common.
As it has been previously said, ghosts are both interactable and interactor, It justifies the fact that ghost derives from **MoveableAreaEntity** and implements *Interactor* interface.

Concerning Pinky and Inky, they obviously are ghost, however, making them deriving from ghost might be a bad idea. In fact, they differ from Blinky by their agressive abilities, they have a different player interaction approach. Willing to avoid as much as possible code redundance, creating a sub-class deriving from ghost, linking Inky & Pinky was therefore an important element to consider implementing.
Summing up what we wrote earlier, here are part 3 classes : 
- Blinky (sub-package **Actor**, deriving from Ghost)
- Inky (sub-package **Actor**, deriving from *AgressiveGhost*)
- Pinky (sub-package **Actor**, deriving from *AgressiveGhost*)
- AgressiveGhost (sub-package **Actor**, deriving from *Ghost*)

The last class we didn't mention here is the one allowing ghosts to interact with Pacman, it is coded in the same way as *SuperPacmanInteractionVisitor* and is : *GhostInteractionVisitor*

## Extends

Let's now dig on classes made for . We'll divide this part into sub-parts,  each related to an extension.

## Multiplayer

This multiplayer extension allows two differents players to both control a pacman through differents binds and to play together. The two players are allies and have the same goal : earning the most score points and stay alive. In order to do so, they each have their unique score and life bar.

The only new class in this extension is *InvisibleMadeForViewCandidate*. Even though this class could seem quite uncasual by its name, it's actually quite useful for multiplayer mode. In fact, one important problem that we faced while implementing cooperation game was the camera settings. Should we make the camera focusing on Player1, Player2 ? How can we see the two players at the same time on the same screen ? 
A method we use to answer these questions is to take advantage of the setViewCandidate() methode available through *Area*. 
However, we chose neither to setViewCandidate on Player1 or Player2 but to set it at the point representing the middle between the 2 players. Since setViewCandidate can only be applied on Area Enties, we had to create an insivible actor on which the setViewCandidate() could be set at each update. It's exactly the purpose of the class *InvisibleMadeForViewCandidate*.

Even though there's only one new class for this extension, the main action takes place in *SuperPacman* class. Each updates is checking if the multiplayer starting button (Space) is pressed, and starts the cooperation accordingly.
When multiplayer started, *SuperPacman* a new player and call different methods in order to make him dinstinguishable. We chose to adapt Player2 through methods and not directly through SuperPacmanPlayer constructor since we wanted this Multiplayer extension to cause as less impact as possible on main game.

## Auto Generated Maze

Auto Generated Maze extension allows the game user to not get bored with only 3 differents level, possibilities are now Infinite ! 
Two classes make this extension working : *MazeLevel* & *SuperPacmanAutoGeneratedMazeBehavior*. 
As you may now imagine, theses classes are mimicking the actual game properties. 

*MazeLevel* is the equivalent of Level0, Level1 and Level2 for infinite game. Pacman player spawns at his spawning position, doors and gates are instanced, it's overall quite equivalent to Area Levels we just mentionned earlier. 

*SuperPacmanAutoGeneratedMazeBehavior* is the class in which this extension core is.
Instead of generating a new coloured Behavior map for each new generated Level, we prefered to use an other method : generating everything from ourselves. 
Therefore, this class has the role to randomly initialize everything, from walls, passing by collectable area entities to ghost.


## Home menu
The menu is where our game begins. It gives the game user several choices to play his game : Arcade (Classic Gameplay), Infinite (Auto Generated Maze feature) and Level Choosing. To print correctly on screen, home menu is both relying on *AreaEntity* & *Graphics* interface.
Classes related to home menu are in **userInterface** package.
## Pause Screen & End Game screen

Player can pause the game at any time by pressing the key 'P' and an end game screen appears when pacman either loses or win the game.
It's done the same way as the home menu.

## Sounds 
We implemented genuine Pacman game sounds to the game.
Taking advantage of SuperPacmanPlayer update, we could throw a sound each time player eats a Cherry, gets a Bonus, eats Ghost while being invincible or also when the opposite event occurs.

