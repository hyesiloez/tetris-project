package com.example.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApplication extends Application  {
    @Override
    public void start(Stage stage) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-gui.fxml"));
            Parent root = loader.load();
            MenuController controller = loader.getController();
            Scene scene = new Scene(root, 1280, 720);
            Image icon = new Image(getClass().getResourceAsStream("/com/example/gui/icon.png"));
            stage.setMinHeight(700);
            stage.setMinWidth(900);
            stage.getIcons().add(icon);
            stage.setTitle("Tetris");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage);
            });

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void logout(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Du bist dabei das Programm zu schließen");
        alert.setContentText("Möchtest du das Programm schließen?:");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Log out!");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}