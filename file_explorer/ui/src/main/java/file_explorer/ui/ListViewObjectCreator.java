package file_explorer.ui;

import java.io.File;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListViewObjectCreator {
    
    private ListView<Label> listView;

    //Konstruktør
    public ListViewObjectCreator(ListView<Label> listView) {
        this.listView = listView;
    }

    //Konverterer en fil til en Label
    public Label fileToLabel(File file) {
        Label label = new Label(file.getName());
        return label;
    }

    //Populerer ListView med filer
    public void populateListView(List<File> files) {
        for (File file : files) {
            listView.getItems().add(fileToLabel(file));
        }
    }
}
