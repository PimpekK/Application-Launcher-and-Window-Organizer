module com.example.applicationlauncherandwindoworganizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.applicationlauncherandwindoworganizer to javafx.fxml;
    exports com.example.applicationlauncherandwindoworganizer;
}