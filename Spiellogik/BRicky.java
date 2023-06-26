public class BRicky implements Tetromino{
    private Coords[] coords;
    private boolean on_ground;
    private int turn_counter;

    public BRicky(){
        this.coords = new Coords[4];
        this.on_ground = false;
        this.turn_counter = 0;

        this.coords[0] = new Coords(1,4);
        this.coords[1] = new Coords(1,5);
        this.coords[2] = new Coords(0,4);
        this.coords[3] = new Coords(1,6);
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

    public void turn(){
        switch(this.turn_counter % 4){
            case 0:
                this.coords[0].setX(this.coords[0].getX() - 1);
                this.coords[0].setY(this.coords[0].getY() + 1);
                this.coords[2].setY(this.coords[2].getY() + 2);
                this.coords[3].setX(this.coords[3].getX() + 1);
                this.coords[3].setY(this.coords[3].getY() - 1);
                this.turn_counter++;
                break;
            case 1:
                this.coords[0].setX(this.coords[0].getX() + 1);
                this.coords[0].setY(this.coords[0].getY() + 1);
                this.coords[2].setX(this.coords[2].getX() + 2);
                this.coords[3].setX(this.coords[3].getX() - 1);
                this.coords[3].setY(this.coords[3].getY() - 1);
                this.turn_counter++;
                break;
            case 2:
                this.coords[0].setX(this.coords[0].getX() + 1);
                this.coords[0].setY(this.coords[0].getY() - 1);
                this.coords[2].setY(this.coords[2].getY() - 2);
                this.coords[3].setX(this.coords[3].getX() - 1);
                this.coords[3].setY(this.coords[3].getY() + 1);
                this.turn_counter++;
                break;
            case 3:
                this.coords[0].setX(this.coords[0].getX() - 1);
                this.coords[0].setY(this.coords[0].getY() - 1);
                this.coords[2].setX(this.coords[2].getX() - 2);
                this.coords[3].setX(this.coords[3].getX() + 1);
                this.coords[3].setY(this.coords[3].getY() + 1);
                this.turn_counter++;
                break;
            default:
                return;
        }
    }
    public void drop(){
        this.defaultDrop(this.coords);
    }
}
