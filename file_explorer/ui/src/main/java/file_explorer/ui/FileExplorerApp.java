package file_explorer.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileExplorerApp extends Application {

@Override
public void start(Stage stage) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("FileExplorerApp.fxml"));
    stage.setScene(new Scene(parent));
    stage.show();
}

public static void main(String[] args) {
    launch();
    }
}