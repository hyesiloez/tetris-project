package Spiellogik;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class StartGame {
    public BoardStatus[][] gameboard;
    public boolean isGameOver;
    private Tetromino tet;
    //private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Tetromino next_tet;
    private int points;

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

    public Tetromino getNext_tet(){
        return this.next_tet;
    }

    public Tetromino getTet(){
        return this.tet;
    }
    public void changeTet(Tetromino a){
        this.tet = a;
    }
    public void changenext_tet(Tetromino a){
        this.next_tet = a;
    }

    public boolean getIsGameOver(){
        return this.isGameOver;
    }
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

    public void goRight(){
        Coords[] a = this.tet.getCoords();
        for(int i = 0; i < 4; i++){
            if(a[i].getY() == 9){
                return;
            } else if(this.gameboard[a[i].getX()][a[i].getY() + 1] == BoardStatus.SET ){
                return;
            }
        }
        this.removeTet();
        this.tet.goRight();
        this.setTet();
        this.isOnGround();
    }

    public void goLeft(){
        Coords[] a = this.tet.getCoords();
        for(int i = 0; i < 4; i++){
            if(a[i].getY() == 0){
                return;
            } else if(this.gameboard[a[i].getX()][a[i].getY() - 1] == BoardStatus.SET ){
                return;
            }
        }

        this.removeTet();
        this.tet.goLeft();
        this.setTet();
        this.isOnGround();
    }

    public void drop(){

        this.removeTet();
        this.tet.drop();
        this.setTet();
        this.isOnGround();
    }

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
     * @param int row: to check the row where the Tetromino is placed, migth consider multiple rows depending on Tetromino
     * @return boolean: true if row full, false if not
     * @version 02.07.2023
     * */
    public boolean FullRow (int row) {
        //Coords[] coords = this.tet.getCoords();

        for(int i = 0; i < this.gameboard[row].length; i++) {
            if(this.gameboard[row][i] == BoardStatus.AIR) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method removes the row if its full by dragging every row above 1 row down
     * row 0 has to be manually changed cause theres no row above to be dragged. Row 0 is always empty if TETRIS happens.
     * @param int row: to check the row where the Tetromino is placed, migth consider multiple rows depending on Tetromino
     * @return void
     * @version 02.07.2023
     * */
    public void removeRow(int row ) {
        if(this.FullRow(row))  {
            points += 40;
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
    public void isOnGround(){
        Coords[] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++){
            if (coords[i].getX() == 13 || this.gameboard[coords[i].getX() + 1][coords[i].getY()] == BoardStatus.SET){
                this.tet.setOn_ground(true);
                this.tetOnGround();
            }
        }
    }

    public void tetOnGround(){
        Coords[] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++){
            this.gameboard[coords[i].getX()][coords[i].getY()] = BoardStatus.SET;
        }
        this.isGameOver();

    }
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
    public boolean[][] convertToBooleanArray() {
        boolean[][] res = new boolean[14][10];
        for (int i = 0; i < this.gameboard.length; i++) {
            for (int j = 0; j < this.gameboard[i].length; j++) {
                if (this.gameboard[i][j] == BoardStatus.AIR) {
                    res[i][j] = false;
                } else {
                    res[i][j] = true;
                }
            }
        }
        return res;
    }


}
