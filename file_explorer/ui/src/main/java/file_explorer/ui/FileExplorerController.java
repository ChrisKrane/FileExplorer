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
    private File selectedFile;

    @FXML private ListView<Label> fileListView;
    @FXML private ComboBox<Label> driveDropdownMenu;
    @FXML private TextField costumPathField;
    @FXML private ListView<Label> fileInformationList;
    @FXML private ProgressIndicator loadingIcon;
    @FXML private Label pathBar;
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
        updatePathBar();
    }

    //Calls on listViewPopulator to populate the ListView with files
    public void viewFiles(List<File> files) {
        listViewPopulator.populateListView(files);
    }

    public void displayFileInformationInView(DisplayFileInformation displayFileInformation) {
        displayFileInformation.displayFileName();
        displayFileInformation.displayFileLocation();
        displayFileInformation.displayFileType();
        //TODO to be implemented later
        //displayFileInformation.displayNumberOfFiles();
        //displayFileInformation.displayFileSize();
    }

    /**
     * Finds files in given directory when the button is clicked.
     */
    //TODO needs proper testing
    public void selectDrive() {
        File drive = new File(driveDropdownMenu.getValue().getText());
        viewFiles(directoryFileFinder.findFilesInDirectory(drive));
        updatePathBar();
    }

    //Calls DisplayFileInformation to display information about the file using FileInformation
    /**
     * Displays information about the file that is clicked by calling on FileInformation and DisplayFileInformation.
     * 
     * @throws IOException
     */
    public void onFileClicked() throws IOException {
        String clickedObject = fileListView.getSelectionModel().getSelectedItem().getText();
        File clickedFile = new File(currentPath + "\\" + clickedObject);
        selectedFile = clickedFile;

        if(clickedFileInformation.containsKey(currentPath + "\\" + clickedObject)) {
            fileInformation = clickedFileInformation.get(currentPath + "\\" + clickedObject);
        }
        else {
            fileInformation = new FileInformation(clickedFile);
            clickedFileInformation.put(currentPath + "\\" + clickedObject, fileInformation);
        }

        DisplayFileInformation displayFileInformation = 
            new DisplayFileInformation(fileInformation, fileInformationList, loadingIcon);
        displayFileInformationInView(displayFileInformation);

        updatePathBar();
    }

    /**
     * Finds files in given directory when enter is pressed.
     * 
     * @param ActionEvent enter
     * @throws IOException
     */
    @FXML
    public void onEnter(ActionEvent pressedEnter) throws IOException {
        if(costumPathField.getText().length() > 0) {
            String path = costumPathField.getText();
            File file = new File(path);

            if(file.exists() && file.isDirectory()) {
                List<File> directoryFiles = directoryFileFinder.findFilesInDirectory(file);
                currentPath = path;
                updatePathBar();
                viewFiles(directoryFiles);
            }
            else if(file.exists() && !file.isDirectory()) {
                List<File> directoryFiles = directoryFileFinder.findFilesInDirectory(file.getParentFile());
                currentPath = file.getParentFile().getPath();
                updatePathBar();
                viewFiles(directoryFiles);

                fileInformation = new FileInformation(file);
                DisplayFileInformation displayFileInformation = 
                    new DisplayFileInformation(fileInformation, fileInformationList, loadingIcon);
                displayFileInformationInView(displayFileInformation);

                // Selects the file that was entered in the textfield in the ListView
                selectFile(file.getName());
            }
            else {
                AlertHandler alertHandler = new AlertHandler();
                Alert alert = alertHandler.createAlert("Error", "Invalid path", "The path you entered is invalid.", Alert.AlertType.ERROR);
                alert.showAndWait();
            }
        }
    }

    /**
     * Finds files in given directory when the button is clicked.
     * Does nothing if directory is empty or if the directory is a file.
     */
    public void goToSelected(ActionEvent event) {
        if(selectedFile != null && selectedFile.isDirectory() && selectedFile.exists()) {
            List<File> directoryFiles = directoryFileFinder.findFilesInDirectory(selectedFile);
            if(!directoryFiles.isEmpty()) {
                currentPath = selectedFile.getPath();
                updatePathBar();
                viewFiles(directoryFiles);
            }
        }
    }

    /**
     * Finds files in given directory when the button is clicked.
     * 
     * @param fileName
     */
    public void selectFile(String fileName) {
        listViewPopulator.getListOfFilenames().forEach(filename -> {
            if(filename.equals(fileName)) {
                fileListView.getSelectionModel().select(listViewPopulator.getListOfFilenames().indexOf(filename));
            }
        });
    }

    /**
     * Updates the path bar with the current path.
     */
    public void updatePathBar() {
        pathBar.setText(currentPath);
    }
}
