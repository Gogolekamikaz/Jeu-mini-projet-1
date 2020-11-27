package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior {
    public enum SuperPacmanCellType {

        //https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
        NONE(0, true), // never used as real content
        WALL ( -16777216,true),       //black
        FREE_WITH_DIAMOND(-1,true),         //white
        FREE_WITH_BLINKY (-65536,true),     //red
        FREE_WITH_PINKY ( -157237,true),    //pink
        FREE_WITH_INKY ( -16724737,true),   //cyan
        FREE_WITH_CHERRY (-36752,true),      //light red
        FREE_WITH_BONUS ( -16478723,true), //light blue
        FREE_EMPTY ( -6118750,true);         // sort of gray
        ;

        final int type;
        final boolean isWalkable;

        SuperPacmanCellType(int type, boolean isWalkable) {
            this.type = type;
            this.isWalkable = isWalkable;
        }

        public static SuperPacmanCellType toType(int type) {
            for (SuperPacmanCellType ict : SuperPacmanCellType.values()) {
                if (ict.type == type)
                    return ict;
            }
            // When you add a new color, you can print the int value here before assign it to a type
            System.out.println(type);
            return NONE;

        }
    }

    protected void registerActors(Area area){
        Wall wallActor;
        DiscreteCoordinates coordinates;
        boolean[][] neighborhood;
        int height = getHeight();
        int width = getWidth();
        for(int x = 0; x < width; ++x )
        {
            for(int y = 0; y < height; ++y){
                SuperPacmanCellType color = SuperPacmanCellType.toType(getRGB(height-1-y, x));
                if(color == SuperPacmanCellType.WALL){
                    coordinates = new DiscreteCoordinates(x,y);
                    wallActor = new Wall(area,coordinates,getWallNeighborhood(x,y));
                    area.registerActor(wallActor);
                }
            }
        }
    }

    private boolean[][] getWallNeighborhood(int x, int y){
        boolean[][] neigborhood = new boolean[3][3];
        for(int xcord = x-1, i = 0; xcord <= x+1; ++xcord , ++i)
        {
            for(int ycord = y-1, j = 0; ycord <= y+1; ++ycord, ++j){
                SuperPacmanCellType color = SuperPacmanCellType.toType(getRGB(getHeight()-1-ycord, xcord));
                if(color == SuperPacmanCellType.WALL){
                    neigborhood[i][j] = true;
                }
                else{
                    neigborhood[i][j] = false;
                }
            }
        }
        return neigborhood;
    }

    /**
     * Default AreaBehavior Constructor
     *
     * @param window (Window): graphic context, not null
     * @param name   (String): name of the behavior image, not null
     */
    public SuperPacmanBehavior(Window window, String name) {
        super(window, name);


        // - Code ci-dessous probablement inutile, à vérifier.
        int height = getHeight();
        int width = getWidth();
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width ; x++) {
                SuperPacmanCellType color = SuperPacmanCellType.toType(getRGB(height-1-y, x));
                setCell(x,y, new SuperPacmanCell(x,y,color));
            }
        }

    }

    public class SuperPacmanCell extends AreaBehavior.Cell{

        private final SuperPacmanCellType type;

        /**
         * Default Cell constructor
         *  @param x (int): x-coordinate of this cell
         * @param y (int): y-coordinate of this cell
         * @param type
         */
        protected SuperPacmanCell(int x, int y, SuperPacmanCellType type) {
            super(x, y);
            this.type = type;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return false;
        }

        @Override
        protected boolean canEnter(Interactable entity) {   //If cell already has an entity occupying all the cell space, it's not traversable
            if(this.hasNonTraversableContent()){
                return false;
            }
            else{
                return true;
            }
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
    }


}
