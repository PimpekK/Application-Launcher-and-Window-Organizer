package com.example.applicationlauncherandwindoworganizer;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class HelloController {
    @FXML
    private GridPane buttonContainer;

    private ButtonHandler buttonHandler;

    @FXML
    public void initialize() {
        buttonHandler = new ButtonHandler(buttonContainer);
    }

    @FXML
    public void addButton() {
        buttonHandler.addButton("Button " + (buttonHandler.getButtonList().size() + 1));
    }

    @FXML
    public void previousButton() {
        buttonHandler.reduceGridPaneId();
    }

    @FXML
    public void nextButton() {
        buttonHandler.increaseGridPaneId();
    }

    @FXML
    public void deleteButton() {
        buttonHandler.giveDeleteClassToButtons();
    }
}
