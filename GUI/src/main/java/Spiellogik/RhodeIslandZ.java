package Spiellogik;
/**
 * RhodeIslandZ implements interface Tetromino
 * @version 06.07.2023
 * @author Halil Yesiloez
 */
public class RhodeIslandZ implements Tetromino {
    private final Coords[] coords;/**variable for position of stone*/
    private boolean on_ground;/**variable to check if stone on ground*/
    private int turn_counter;/**variable needed for proper turn*/
    /**
     * Constructor method sets variables above and coordinates at start
     * @version 06.07.2023
     */
    public RhodeIslandZ (){
        this.coords = new Coords[4];
        this.on_ground = false;
        this.turn_counter = 0;

        this.coords[0] = new Coords(-1,5);
        this.coords[1] = new Coords(-1,6);
        this.coords[2] = new Coords(0,4);
        this.coords[3] = new Coords(0,5);
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
     * Method forbids block to turn outside of gameboard or into other blocks set
     * @param x: needs to be within -2 and 14 which is gameboard's vertical size
     * @param y: needs to be within 0 and 10 which is gameboard's horizontal size
     * @param gameboard: gameboard to be checked
     * @return true if block at position x and y is Empty and can be transitioned to
     */
    public boolean CheckOutOfBounds (int x , int y, BoardStatus[][] gameboard) {
        if(!(x >= -2 && x < 14 && y >= 0 && y < 10 )) return false;
        return true;
    }
//Tim war hier
    public boolean CheckForCollision(int x, int y, BoardStatus[][] gameboard){
        if(x >= 0 && x < 14 && y >= 0 && y < 10){
            if(gameboard[x][y] == BoardStatus.SET) return false;
        }
        return true;
    }
    /**
     * Turn method implemented using a turn counter to determine next position
     * @param gameboard changes going to be visible on board
     * @version 06.07.2023
     */
    public void turn(BoardStatus[][] gameboard) {
        switch (turn_counter % 4) {
            case 0:
                if (    CheckOutOfBounds(coords[0].getX() + 1, coords[0].getY() + 1, gameboard) &&
                        CheckOutOfBounds(coords[1].getX() + 2  ,coords[1].getY(), gameboard) &&
                        CheckOutOfBounds(coords[2].getX() - 1, coords[2].getY() + 1, gameboard) &&
                        CheckForCollision(coords[0].getX() + 1, coords[0].getY() + 1, gameboard) &&
                        CheckForCollision(coords[1].getX() + 2  ,coords[1].getY(), gameboard) &&
                        CheckForCollision(coords[2].getX() - 1, coords[2].getY() + 1, gameboard)) {

                        coords[0].setX(coords[0].getX() + 1);
                        coords[0].setY(coords[0].getY() + 1);
                        coords[1].setX(coords[1].getX() + 2);
                        coords[2].setX(coords[2].getX() - 1);
                        coords[2].setY(coords[2].getY() + 1);
                        turn_counter++;

                }
                break;
            case 1:
                if(     CheckForCollision(coords[0].getX() + 1, coords[0].getY() -1, gameboard) &&
                        CheckForCollision(coords[1].getX(),coords[1].getY() - 2, gameboard ) &&
                        CheckForCollision(coords[2].getX() + 1, coords[2].getY() + 1, gameboard)) {

                    if (    CheckOutOfBounds(coords[0].getX() + 1, coords[0].getY() - 1, gameboard) &&
                            CheckOutOfBounds(coords[1].getX(), coords[1].getY() - 2, gameboard) &&
                            CheckOutOfBounds(coords[2].getX() + 1, coords[2].getY() + 1, gameboard)) {

                            coords[0].setX(coords[0].getX() + 1);
                            coords[0].setY(coords[0].getY() - 1);
                            coords[1].setY(coords[1].getY() - 2);
                            coords[2].setX(coords[2].getX() + 1);
                            coords[2].setY(coords[2].getY() + 1);
                            turn_counter++;
                    } else{
                            coords[0].setX(coords[0].getX() + 1);
                            coords[1].setY(coords[1].getY() - 1);
                            coords[2].setX(coords[2].getX() + 1);
                            coords[2].setY(coords[2].getY() + 2);
                            coords[3].setY(coords[3].getY() + 1);
                            turn_counter++;
                    }
                }
                break;
            case 2:
                if (    CheckOutOfBounds(coords[0].getX() - 1, coords[0].getY() - 1, gameboard) &&
                        CheckOutOfBounds(coords[1].getX() - 2  ,coords[1].getY(), gameboard) &&
                        CheckOutOfBounds(coords[2].getX() + 1, coords[2].getY() - 1, gameboard) &&
                        CheckForCollision(coords[0].getX() - 1, coords[0].getY() - 1, gameboard) &&
                        CheckForCollision(coords[1].getX() - 2,coords[1].getY(), gameboard) &&
                        CheckForCollision(coords[2].getX() + 1, coords[2].getY() - 1, gameboard)) {

                         coords[0].setX(coords[0].getX() - 1);
                         coords[0].setY(coords[0].getY() - 1);
                         coords[1].setX(coords[1].getX() - 2);
                         coords[2].setX(coords[2].getX() + 1);
                         coords[2].setY(coords[2].getY() - 1);
                         turn_counter++;

                }
                break;
            case 3:
                if(     CheckForCollision(coords[0].getX() - 1, coords[0].getY() + 1, gameboard) &&
                        CheckForCollision(coords[1].getX(),coords[1].getY() + 2, gameboard) &&
                        CheckForCollision(coords[2].getX() - 1, coords[2].getY() - 1, gameboard)) {

                    if (    CheckOutOfBounds(coords[0].getX() - 1, coords[0].getY() + 1, gameboard) &&
                            CheckOutOfBounds(coords[1].getX(), coords[1].getY() + 2, gameboard) &&
                            CheckOutOfBounds(coords[2].getX() - 1, coords[2].getY() - 1, gameboard)) {

                            coords[0].setX(coords[0].getX() - 1);
                            coords[0].setY(coords[0].getY() + 1);
                            coords[1].setY(coords[1].getY() + 2);
                            coords[2].setX(coords[2].getX() - 1);
                            coords[2].setY(coords[2].getY() - 1);
                            turn_counter++;
                    }else{
                            coords[0].setX(coords[0].getX() - 1);
                            coords[1].setY(coords[1].getY() + 1);
                            coords[2].setX(coords[2].getX() - 1);
                            coords[2].setY(coords[2].getY() - 2);
                            coords[3].setY(coords[3].getY() - 1);
                            turn_counter++;
                    }
                }
                break;
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



}