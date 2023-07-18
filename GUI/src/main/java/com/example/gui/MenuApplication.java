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
/**
 * The main class for the Menu Application.
 * @version 19.07.2023
 * @author Vladislav Gornet, Jonas Plankert
 */
public class MenuApplication extends Application  {
    /**
     * The entry point for the JavaFX application.
     *
     * @param stage The stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-gui.fxml"));
            Parent root = loader.load();
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
    /**
     * Handles the logout process when the application is closed.
     *
     * @param stage The stage of the application.
     */
    public void logout(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Du bist dabei das Programm zu schließen");
        alert.setContentText("Möchtest du das Programm schließen?:");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Log out!");
            stage.close();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}