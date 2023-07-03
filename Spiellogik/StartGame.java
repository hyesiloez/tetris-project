public class StartGame {
    private BoardStatus[][] gameboard;
    private boolean isGameOver;
    private Tetromino tet;

    public StartGame(int length, int height){
        this.gameboard = new BoardStatus[height][length];
        isGameOver = false;
        tet = new Hero();

        for(int i = 0; i < gameboard.length; i++){
            for (int j = 0; j < gameboard[i].length; j++){
                gameboard[i][j] = BoardStatus.AIR;
            }
        }
        this.setTet();
    }

    public void removeTet (){
        Coords [] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++) {
            if (coords[i].getX() >= 0 && coords[i].getX() <= this.gameboard.length) {
                if (coords[i].getY() >= 0 && coords[i].getY() <= this.gameboard[0].length) {
                    gameboard[coords[i].getX()][coords[i].getY()] = BoardStatus.AIR;
                }
            }
        }
    }

    public void setTet (){
        Coords [] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++){
            if(coords[i].getX() >= 0 && coords[i].getX() <= this.gameboard.length){
                if (coords[i].getY() >= 0 && coords[i].getY() <= this.gameboard[0].length){
                    gameboard[coords[i].getX()][coords[i].getY()] = BoardStatus.PLAYER;
                }
            }
        }
    }

    public void goRight(){
        this.removeTet();
        this.tet.goRight();
        this.setTet();
        this.isOnGround();
    }

    public void goLeft(){
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

    public void turn(){
        this.removeTet();
        this.tet.turn();
        this.setTet();
        this.isOnGround();
    }


    public String toString(){
        String end = "";
        for(int i = 0; i < this.gameboard.length; i++){
            for (int j = 0; j < this.gameboard[i].length; j++){
                if(this.gameboard[i][j].getIsSet()){
                    end += "1";
                } else {
                    end += "0";
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

    }

    public void play(){
        System.out.println(this);
        this.goRight();
        System.out.println(this);
        this.goLeft();
        System.out.println(this);
        this.drop();
        System.out.println(this);

        for(int i = 0; i < 15; i++){
            this.goRight();
            System.out.println(this);
        }



        for(int i = 0; i < 3; i++){
            this.goLeft();
            System.out.println(this);
        }

        for(int i = 0; i < 4; i++){
            this.drop();
            System.out.println(this);
        }
        for(int i = 0; i < 20; i++) {
            this.turn();
            System.out.println(this);
        }

        for (int i = 0; i < 6;i++){
            while (!this.tet.getOn_ground()) {
                this.drop();
                System.out.println(this);
            }
            this.tet = new Hero();
        }
    }

}
