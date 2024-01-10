package file_explorer.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListViewObjectCreator {
    
    private ListView<Label> listView;
    // Contains the files that are displayed in the ListView as filenames
    private List<String> fileNames = new ArrayList<String>();

    /**
     * Constructor
     * 
     * @param listView
     */
    public ListViewObjectCreator(ListView<Label> listView) {
        this.listView = listView;
    }

    /**
     * Converts file to label
     * 
     * @param file
     * @return file as label
     */
    public Label fileToLabel(File file) {
        Label label = new Label(file.getName());
        return label;
    }

    /**
     * Populates a listview with files
     * 
     * @param files
     */
    public void populateListView(List<File> files) {
        fileNames.clear();
        if(listView.getItems().size() != 0) {
            listView.getItems().clear();
            for (File file : files) {
                listView.getItems().add(fileToLabel(file));
                fileNames.add(file.getName());
            }
        }
        else {
            for (File file : files) {
                listView.getItems().add(fileToLabel(file));
                fileNames.add(file.getName());
            }
        }
    }

    public List<String> getListOfFilenames() {
        return fileNames;
    }
}
