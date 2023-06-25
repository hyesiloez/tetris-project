package com.example.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {

                    switch (keyEvent.getCode()) {
                        case ESCAPE:
                            try {
                                controller.back();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        default:
                            break;
                    }

                }
            });

            stage.setTitle("Tetris");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}