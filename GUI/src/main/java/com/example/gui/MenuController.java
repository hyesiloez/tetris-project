package com.example.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {

    @FXML
    private Button lougoutButton;
    @FXML
    private AnchorPane scenePane;

    TetrisController tetrisController;
    Stage stage;

    public void logout(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Du bist dabei das Programm zu schließen");
        alert.setContentText("Möchtest du das Programm schließen?:");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("Log out!");
            stage.close();
        }
    }
    @FXML
    void backToMainMenu(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ESCAPE){
            System.out.println("Escape pressed!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-gui.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
            stage.show();
        }
    }

    public void toMainMenu(ActionEvent e) throws IOException {
        System.out.println("TO Main Menu");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-gui.fxml"));
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
    @FXML
    void backToMultiplayer(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ESCAPE){
            System.out.println("Escape pressed!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("multiplayer.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
            stage.show();
        }
    }
    private Scene scene;
    private Parent root;

    public void singleplayer(ActionEvent e) throws IOException {
        System.out.println("Singleplayer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("singleplayer.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public void multiplayer(ActionEvent e) throws IOException {
        System.out.println("Multiplayer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("multiplayer.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public void playSingle (ActionEvent e) throws IOException {
        System.out.println("Play Singleplayer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("play_single.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
        tetrisController = loader.getController();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case W:
                        tetrisController.rotate2();
                        break;
                    case S:
                        tetrisController.dropTet2();
                        break;
                    case A:
                        tetrisController.moveLeft2();
                        break;
                    case D :
                        tetrisController.moveRight2();
                        break;
                    default:
                        break;
                }

            }
        });
    }

    public void playMulti (ActionEvent e) throws IOException {
        System.out.println("Play Multiplayer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("play_multi.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();

    }
}