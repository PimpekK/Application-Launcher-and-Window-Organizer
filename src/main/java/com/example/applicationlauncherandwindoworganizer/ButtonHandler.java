package com.example.applicationlauncherandwindoworganizer;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ButtonHandler {
    private List<Button> buttonList;
    private VBox buttonContainer;

    public ButtonHandler(VBox buttonContainer) {
        this.buttonContainer = buttonContainer;
        this.buttonList = new ArrayList<>();
    }

    public void addButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setOnAction(event -> handleButtonAction(button));
        buttonList.add(button);
        buttonContainer.getChildren().add(button);
    }

    private void handleButtonAction(Button button) {
        // Dodaj tutaj kod, który ma się wykonać po kliknięciu przycisku
        System.out.println("Clicked: " + button.getText());
    }

    public List<Button> getButtonList() {
        return buttonList;
    }
}
