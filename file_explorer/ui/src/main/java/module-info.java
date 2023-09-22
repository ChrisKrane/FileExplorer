module file_explorer.ui {

    requires javafx.fxml;
    requires javafx.controls;
    requires file_explorer.core;
    requires javafx.graphics;

    exports file_explorer.ui to javafx.graphics, javafx.fxml;
}