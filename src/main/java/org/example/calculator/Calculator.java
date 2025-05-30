package org.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
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
        display = new TextField();
        display.setPrefSize(300, 80);
        display.setEditable(false);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        String[] buttons = {
                "AC",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        int row = 1;
        int col = 0;

        for (String text : buttons) {
            Button button = new Button(text);
            button.setPrefSize(100, 80);
            button.setOnAction(e -> handleButtonClick(text));
            if (text.equals("AC")) {
                gridPane.add(button, 3, 0);
                continue;
            }
            gridPane.add(button, col, row);
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }
        Scene scene = new Scene(gridPane, 300, 350);
        gridPane.add(display, 0, 0, 3, 1);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPress);

        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();
    }

    private double calculate(double firstNumber, double secondNumber, String operator) {
        return switch (operator) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            default -> 0;
        };
    }

    private void handleButtonClick(String text) {

        if ("0123456789.".contains(text)) {
            if (newNum) {
                display.setText(text);
                newNum = false;
            } else {
                display.appendText(text);
            }
        } else if ("/*-+".contains(text)) {
            firstNum = Double.parseDouble(display.getText());
            operator = text;
            newNum = true;
        } else if ("=".equals(text)) {
            double secondOperand = Double.parseDouble(display.getText());
            double result = calculate(firstNum, secondOperand, operator);
            display.setText(String.valueOf(result));
            newNum = true;
        } else if ("AC".equals(text)) {
            handleClear();
        }


    }

    private void handleKeyPress(KeyEvent event) {
        String keyText = event.getText();
        if ("0123456789.".contains(keyText)) {
            handleButtonClick(keyText);
        } else {
            switch (event.getCode()) {
                case ADD -> handleButtonClick("+");
                case SUBTRACT -> handleButtonClick("-");
                case MULTIPLY -> handleButtonClick("*");
                case DIVIDE -> handleButtonClick("/");
                case ENTER -> handleButtonClick("=");
            }
        }
    }

    private void handleClear() {
        display.clear();
        firstNum = 0;
        operator = "";
        newNum = true;
    }

}