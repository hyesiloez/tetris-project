package com.example.gui;

import Spiellogik.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Key;
import java.util.concurrent.TimeUnit;
//Für das automatische Fallen
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TetrisController {
    @FXML
    private AnchorPane gameOverPopUp;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label scoreLabel;
    private StartGame game;
    private Button[][] gridButtons;
    MenuController menuController;
    Stage stage;
    private Scene scene;
    private Parent root;


    private int score = 0;

    private ScheduledExecutorService scheduler;

    public void initialize() {
        game = new StartGame(10, 14);
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
            }
        }
    }


    private void updateGrid() {
        boolean[][] board = game.convertToBooleanArray();

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j]) {
                    gridButtons[i][j].setStyle("-fx-background-color: #000000;");
                } else {
                    gridButtons[i][j].setStyle("-fx-background-color: #FFFFFF;");
                }
            }
        }
        updateScore();
    }
    @FXML
    public void startGame() {
        game = new StartGame(10,14);
        scheduler.shutdown();
        tetFall(game);

    }


    public void toMainMenu(ActionEvent e) throws IOException {
        System.out.println("TO Main Menu");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-gui.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
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
    public void rotate(ActionEvent event) throws IOException {
        System.out.println("Rotate!");
        game.turn(game.gameboard);
        updateGrid();
        System.out.println(game);
    }
    public void rotate2() {
        System.out.println("Rotate!");
        if (game.getIsGameOver()){
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
    public void moveRight(ActionEvent event)  {
        System.out.println("Right!");
        game.goRight();
        updateGrid();
        System.out.println(game);
    }
    public void moveRight2()  {
        System.out.println("Right!");
        if (game.getIsGameOver()){
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

    public void moveLeft(ActionEvent event) {
        System.out.println("Left!");
        game.goLeft();
        updateGrid();
        System.out.println(game);
    }
    public void moveLeft2() {
        System.out.println("Left!");
        if (game.getIsGameOver()){
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

    public void dropTet(ActionEvent event) {
        System.out.println("Drop!");
        if (game.getIsGameOver()){
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
    public void dropTet2() {
        System.out.println("Drop!");
        if (game.getIsGameOver()){
            gameOverPopUp.setVisible(true);
            return;
        }
        if (!(game.getTet().getOn_ground())) {
            game.drop();
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
    public void tetFall(StartGame a){
        scheduler = Executors.newSingleThreadScheduledExecutor();
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
                    gameOverPopUp.setVisible(true);
                    System.out.println("GAME OVER");
                }

            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

    }
    public void updateScore () {

        scoreLabel.setText("  Score:  " + game.getPoints());
    }

    public void restart () {
        gameOverPopUp.setVisible(false);
        this.startGame();
    }
}
