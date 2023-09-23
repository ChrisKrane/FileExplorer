package file_explorer.ui;

import java.io.File;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListViewObjectCreator {
    
    private ListView<Label> listView;

    public ListViewObjectCreator(ListView<Label> listView) {
        this.listView = listView;
    }

    public Label fileToLabel(File file) {

        Label label = new Label(file.getName());
        return label;
    }

    public void populateScrollPane(List<File> files) {
        
        for (File file : files) {
            listView.getItems().add(fileToLabel(file));
        }
        
        
    }
}
