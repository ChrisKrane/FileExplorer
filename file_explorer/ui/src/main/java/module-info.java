module file_explorer.ui {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires file_explorer.core;

    opens file_explorer.ui to javafx.graphics, javafx.fxml;
}