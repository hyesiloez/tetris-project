public class Hero implements Tetromino {
    private Coords[] coords;
    private boolean on_ground;
    private int turn_counter;

    public Hero() {
        this.coords = new Coords[4];
        this.on_ground = false;
        this.turn_counter = 0;

        this.coords[0] = new Coords(0,4);
        this.coords[1] = new Coords(0,5);
        this.coords[2] = new Coords(0,6);
        this.coords[3] = new Coords(0,7);
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

    public boolean CheckOutOfBounds (int x , int y) {
        return x >= 0 && x < 14 && y >= 0 && y < 10;          //nicht  hardcoded wäre besser
    }

    public void turn(){
        switch(this.turn_counter % 4){
            case 0:
                if (    CheckOutOfBounds(coords[0].getX() - 2 , coords[0].getY() + 2) &&
                        CheckOutOfBounds(coords[1].getX() - 1  ,coords[1].getY() + 1 ) &&
                        CheckOutOfBounds(coords[3].getX() + 1, coords[3].getY() - 1)) {
                        this.coords[0].setX(this.coords[0].getX() - 2);
                        this.coords[0].setY(this.coords[0].getY() + 2);
                        this.coords[1].setX(this.coords[1].getX() - 1);
                        this.coords[1].setY(this.coords[1].getY() + 1);
                        this.coords[3].setX(this.coords[3].getX() + 1);
                        this.coords[3].setY(this.coords[3].getY() - 1);
                        this.turn_counter++;
                        break;
                }
            case 1:
                if (    CheckOutOfBounds(coords[0].getX() + 2 , coords[0].getY() + 2) &&
                        CheckOutOfBounds(coords[1].getX() + 1  ,coords[1].getY() + 1 ) &&
                        CheckOutOfBounds(coords[3].getX() - 1, coords[3].getY() - 1)) {
                        this.coords[0].setX(this.coords[0].getX() + 2);
                        this.coords[0].setY(this.coords[0].getY() + 2);
                        this.coords[1].setX(this.coords[1].getX() + 1);
                        this.coords[1].setY(this.coords[1].getY() + 1);
                        this.coords[3].setX(this.coords[3].getX() - 1);
                        this.coords[3].setY(this.coords[3].getY() - 1);
                        this.turn_counter++;
                        break;
                }
            case 2:
                if (    CheckOutOfBounds(coords[0].getX() + 2 , coords[0].getY() - 2) &&
                        CheckOutOfBounds(coords[1].getX() + 1  ,coords[1].getY() - 1 ) &&
                        CheckOutOfBounds(coords[3].getX() - 1, coords[3].getY() + 1)) {
                        this.coords[0].setX(this.coords[0].getX() + 2);
                        this.coords[0].setY(this.coords[0].getY() - 2);
                        this.coords[1].setX(this.coords[1].getX() + 1);
                        this.coords[1].setY(this.coords[1].getY() - 1);
                        this.coords[3].setX(this.coords[3].getX() - 1);
                        this.coords[3].setY(this.coords[3].getY() + 1);
                        this.turn_counter++;
                        break;
                }
            case 3:
                if (    CheckOutOfBounds(coords[0].getX() - 2 , coords[0].getY() - 2) &&
                        CheckOutOfBounds(coords[1].getX() - 1 ,coords[1].getY() - 1 ) &&
                        CheckOutOfBounds(coords[3].getX() + 1, coords[3].getY() + 1)) {
                        this.coords[0].setX(this.coords[0].getX() - 2);
                        this.coords[0].setY(this.coords[0].getY() - 2);
                        this.coords[1].setX(this.coords[1].getX() - 1);
                        this.coords[1].setY(this.coords[1].getY() - 1);
                        this.coords[3].setX(this.coords[3].getX() + 1);
                        this.coords[3].setY(this.coords[3].getY() + 1);
                        this.turn_counter++;
                        break;
                }
            default:
                return;
        }
    }
    public void drop(){
        this.defaultDrop(this.coords);
    }
}