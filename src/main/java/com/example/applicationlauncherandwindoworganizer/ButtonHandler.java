package com.example.applicationlauncherandwindoworganizer;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

public class ButtonHandler {
    private static final int GWL_STYLE = -16;
    private static final int WS_VISIBLE = 0x10000000;
    private static final int WS_POPUP = 0x80000000;

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

        User32.INSTANCE.EnumWindows(new WinUser.WNDENUMPROC() {
            @Override
            public boolean callback(WinDef.HWND hWnd, Pointer arg1) {
                if (isWindowAppropriate(hWnd)) {
                    char[] windowText = new char[512];
                    User32.INSTANCE.GetWindowText(hWnd, windowText, 512);
                    String wText = Native.toString(windowText);

                    if (!wText.isEmpty()) {
                        WinDef.RECT rect = new WinDef.RECT();
                        User32.INSTANCE.GetWindowRect(hWnd, rect);
                        if (rect.left < rect.right && rect.top < rect.bottom) {
                            System.out.println("Window Title: " + wText);
                            System.out.println("Position: (" + rect.left + ", " + rect.top + ") - (" + rect.right + ", " + rect.bottom + ")");
                        }
                    }
                }
                return true;
            }
        }, null);
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

    private static boolean isWindowAppropriate(WinDef.HWND hWnd) {
        int style = User32.INSTANCE.GetWindowLong(hWnd, GWL_STYLE);

        return ((style & WS_VISIBLE) != 0) && ((style & WS_POPUP) == 0);
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
        boolean hasDeleteClass = buttonList.getFirst().getStyleClass().contains("delete");
        for (Button button : buttonList) {
            if (hasDeleteClass)
                button.getStyleClass().remove("delete");
            else
                button.getStyleClass().add("delete");
        }
        updateGridPane();
    }
}
