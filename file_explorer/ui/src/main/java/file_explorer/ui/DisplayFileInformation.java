package file_explorer.ui;

import file_explorer.core.FileInformation;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class DisplayFileInformation {

    private FileInformation fileInformation;

    @FXML private TextArea fileInformationArea;
    
    public DisplayFileInformation(FileInformation fileInformation, TextArea fileInformationArea) {
        this.fileInformation = fileInformation;
        this.fileInformationArea = fileInformationArea;
        fileInformation.setFileInformation();
    }

    //Displays information about the file
    public void displayInformation() {
        fileInformationArea.setText("Name: " + fileInformation.getFileName());
        fileInformationArea.appendText("\nFile type: " + fileInformation.getFileType());
        fileInformationArea.appendText("\nFile size: " + fileInformation.getFileSize());
        fileInformationArea.appendText("\nFile location: " + fileInformation.getFileLocation());
    }

}
