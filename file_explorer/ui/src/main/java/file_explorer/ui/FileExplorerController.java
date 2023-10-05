package file_explorer.ui;

import file_explorer.core.StartingDirectoryFinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import file_explorer.core.DirectoryFileFinder;
import file_explorer.core.FileInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.ComboBox;

public class FileExplorerController {

    private String currentPath;

    @FXML private ListView<Label> fileListView;
    @FXML private ComboBox<Label> driveDropdownMenu;
    @FXML private TextField costumPathField;
    @FXML private ListView<Label> fileInformationList;
    @FXML private ProgressIndicator loadingIcon;
    @FXML private Button desktopButton;
    @FXML private Button goToParentButton;
    @FXML private Button goToSelectedButton;

    private ListViewObjectCreator listViewPopulator;
    private DirectoryFileFinder directoryFileFinder;
    private StartingDirectoryFinder startingDirectoryFinder;
    private ComboboxObjectCreator comboBoxPopulator;
    private FileInformation fileInformation;
    
    private HashMap<String, FileInformation> clickedFileInformation = new HashMap<String, FileInformation>();

    //Initializes the program
    public void initialize() {
        comboBoxPopulator = new ComboboxObjectCreator(driveDropdownMenu);
        listViewPopulator = new ListViewObjectCreator(fileListView);
        directoryFileFinder = new DirectoryFileFinder();
        startingDirectoryFinder = new StartingDirectoryFinder();

        viewFiles(directoryFileFinder.findFilesInDirectory(startingDirectoryFinder.findDriveLocations()[0]));
        comboBoxPopulator.populateComboBox(startingDirectoryFinder.findDriveLocations());
        currentPath = startingDirectoryFinder.findDriveLocations()[0].getPath();
    }

    //Calls on listViewPopulator to populate the listview with files
    public void viewFiles(List<File> files) {
        listViewPopulator.populateListView(files);
    }

    //Calls on DirectoryFileFinder to find files in given directory
    //TODO needs proper testing
    public void selectDrive() {
        File drive = new File(driveDropdownMenu.getValue().getText());
        viewFiles(directoryFileFinder.findFilesInDirectory(drive));
    }

    //Calls DisplayFileInformation to display information about the file using FileInformation
    public void onFileClicked() throws IOException {
        String clickedObject = fileListView.getSelectionModel().getSelectedItem().getText();
        File clickedFile = new File(currentPath + "\\" + clickedObject);

        if(clickedFileInformation.containsKey(currentPath + "\\" + clickedObject)) {
            fileInformation = clickedFileInformation.get(currentPath + "\\" + clickedObject);
        }
        else {
            fileInformation = new FileInformation(clickedFile);
            clickedFileInformation.put(currentPath + "\\" + clickedObject, fileInformation);
        }

        DisplayFileInformation displayFileInformation = 
            new DisplayFileInformation(fileInformation, fileInformationList, loadingIcon);

        displayFileInformation.displayFileName();
        displayFileInformation.displayFileLocation();
        displayFileInformation.displayFileType();
        //TODO to be implemanted later
        //displayFileInformation.displayNumberOfFiles();
        //displayFileInformation.displayFileSize();
    }

    //Calls on DirectoryFileFinder to find files in given directory when enter is pressed
    @FXML
    public void onEnter(ActionEvent pressedEnter) {
        if(costumPathField.getText().length() > 0) {
            String path = costumPathField.getText();
            File file = new File(path);

            if(file.exists() && file.isDirectory()) {
                List<File> directoryFiles = directoryFileFinder.findFilesInDirectory(file);
                currentPath = path;
                viewFiles(directoryFiles);
            }
            else if(file.exists() && !file.isDirectory()) {
                //TODO
                //vis filinformasjon
            }
            else {
                AlertHandler alertHandler = new AlertHandler();
                Alert alert = alertHandler.createAlert("Error", "Invalid path", "The path you entered is invalid.", Alert.AlertType.ERROR);
                alert.showAndWait();
            }
        }
    }
}
