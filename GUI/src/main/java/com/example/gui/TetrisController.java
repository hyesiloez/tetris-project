package com.example.gui;

import Spiellogik.StartGame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * The controller class for the single-player game screen.
 * @version 19.07.2023
 * @author  Jonas Plankert, Vladislav Gornet
 */
public class TetrisController {
    @FXML
    private AnchorPane gameOverPopUp;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label scoreLabelGameOver;
    @FXML
    private HBox hBoxGameOver1;
    @FXML
    private HBox hBoxGameOver2;
    @FXML
    private HBox hBoxGameOver3;
    private StartGame game;
    private Button[][] gridButtons;
    MenuController menuController;
    Stage stage;
    private Scene scene;
    private Parent root;

    private ScheduledExecutorService scheduler;
    /**
     * Initializes the controller and sets up the game grid and UI components.
     */
    public void initialize() {
        changeBackground(false);
        gameOverPopUp.setVisible(false);
        game = new StartGame(10, 14);
        scoreLabel.setText("  Score:  " + game.getPoints());
        gridButtons = new Button[14][10];
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = new Button();
                button.setPrefSize(30, 30);
                gridPane.add(button, j, i);
                gridButtons[i][j] = button;
                gridButtons[i][j].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #04196C;");
            }
        }
    }

    /**
     * Updates the game grid.
     */
    private void updateGrid() {
        boolean[][] board = game.convertToBooleanArray();

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j]) {
                    gridButtons[i][j].setStyle("-fx-background-color: #000000; -fx-border-color: #04196C;");
                } else {
                    gridButtons[i][j].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #04196C;");
                }
            }
        }
        updateScore();
    }
    /**
     * Starts the game.
     */
    @FXML
    public void startGame() {
        scheduler.shutdown();
        game = new StartGame(10,14);
        scoreLabel.setText("  Score:  " + game.getPoints());
        tetFall(game);
    }

    /**
     * Returns to the main menu screen.
     *
     * @param e The event triggered by clicking the "Back" button.
     */
    public void toMainMenu(ActionEvent e) throws IOException {
        System.out.println("TO Main Menu");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-gui.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Returns to the main menu screen when the Escape key is pressed.
     *
     * @param event The event triggered by pressing a key.
     */
    @FXML
    void backToMainMenu(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ESCAPE){
            System.out.println("Escape pressed!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-gui.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(scene);
            stage.show();
        }
    }
    /**
     * Rotates the Tetromino when you click the button on screen.
     */
    public void rotate(ActionEvent event) throws IOException {
        System.out.println("Rotate!");
        game.turn(game.gameboard);
        updateGrid();
        System.out.println(game);
    }
    /**
     * Rotates the Tetromino.
     */
    public void rotate2() {
        System.out.println("Rotate!");
        if (game.getIsGameOver()){
            changeBackground(true);
            gameOverPopUp.setVisible(true);
            return;
        }
        if (!(game.getTet().getOn_ground())) {
            game.turn(game.gameboard);
            //game.isOnGround();
            updateGrid();
        } else if (!game.getIsGameOver()){
            game.changeTet(game.getNext_tet());
            game.setTet();
            updateGrid();
            game.changenext_tet(game.randomTet());
            updateGrid();
        }
        System.out.println(game);
        updateGrid();
    }
    /**
     * Moves the Tetromino to the right when you click the button on screen.
     */
    public void moveRight(ActionEvent event)  {
        System.out.println("Right!");
        game.goRight();
        updateGrid();
        System.out.println(game);
    }
    /**
     * Moves the Tetromino to the right.
     */
    public void moveRight2()  {
        System.out.println("Right!");
        if (game.getIsGameOver()){
            changeBackground(true);
            gameOverPopUp.setVisible(true);
            return;
        }
        if (!(game.getTet().getOn_ground())) {
            game.goRight();
            updateGrid();
        } else if (!game.getIsGameOver()){
            game.changeTet(game.getNext_tet());
            game.setTet();
            updateGrid();
            game.changenext_tet(game.randomTet());
            updateGrid();
        }
        System.out.println(game);
        updateGrid();
    }
    /**
     * Moves the Tetromino to the left when you click the button on screen.
     */
    public void moveLeft(ActionEvent event) {
        System.out.println("Left!");
        game.goLeft();
        updateGrid();
        System.out.println(game);
    }
    /**
     * Moves the Tetromino to the left.
     */
    public void moveLeft2() {
        System.out.println("Left!");
        if (game.getIsGameOver()){
            changeBackground(true);
            gameOverPopUp.setVisible(true);
            return;
        }
        if (!(game.getTet().getOn_ground())) {
            game.goLeft();
            //game.isOnGround();
            updateGrid();
        } else if (!game.getIsGameOver()){
            game.changeTet(game.getNext_tet());
            game.setTet();
            updateGrid();
            game.changenext_tet(game.randomTet());
            updateGrid();
        }
        System.out.println(game);
        updateGrid();
    }
    /**
     * Drops the Tetromino when you click the button on screen.
     */
    public void dropTet(ActionEvent event) {
        System.out.println("Drop!");
        if (game.getIsGameOver()){
            changeBackground(true);
            gameOverPopUp.setVisible(true);
            return;
        }
        if (!(game.getTet().getOn_ground())) {
            game.drop();
            //game.isOnGround();
            updateGrid();
        } else if (!game.getIsGameOver()){
            game.changeTet(game.getNext_tet());
            game.setTet();
            updateGrid();
            game.changenext_tet(game.randomTet());
            updateGrid();
        }
        System.out.println(game);
        updateGrid();
    }
    /**
     * Drops the Tetromino.
     */
    public void dropTet2() {
        System.out.println("Drop!");
        if (game.getIsGameOver()){
            changeBackground(true);
            gameOverPopUp.setVisible(true);
            return;
        }
        if (!(game.getTet().getOn_ground())) {
            game.drop();
            //game.isOnGround();
            updateGrid();
        } else if (!game.getIsGameOver()){
            game.changeTet(game.getNext_tet());
            updateGrid();
            game.setTet();
            game.changenext_tet(game.randomTet());
            updateGrid();
        }

        System.out.println(game);
        updateGrid();
    }
    /**
     * Handles the automatic falling of the Tetromino.
     *
     * @param a The StartGame instance.
     */
    public void tetFall(StartGame a){
        this.scheduler = Executors.newSingleThreadScheduledExecutor();

        this.scheduler.scheduleAtFixedRate(new Runnable(){
            public void run() {

                if (!(a.getTet().getOn_ground())) {
                    a.drop();
                    System.out.println(a);
                    //a.isOnGround();
                    updateGrid();
                } else if (!a.getIsGameOver()){
                    a.changeTet(a.getNext_tet());
                    a.setTet();
                    a.changenext_tet(a.randomTet());
                } else {
                    scheduler.shutdown();
                    changeBackground(true);
                    gameOverPopUp.setVisible(true);
                    System.out.println("GAME OVER");
                }

            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

    }
    /**
     * Updates the score label.
     */
    public void updateScore () {
        Platform.runLater(() -> {
        scoreLabel.setText("  Score:  " + game.getPoints());
        int scoreGameOver = game.getPoints();
        scoreLabelGameOver.setText("Score:   " + scoreGameOver);
    });
    }
    /**
     * Restarts the game after game over.
     */
    public void restart () {
        changeBackground(false);
        gameOverPopUp.setVisible(false);
        this.startGame();
    }
    /**
     * Changes the background color when the game is over.
     *
     * @param color If true, sets the background color to black. If false, sets it to transparent.
     */
    public void changeBackground(boolean color){
        if(color){
            hBoxGameOver1.setStyle("-fx-background-color: #000000;");
            hBoxGameOver2.setStyle("-fx-background-color: #000000;");
            hBoxGameOver3.setStyle("-fx-background-color: #000000;");
        } else {
            hBoxGameOver1.setStyle("-fx-background-color: transparent;");
            hBoxGameOver2.setStyle("-fx-background-color: transparent;");
            hBoxGameOver3.setStyle("-fx-background-color: transparent;");
        }
    }
}
