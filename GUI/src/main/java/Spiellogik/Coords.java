package Spiellogik;
/**
 * Class implements position of Blocks
 * @version 22.06.2023
 * @author Erik Brinker
 */
public class Coords {
    private int x; /** coordinate of array rows*/
    private int y;/** coordinate of array columns*/

    public Coords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return this.x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return this.y;
    }
}
