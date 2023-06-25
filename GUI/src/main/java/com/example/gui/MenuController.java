package com.example.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void singleplayer(ActionEvent e) throws IOException {
        System.out.println("Singleplayer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("singleplayer.fxml"));
        root = loader.load();
        MenuController controller = loader.getController();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                switch (keyEvent.getCode()) {
                    case ESCAPE:
                        try {
                            controller.back();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    default:
                        break;
                }

            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public void multiplayer(ActionEvent e) throws IOException {
        System.out.println("Multiplayer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("multiplayer.fxml"));
        root = loader.load();
        MenuController controller = loader.getController();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                switch (keyEvent.getCode()) {
                    case ESCAPE:
                        try {
                            controller.back();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    default:
                        break;
                }

            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent e) {
        System.out.println("Exit");
        System.exit(0);
    }

    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("multiplayer.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}