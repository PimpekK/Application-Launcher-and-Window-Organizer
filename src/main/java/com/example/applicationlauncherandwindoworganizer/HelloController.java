package com.example.applicationlauncherandwindoworganizer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private VBox buttonContainer;

    private ButtonHandler buttonHandler;

    @FXML
    public void initialize() {
        buttonHandler = new ButtonHandler(buttonContainer);
    }

    @FXML
    public void addButton() {
        buttonHandler.addButton("Button " + (buttonHandler.getButtonList().size() + 1));
    }
}