package Spiellogik;

public enum BoardStatus {
    PLAYER(true),
    SET(true),
    AIR(false);

    private final boolean is_set;

    private BoardStatus(boolean b){
        this.is_set = b;
    }

    public boolean getIsSet(){
        return this.is_set;
    }
}
