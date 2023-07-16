package Spiellogik;
/**
 * Enum which sets every block in gameboard either PLAYER,SET or AIR
 * @author Erik Brinker
 * @version 14.07.2023
 *
 */
public enum BoardStatus {
    PLAYER(true),
    SET(true),
    AIR(false);

    private final boolean is_set; /**boolean value which determines whether block is set or not */

    private BoardStatus(boolean b){
        this.is_set = b;
    }

    public boolean getIsSet(){
        return this.is_set;
    }
}
