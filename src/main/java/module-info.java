module com.example.applicationlauncherandwindoworganizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.sun.jna;
    requires com.sun.jna.platform;


    opens com.example.applicationlauncherandwindoworganizer to javafx.fxml;
    exports com.example.applicationlauncherandwindoworganizer;
}