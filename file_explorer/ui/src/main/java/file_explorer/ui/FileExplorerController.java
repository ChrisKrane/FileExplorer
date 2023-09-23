package file_explorer.ui;

import file_explorer.core.DesktopLocator;
import file_explorer.core.DirectoryFileFinder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class FileExplorerController {

    @FXML private ListView<Label> fileListView;
    @FXML private Button button1;

    public void viewFiles() {
        ListViewObjectCreator populator = new ListViewObjectCreator(fileListView);
        DirectoryFileFinder finder = new DirectoryFileFinder();
        DesktopLocator locator = new DesktopLocator();

        populator.populateScrollPane(finder.findFilesInDirectory(locator.findDesktopLocation()));

    }
}
