package Spiellogik;
/**
 * Smashboy  implements interface Tetromino
 * @version 06.07.2023
 * @author Tim Schwetje
 */
public class SmashBoy implements Tetromino {
    private Coords[] coords; /**variable for position of stone*/
    private boolean on_ground;  /**variable to check if stone on ground*/
    private int turn_counter;  /**variable needed for proper turn*/

    /**
     * Constructor method sets variables above and coordinates at start
     * @version 06.07.2023
     */
    public SmashBoy(){
        this.coords = new Coords[4];
        this.on_ground = false;
        this.turn_counter = 0;

        this.coords[0] = new Coords(0,6);
        this.coords[1] = new Coords(0,5);
        this.coords[2] = new Coords(-1,5);
        this.coords[3] = new Coords(-1,6);
    }

    public void setOn_ground(boolean a){
        this.on_ground = a;
    }
    public boolean getOn_ground() {
        return this.on_ground;
    }
    public Coords[] getCoords(){
        return this.coords;
    }
    public void goRight(){
        this.defaultGoRight(this.coords);
    }
    public void goLeft(){
        this.defaultGoLeft(this.coords);
    }

    /**
     * Turn method implemented using a turn counter to determine next position
     * Smashboy's next position is equal to its prior position
     * @param gameboard changes going to be visible on board
     * @version 06.07.2023
     */
    public void turn(BoardStatus[][] gameboard){
        switch(this.turn_counter % 4){
            default:
                return;
        }
    }

    /**
     * method calls defaultDrop function when called
     * causes block to fall
     * @version 06.07.2023
     */
    public void drop(){
        this.defaultDrop(this.coords);
    }

    /**
     * Method forbids block to turn outside of gameboard or into other blocks set
     * @param x: needs to be within -2 and 14 which is gameboard's vertical size
     * @param y: needs to be within 0 and 10 which is gameboard's horizontal size
     * @param gameboard: gameboard to be checked
     * @return true if block at position x and y is Empty and can be transitioned to
     */
    public boolean CheckOutOfBounds (int x , int y, BoardStatus[][] gameboard) {
        if(!(x >= -2 && x < 14 && y >= 0 && y < 10 )) return false;

        if(gameboard[x][y] == BoardStatus.SET) return false;

        return true;
    }
}
