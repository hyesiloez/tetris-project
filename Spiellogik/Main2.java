// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        BoardStatus test = BoardStatus.AIR;
        test = BoardStatus.SET;
        test = BoardStatus.PLAYER;
        Coords coords = new Coords (19,19);
        StartGame test1 = new StartGame(20,10);
        System.out.println(test1);
    }
}