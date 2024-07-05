module com.example.applicationlauncherandwindoworganizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.applicationlauncherandwindoworganizer to javafx.fxml;
    exports com.example.applicationlauncherandwindoworganizer;
}