// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main2 {
    public static void main(String[] args) {


        BoardStatus test = BoardStatus.AIR;
        test = BoardStatus.SET;
        test = BoardStatus.PLAYER;
        Coords coords = new Coords (19,19);
        StartGame test1 = new StartGame(20,10);
        test1.setTet();
        System.out.println(test1);
        test1.goRight();
        System.out.println(test1);
    }
}