package Spiellogik;

/**
 * Tetromino interface which every block implements
 * handles basic movement, drop and turn
 * @version 06.07.2023
 * @author Erik Brinker
 * @author Halil Yesiloez
 */
public interface Tetromino {

    /**
     * Method  guarantees that Block won't walk outside of gameboard when going in right direction
     * @version 06.07.2023
     * @param coords: position of the block
     */
    default void defaultGoRight(Coords[] coords ){
        for (int j = 0; j < coords.length; j++){
            if(coords[j].getY() == 9){
                return;
            }
        }

        for (int i = 0; i < coords.length; i++){
            coords[i].setY(coords[i].getY() + 1);
        }
    }

    public void goRight(); /**goRight function to be implemented*/

    /**
     * Method  guarantees that Block won't walk outside of gameboard when going left
     * @version 06.07.2023
     * @param coords: position of the block
     */
    default void defaultGoLeft(Coords[] coords){
        for (int j = 0; j < coords.length; j++){
            if(coords[j].getY() == 0){
                return; //Nicht außerhalb des Spielfeldes laufen
            }
        }

        for (int i = 0; i < coords.length; i++){
            coords[i].setY(coords[i].getY() - 1);
        }
    }
    public void goLeft(); /**goLeft function to be implemented*/



    public void turn(BoardStatus[][] gameboard); /**turn function to be implemented*/

    /**
     * Method lets block drop
     * Increasing X-coordinate in for loop
     * @version 06.07.2023
     * @param coords: position of the block
     */
    default void defaultDrop(Coords[] coords){
        for (int i = 0; i < coords.length; i++){
            coords[i].setX(coords[i].getX() + 1);
        }
    }
    public void drop(); /**drop function to be implemented*/
    public Coords[] getCoords();

    public void setOn_ground(boolean a);

    public boolean getOn_ground();

    public boolean CheckOutOfBounds (int x, int y, BoardStatus[][] gameboard);  /**CheckOutOfBounds function to be implemented*/


}
