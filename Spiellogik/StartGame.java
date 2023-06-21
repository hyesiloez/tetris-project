public class StartGame {
    private BoardStatus[][] gameboard;
    private boolean isGameOver;

    public StartGame(int length, int height){
        this.gameboard = new BoardStatus[height][length];
        isGameOver = false;

        for(int i = 0; i < gameboard.length; i++){
            for (int j = 0; j < gameboard[i].length; j++){
                gameboard[i][j] = BoardStatus.AIR;
            }
        }

        this.gameboard[0][8] = BoardStatus.PLAYER;
        this.gameboard[0][9] = BoardStatus.PLAYER;
        this.gameboard[0][10] = BoardStatus.PLAYER;
        this.gameboard[0][11] = BoardStatus.PLAYER;

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
