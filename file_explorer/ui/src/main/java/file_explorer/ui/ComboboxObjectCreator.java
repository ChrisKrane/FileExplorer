package file_explorer.ui;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ComboboxObjectCreator {
    
    @FXML private ComboBox<Label> comboBox;

    //Konstruktør
    public ComboboxObjectCreator(ComboBox<Label> comboBox) {
        this.comboBox = comboBox;
    }

    //Populerer comboboxen med stasjoner
    public void populateComboBox(File[] drives) {
        for(File drive : drives) {
            comboBox.getItems().add(new Label(drive.getAbsolutePath()));
        }  
    }
}
