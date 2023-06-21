public class Hero implements Tetromino {
    Coords[] coords;
    boolean on_ground;

    public Hero() {
        this.coords = new Coords[4];
        this.on_ground = false;

        this.coords[0] = new Coords(0,8);
        this.coords[1] = new Coords(0,9);
        this.coords[2] = new Coords(0,10);
        this.coords[3] = new Coords(0,11);
    }

    public Coords[] getCoords(){
        return this.coords;
    }
    public void goRight(){
        for (int i = 0; i < this.coords.length; i++){
            this.coords[i].setY(this.coords[i].getY() + 1);
        }
    }
    public void goLeft(){

    }
    public void turn(){

    }
    public void drop(){

    }
}