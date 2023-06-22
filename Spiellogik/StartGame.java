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
    }

    public void goLeft(){
        this.removeTet();
        this.tet.goLeft();
        this.setTet();
    }

    public void drop(){
        this.removeTet();
        this.tet.drop();
        this.setTet();
    }

    public void turn(){
        this.removeTet();
        this.tet.turn();
        this.setTet();
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
            //System.out.println(this);
        }

        for(int i = 0; i < 7; i++){
            this.goLeft();
            //System.out.println(this);
        }

        for(int i = 0; i < 4; i++){
            this.drop();
            //System.out.println(this);
        }
        for(int i = 0; i < 20; i++) {
            this.turn();
            System.out.println(this);
        }
    }
}
