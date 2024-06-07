package org.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Calculator extends Application {
    private TextField display;
    private String operator = "";
    private double firstNum = 0;
    private boolean newNum = true;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

    }

}