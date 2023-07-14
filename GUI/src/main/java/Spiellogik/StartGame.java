package Spiellogik;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;

/**
 * Startgame initializes and prints gameboard, spawns random blocks when set and consists of main logic
 * @version 07.07.2023
 * @author Erik Brinker, Halil Yesiloez, Tim Schwetje
 */
public class StartGame {
    public BoardStatus[][] gameboard; /**variable for gameboard*/
    public boolean isGameOver; /** boolean to check for when game is over*/
    private Tetromino tet; /** tetromino variable */
    //private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Tetromino next_tet; /** variable for the next tet*/
    private int points; /**points variable for blocks set */
    public boolean isTetOnGround = false;  /**variable to tell if tet is on ground*/
    public boolean isFullRowPoints = false; /**variable for points received when Tetris*/

    /**
     * constructor which sets length and height of gameboard  and fills every gameboard position with AIR
     * current and next tetromino are created randomly by calling randomTet() function
     * @param length : integer which represents the horizontal size of gameboard
     * @param height : integer which represents the vertical size of gameboard
     */
    public StartGame(int length, int height){
        this.gameboard = new BoardStatus[height][length];
        this.isGameOver = false;
        this.tet = randomTet();
        this.next_tet = randomTet();
        this.points = 0;
        for(int i = 0; i < gameboard.length; i++){
            for (int j = 0; j < gameboard[i].length; j++){
                gameboard[i][j] = BoardStatus.AIR;
            }
        }
        this.setTet();
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public Tetromino getNext_tet(){
        return this.next_tet;
    }

    public Tetromino getTet(){
        return this.tet;
    }

    /**
     * This method updates the current Tet to tetromino a
     * @param a this.tet is now this Tetromino
     */
    public void changeTet(Tetromino a){
        this.tet = a;
    }
    /**
     * This method updates the next Tet to Tetromino a
     * @param a this.tet is now this Tetromino
     */
    public void changenext_tet(Tetromino a){
        this.next_tet = a;
    }

    public boolean getIsGameOver(){
        return this.isGameOver;
    }

    /**
     * Method returns a new Tetromino randomly chosen by a random integer.
     * @version 06.07.2023
     * @return new random Tetromino
     */
    public Tetromino randomTet(){
        Random rand = new Random();
        int random_int = rand.nextInt(7);
        switch (random_int){
            case 0:
                return new TeeWee();
            case 1:
                return new BRicky();
            case 2:
                return new Hero();
            case 3:
                return new OrangeRicky();
            case 4:
                return new RhodeIslandZ();
            case 5:
                return new SmashBoy();
            case 6:
                return new CleveLandZ();
            default:
                return new Hero();
        }
    }

    /**
     * This method removes the Tetromino by replacing the Boardgame position with AIR
     * Done to illustrate a smooth movement of the stones, it is vital to replace the former position of the stones
     */
    public void removeTet (){
        Coords [] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++) {
            if (coords[i].getX() >= 0 && coords[i].getX() < this.gameboard.length) {
                if (coords[i].getY() >= 0 && coords[i].getY() < this.gameboard[0].length) {
                    gameboard[coords[i].getX()][coords[i].getY()] = BoardStatus.AIR;
                }
            }
        }
    }

