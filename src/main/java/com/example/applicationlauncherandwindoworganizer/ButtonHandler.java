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
        gridPaneId = 0;
    }

    public void addButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setOnAction(event -> handleButtonAction(button));
        buttonList.add(button);
        updateGridPane();
    }

    private void handleButtonAction(Button button) {
        if(button.getStyleClass().contains("delete")) {
            buttonList.remove(button);
            updateGridPane();
        }
        else {

        }
        System.out.println("Clicked: " + button.getText());
    }

    private void updateGridPane() {
        gridPane.getChildren().clear();
        int visibleButtonIndex = 0;
        for (int i = 0; i < buttonList.size(); ++i) {
            if((i / 4) == gridPaneId) {
                Button button = buttonList.get(i);
                button.getStyleClass().add("buttonOnPage");
                int row = (visibleButtonIndex / 2) * 2;
                int col = (visibleButtonIndex % 2) * 2;
                gridPane.add(button, col, row);
                visibleButtonIndex++;
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

    public void giveDeleteClassToButtons() {
        boolean hasDeleteClass = buttonList.get(0).getStyleClass().contains("delete");
        for(int i = 0; i < buttonList.size(); ++i) {
            if(hasDeleteClass)
                buttonList.get(i).getStyleClass().remove("delete");
            else
                buttonList.get(i).getStyleClass().add("delete");
        }
        updateGridPane();
    }
}
