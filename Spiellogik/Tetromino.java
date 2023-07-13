public interface Tetromino {
    default void defaultGoRight(Coords[] coords ){
        for (int j = 0; j < coords.length; j++){
            if(coords[j].getY() == 9){
                return; //Nicht außerhalb des Spielfeldes laufen
            }
        }

        for (int i = 0; i < coords.length; i++){
            coords[i].setY(coords[i].getY() + 1);
        }
    }

    public void goRight();

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
    public void goLeft();


    public void turn(BoardStatus[][] gameboard);

    default void defaultDrop(Coords[] coords){
        for (int i = 0; i < coords.length; i++){
            coords[i].setX(coords[i].getX() + 1);
        }
    }
    public void drop();
    public Coords[] getCoords();

    public void setOn_ground(boolean a);

    public boolean getOn_ground();

    public boolean CheckOutOfBounds (int x, int y, BoardStatus[][] gameboard);


}
