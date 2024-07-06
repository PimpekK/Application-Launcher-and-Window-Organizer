package com.example.applicationlauncherandwindoworganizer;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class ButtonHandler {
    private List<Button> buttonList;
    private GridPane gridPane;
    private int gridPaneId;

    public ButtonHandler(GridPane gridPane) {
        this.gridPane = gridPane;
        this.buttonList = new ArrayList<>();
        gridPaneId = 1;
    }

    public void addButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setOnAction(event -> handleButtonAction(button));
        buttonList.add(button);
        updateGridPane();
    }

    private void handleButtonAction(Button button) {
        // Dodaj tutaj kod, który ma się wykonać po kliknięciu przycisku
        System.out.println("Clicked: " + button.getText());
    }

    private void updateGridPane() {
        gridPane.getChildren().clear();
        for (int i = 0; i < buttonList.size(); i++) {
            if((i / 4) == gridPaneId) {
                Button button = buttonList.get(i);
                button.getStyleClass().add("buttonOnPage");
                int row = (i / 4) * 2 + (i % 4) / 2;
                int col = (i % 4) % 2 * 2;
                gridPane.add(button, col, row);
            }
        }
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public void reduceGridPaneId() {
        if(gridPaneId > 0) {
            gridPaneId--;
            updateGridPane();
        }
    }

    public void increaseGridPaneId() {
        if (((buttonList.size() - 1) / 4) > gridPaneId) {
            gridPaneId++;
            updateGridPane();
        }
    }
}