    /**
     * This method set the Tetromino at its new position
     * Done to illustrate a smooth movement of the stones, it is vital to replace the former position of the stones
     */
    public void setTet (){
        Coords [] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++){
            if(coords[i].getX() >= 0 && coords[i].getX() < this.gameboard.length){
                if (coords[i].getY() >= 0 && coords[i].getY() < this.gameboard[0].length){
                    gameboard[coords[i].getX()][coords[i].getY()] = BoardStatus.PLAYER;
                }
            }
        }
    }

    /**
     * This method realizes the movement of the stone in  right direction
     * Its using remove- and setTet  function to realize the transition and checks if the stone is on ground at the end
     * @version 07.07.2023
     */
    public void goRight(){
        Coords[] a = this.tet.getCoords();
        for(int i = 0; i < 4; i++){
            if(a[i].getY() == 9){
                return;
            } else if(a[i].getX() >=  0 && this.gameboard[a[i].getX()][a[i].getY() + 1] == BoardStatus.SET ){
                return;
            }
        }
        this.removeTet();
        this.tet.goRight();
        this.setTet();
        this.isOnGround();
    }

    /**
     * This method realizes the movement of the stone in  left direction
     * Its using remove- and setTet  function to realize the transition and checks if the stone is on ground at the end
     * @version 07.07.2023
     */
    public void goLeft(){
        Coords[] a = this.tet.getCoords();
        for(int i = 0; i < 4; i++){
            if(a[i].getY() == 0){
                return;
            } else if(a[i].getX() >=  0 && this.gameboard[a[i].getX()][a[i].getY() - 1] == BoardStatus.SET ){
                return;
            }
        }

        this.removeTet();
        this.tet.goLeft();
        this.setTet();
        this.isOnGround();
    }

    /**
     * Drop method calls drop method of tetromino by checking if next position free to transition to
     * Its using remove- and setTet  to do so
     * @version 07.07.2023
     */
    public void drop(){
        Coords[] coords = this.tet.getCoords();

        for (int i = 0; i < coords.length; i++){
            if(coords[i].getX() == 13 || gameboard[coords[i].getX()+1][coords[i].getY()] == BoardStatus.SET){
                return;
            }
        }
        this.removeTet();
        this.tet.drop();
        this.setTet();
        this.isOnGround();
    }

    /**
     * turn method calls turn method of tetromino
     * Its using remove- and setTet to do so
     * @version 07.07.2023
     */
    public void turn( BoardStatus[][] gameboard){
        this.removeTet();
        this.tet.turn(gameboard);
        this.setTet();
        this.isOnGround();
    }

    /*public void tetFall(StartGame a){
        scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(new Runnable(){
            public void run() {

                if (!a.tet.getOn_ground()) {
                    a.drop();
                    System.out.println(a);
                } else if (!a.isGameOver){
                    a.tet = next_tet;
                    a.setTet();
                    System.out.println(a);
                    a.next_tet = randomTet();
                } else {
                    //scheduler.shutdown();
                    System.out.println("GAME OVER");
                }

            }
        }, 0, 2000, TimeUnit.MILLISECONDS);

    }*/

    /**
     * realizes the gameboard ín Terminal by assigning "0" to Air, "1" to set blocks and "2" to current Player
     * @return String which represents the gameboard in string version
     */
    public String toString(){
        String end = "";
        for(int i = 0; i < this.gameboard.length; i++){
            for (int j = 0; j < this.gameboard[i].length; j++){
                if(this.gameboard[i][j] == BoardStatus.SET){
                    end += "1";
                } else if (this.gameboard[i][j] == BoardStatus.AIR){
                    end += "0";
                } else {
                    end += "2";
                }
            }
            end += "\n";
        }
        return end;
    }

    /**
     * Method checks if a given row is full
     * @param  row: int to check the row where the Tetromino is placed, migth consider multiple rows depending on Tetromino
     * @return boolean: true if row full, false if not
     * @version 02.07.2023
     * */
    public boolean FullRow (int row) {
        //Coords[] coords = this.tet.getCoords();

        for(int i = 0; i < this.gameboard[row].length; i++) {
            if(this.gameboard[row][i] == BoardStatus.AIR ||this.gameboard[row][i] == BoardStatus.PLAYER) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method removes the row if it's full by dragging every row above 1 row down
     * row 0 has to be manually changed cause there's no row above to be dragged. Row 0 is always empty if TETRIS happens.
     * @param row:  integer to check the row where the Tetromino is placed, migth consider multiple rows depending on Tetromino
     * @return void
     * @version 02.07.2023
     * */
    public void removeRow(int row ) {
        if(this.FullRow(row))  {
            this.points += 100;
            for(int i = row; i > 0; i--) {
                for(int j = 0; j < this.gameboard[row].length; j++) {
                    this.gameboard[i][j] = this.gameboard[i - 1] [j];
                }
            }

            for( int k = 0; k < this.gameboard[0].length;k++) {
                this.gameboard[0][k] = BoardStatus.AIR;
            }
        }

    }

    /**
     * method updates board by shifting the values by one row
     * Adds a new row to the bottom with a random cell set to AIR the rest filled with Set
     * @version 07.07.2023
     */
    public void addRow(){
        Random rand = new Random();
        int random_int = rand.nextInt(10);
        for(int i = 0; i < this.gameboard.length - 1; i++ ){
            for (int j = 0; j < this.gameboard[i].length; j++){
                if(this.gameboard[i+1][j] != BoardStatus.PLAYER && this.gameboard[i][j] != BoardStatus.PLAYER){
                    this.gameboard[i][j] = this.gameboard[i+1][j];
                }

            }
        }
        for (int k = 0; k < this.gameboard[13].length; k++){
            if ( random_int != k){
                this.gameboard[13][k] = BoardStatus.SET;
            } else {
                this.gameboard[13][k] = BoardStatus.AIR;
            }
        }
        this.isOnGround();

    }

    /**
     * method checks if tetromino is on ground either when x is  row 13 or the s position below is SET
     * if on ground its increasing points by 10
     * @version 07.07.2023
     */
    public void isOnGround(){
        Coords[] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++){
            if (coords[i].getX() == 13 || this.gameboard[coords[i].getX() + 1][coords[i].getY()] == BoardStatus.SET){
                this.points += 10;
                this.tet.setOn_ground(true);
                this.tetOnGround();
                this.isTetOnGround = true;
                return;
            }
        }
    }

    /**
     * Sets current Tetromino and checks if this set causes a Tetris
     * calls game over method to check if Set causes the game to stop
     * @version 07.07.2023
     */
    public void tetOnGround(){
        Coords[] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++) {
            this.gameboard[coords[i].getX()][coords[i].getY()] = BoardStatus.SET;
        }

        for (int j = 0; j < gameboard.length; j++){
            if (FullRow(j)){
                removeRow(j);
                this.isFullRowPoints = false;
            }
        }
        this.isGameOver();

    }

    /**
     * if row 1 is set by any stone it's game over
     * Done by iterating through every block in row 1
     * @version 07.07.2023
     */
    public void isGameOver(){
        for(int i = 0; i < this.gameboard[0].length; i++){
            if(this.gameboard[1][i] == BoardStatus.SET){
                this.isGameOver = true;
                return;
            }
        }
    }
    public void play(){
        /*System.out.println(this);
        //tetFall(this);
        while(!this.isGameOver){
            if(!this.isGameOver){
                break;
            }
        }*/
    }

    /**
     * Method converts gameborad to a boolean array by checking the whole field
     * cell is placed with true if SET or PLAYER, false if AIR
     * @return boolean[][]
     * @version 07.07.2023
     */
    public boolean[][] convertToBooleanArray() {
        boolean[][] res = new boolean[14][10];
        for (int i = 0; i < this.gameboard.length; i++) {
            for (int j = 0; j < this.gameboard[i].length; j++) {
                if (this.gameboard[i][j] == BoardStatus.AIR) {
                    res[i][j] = false;
                } else if (this.gameboard[i][j] == BoardStatus.SET){
                    res[i][j] = true;
                } else if(this.gameboard[i][j] == BoardStatus.PLAYER){
                    res[i][j] = true;
                }
            }
        }
        return res;
    }


}
