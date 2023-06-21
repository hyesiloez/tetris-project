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
    }

    public void removeTet (){
        Coords [] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++){
            gameboard[coords[i].getX()][coords[i].getY()] = BoardStatus.AIR;
        }
    }

    public void setTet (){
        Coords [] coords = this.tet.getCoords();
        for (int i = 0; i < coords.length; i++){
            gameboard[coords[i].getX()][coords[i].getY()] = BoardStatus.PLAYER;
        }
    }

    public void goRight(){
        this.removeTet();
        this.tet.goRight();
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
}
