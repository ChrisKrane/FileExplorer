package file_explorer.ui;

import file_explorer.core.StartingDirectoryFinder;

import java.io.File;
import java.util.List;

import file_explorer.core.DirectoryFileFinder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;

public class FileExplorerController {

    @FXML private ListView<Label> fileListView;
    @FXML private ComboBox<Label> driveDropdownMenu;

    ListViewObjectCreator listViewPopulator;
    DirectoryFileFinder directoryFileFinder;
    StartingDirectoryFinder startingDirectoryFinder;
    ComboboxObjectCreator comboBoxPopulator;

    //Initialiserer default-verdiene i ui
    public void initialize() {
        comboBoxPopulator = new ComboboxObjectCreator(driveDropdownMenu);
        listViewPopulator = new ListViewObjectCreator(fileListView);
        directoryFileFinder = new DirectoryFileFinder();
        startingDirectoryFinder = new StartingDirectoryFinder();
        viewFiles(directoryFileFinder.findFilesInDirectory(startingDirectoryFinder.findDriveLocations()[0]));
        comboBoxPopulator.populateComboBox(startingDirectoryFinder.findDriveLocations());
    }

    //Kaller på ListViewObjectCreator for å populere ListView med filer
    public void viewFiles(List<File> files) {
        listViewPopulator.populateListView(files);
    }

    //Kaller på DirectoryFileFinder for å finne filer på en valgt stasjon
    //TODO needs proper testing
    public void selectDrive() {
        File drive = new File(driveDropdownMenu.getValue().getText());
        viewFiles(directoryFileFinder.findFilesInDirectory(drive));
    }
        
}
