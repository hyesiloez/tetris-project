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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Key;
import java.util.concurrent.TimeUnit;
//Für das automatische Fallen
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MultiplayerController {
    @FXML
    private GridPane gridPaneP1;
    @FXML
    private GridPane gridPaneP2;
    @FXML
    private Label scoreLabelP1;
    @FXML
    private Label scoreLabelP2;
    @FXML
    private Label scoreLabelGameOverP1;
    @FXML
    private Label scoreLabelGameOverP2;
    @FXML
    private AnchorPane gameOverPopUpMulti;
    @FXML
    private HBox hBoxGameOverMulti1;
    @FXML
    private HBox hBoxGameOverMulti2;
    @FXML
    private HBox hBoxGameOverMulti3;
    private StartGame gameP1;
    private StartGame gameP2;
    private Button[][] gridButtons;
    private Button[][] gridButtonsP2;
    MenuController menuController;
    Stage stage;
    private Scene scene;
    private Parent root;
    private int scoreP1;
    private int scoreP2;


    public void initialize() {
        scoreP1 = 0;
        scoreP2 = 0;
        scoreLabelP1.setText("  Score: 0");
        scoreLabelP2.setText("  Score: 0");
        changeBackground(false);
        gameOverPopUpMulti.setVisible(false);
        gameP1 = new StartGame(10, 14);
        gameP2 = new StartGame(10, 14);
        gridButtons = new Button[14][10];
        gridButtonsP2 = new Button[14][10];
        gridPaneP1.getRowConstraints().clear();
        gridPaneP1.getColumnConstraints().clear();
        gridPaneP2.getRowConstraints().clear();
        gridPaneP2.getColumnConstraints().clear();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = new Button();
                button.setPrefSize(30, 30);
                gridPaneP1.add(button, j, i);
                gridButtons[i][j] = button;
                gridButtons[i][j].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #04196C;");
            }
        }
        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 10; y++) {
                Button button = new Button();
                button.setPrefSize(30, 30);
                gridPaneP2.add(button, y, x);
                gridButtonsP2[x][y] = button;
                gridButtonsP2[x][y].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #04196C;");
            }
        }
    }

    private void updateGrid() {
        boolean[][] board = gameP1.convertToBooleanArray();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j]) {
                    gridButtons[i][j].setStyle("-fx-background-color: #000000; -fx-border-color: #04196C;");
                } else {
                    gridButtons[i][j].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #04196C;");
                }
            }
        }
        updateScoreP1();
        updateScoreP2();
    }
    private void updateGridP2() {
        boolean[][] boardP2 = gameP2.convertToBooleanArray();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 10; j++) {
                if (boardP2[i][j]) {
                    gridButtonsP2[i][j].setStyle("-fx-background-color: #000000; -fx-border-color: #04196C;");
                } else {
                    gridButtonsP2[i][j].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #04196C;");
                }
            }
        }
    }
    @FXML
    public void startGame() {
        scoreP1 = 0;
        scoreP2 = 0;
        gameP1 = new StartGame(10,14);
        gameP2 = new StartGame(10,14);
        tetFall(gameP1);
        tetFall(gameP2);
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
    public void rotateP2() {
        System.out.println("Rotate!");
        gameP2.turn(gameP2.gameboard);
        updateGrid();
        updateGridP2();
        System.out.println(gameP2);
    }
    public void rotateP1() {
        System.out.println("Rotate!");
        gameP1.turn(gameP1.gameboard);
        updateGrid();
        updateGridP2();
        System.out.println(gameP1);
    }
    public void moveRightP2()  {
        System.out.println("Right!");
        gameP2.goRight();
        updateGrid();
        updateGridP2();
        System.out.println(gameP2);
    }
    public void moveRightP1()  {
        System.out.println("Right!");
        gameP1.goRight();
        updateGrid();
        updateGridP2();
        System.out.println(gameP1);
    }

    public void moveLeftP2() {
        System.out.println("Left!");
        gameP2.goLeft();
        updateGrid();
        updateGridP2();
        System.out.println(gameP2);
    }
    public void moveLeftP1() {
        System.out.println("Left!");
        gameP1.goLeft();
        updateGrid();
        updateGridP2();
        System.out.println(gameP1);
    }

    public void dropTetP2() {
        System.out.println("Drop!");
        if (gameP2.getIsGameOver()){
            changeBackground(true);
            gameOverPopUpMulti.setVisible(true);
            return;
        }
        if (!(gameP2.getTet().getOn_ground())) {
            gameP2.drop();
            //game.isOnGround();
            updateGrid();
            updateGridP2();
        } else if (!gameP2.getIsGameOver()){
            gameP2.changeTet(gameP2.getNext_tet());
            gameP2.setTet();
            updateGrid();
            updateGridP2();
            gameP2.changenext_tet(gameP2.randomTet());
            updateGrid();
            updateGridP2();
        }
        System.out.println(gameP2);
        updateGrid();
        updateGridP2();
    }
    public void dropTetP1() {
        System.out.println("Drop!");
        if (gameP1.getIsGameOver()){
            changeBackground(true);
            gameOverPopUpMulti.setVisible(true);
            return;
        }
        if (!(gameP1.getTet().getOn_ground())) {
            gameP1.drop();
            //game.isOnGround();
            updateGrid();
            updateGridP2();
        } else if (!gameP1.getIsGameOver()){
            gameP1.changeTet(gameP1.getNext_tet());
            updateGrid();
            updateGridP2();
            gameP1.setTet();
            gameP1.changenext_tet(gameP1.randomTet());
            updateGrid();
            updateGridP2();
        }

        System.out.println(gameP1);
        updateGrid();
        updateGridP2();
    }
    public void tetFall(StartGame a){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(new Runnable(){
            public void run() {

                if (!(a.getTet().getOn_ground())) {
                    a.drop();
                    System.out.println(a);
                    //a.isOnGround();
                    updateGrid();
                    updateGridP2();
                } else if (!a.getIsGameOver()){
                    a.changeTet(a.getNext_tet());
                    a.setTet();
                    a.changenext_tet(a.randomTet());
                } else {
                    changeBackground(true);
                    gameOverPopUpMulti.setVisible(true);
                    scheduler.shutdown();
                    System.out.println("GAME OVER");
                }

            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

    }
    public void updateScoreP1() {
        if (gameP1.isFullRowPoints) {
            scoreP1 += 100;
            gameP1.isFullRowPoints = false;
        }
        if (gameP1.isTetOnGround) {
            scoreP1 += 10;
            gameP1.isTetOnGround = false;
        }
        scoreLabelP1.setText("  Score: " + scoreP1);
        int scoreGameOverP1 = scoreP1;
        scoreLabelGameOverP1.setText("Score: " + scoreGameOverP1);
    }

    public void updateScoreP2() {
        if (gameP2.isFullRowPoints) {
            scoreP2 += 100;
            gameP2.isFullRowPoints = false;
        }
        if (gameP2.isTetOnGround) {
            scoreP2 += 10;
            gameP2.isTetOnGround = false;
        }
        scoreLabelP2.setText("  Score: " + scoreP2);
        int scoreGameOverP2 = scoreP2;
        scoreLabelGameOverP2.setText("Score: " + scoreGameOverP2);
    }

    public void restart() {
        scoreP1 = 0;
        scoreP2 = 0;
        scoreLabelP1.setText("  Score: 0");
        scoreLabelP2.setText("  Score: 0");
        changeBackground(false);
        gameOverPopUpMulti.setVisible(false);
        this.startGame();
    }
    public void changeBackground(boolean color){
        if(color){
            hBoxGameOverMulti1.setStyle("-fx-background-color: #000000;");
            hBoxGameOverMulti2.setStyle("-fx-background-color: #000000;");
            hBoxGameOverMulti3.setStyle("-fx-background-color: #000000;");
        } else {
            hBoxGameOverMulti1.setStyle("-fx-background-color: transparent;");
            hBoxGameOverMulti2.setStyle("-fx-background-color: transparent;");
            hBoxGameOverMulti3.setStyle("-fx-background-color: transparent;");
        }
    }
}

