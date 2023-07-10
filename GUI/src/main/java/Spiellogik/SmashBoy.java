package Spiellogik;

public class SmashBoy implements Tetromino {
    private Coords[] coords;
    private boolean on_ground;
    private int turn_counter;

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

    public void turn(BoardStatus[][] gameboard){
        switch(this.turn_counter % 4){
            default:
                return;
        }
    }
    public void drop(){
        this.defaultDrop(this.coords);
    }

    public boolean CheckOutOfBounds (int x , int y, BoardStatus[][] gameboard) {
        if(!(x >= 0 && x < 14 && y >= 0 && y < 10 )) return false;

        if(gameboard[x][y] == BoardStatus.SET) return false;

        return true;
    }
}
