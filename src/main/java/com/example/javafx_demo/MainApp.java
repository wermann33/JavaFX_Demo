package com.example.javafx_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.util.Objects;
public class MainApp extends Application {

    @Getter
    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LogInDemo!");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/favicon.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException{
        stg.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml))));
    }

    public static void main(String[] args) {
        launch();
    }
}