package com.example.gui;

import Spiellogik.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TetrisController {
    @FXML
    private GridPane gridPane;
    private StartGame game;
    private Button[][] gridButtons;
    MenuController menuController;
    Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() {
        game = new StartGame(10, 14);
        gridButtons = new Button[14][10];
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
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
    }
    @FXML
    public void startGame() {
            game.play();
            updateGrid();
    }
    public void singlePlayer(ActionEvent e) throws IOException {
        System.out.println("Singleplayer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("singleplayer.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void backToSingleplayer(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ESCAPE){
            System.out.println("Escape pressed!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("singleplayer.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
            stage.show();
        }
    }

}
