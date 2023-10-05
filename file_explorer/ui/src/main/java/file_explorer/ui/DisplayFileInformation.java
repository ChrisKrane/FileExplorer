package file_explorer.ui;

import file_explorer.core.FileInformation;
import file_explorer.core.FileSizeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;

public class DisplayFileInformation implements FileSizeListener{

    private FileInformation fileInformation;

    @FXML private ListView<Label> fileInformationList;
    @FXML private ProgressIndicator progressIndicator;
    @FXML private Label fileNameLabel;
    @FXML private Label fileLocationLabel;
    @FXML private Label fileTypeLabel;
    @FXML private Label fileNumberLabel;
    @FXML private Label fileSizeLabel;
    
    public DisplayFileInformation(FileInformation fileInformation, ListView<Label> fileInformationList, ProgressIndicator progressIndicator) {
        this.fileInformation = fileInformation;
        this.fileInformationList = fileInformationList;
        this.progressIndicator = progressIndicator;
        fileInformationList.getItems().clear();
        this.fileNameLabel = new Label();
        this.fileLocationLabel = new Label();
        this.fileTypeLabel = new Label();
        this.fileNumberLabel = new Label();
        this.fileSizeLabel = new Label();
        fileInformation.addListener(this);
    }

    //Displays information about the file
    public void displayFileName() {
        fileInformation.setFileName();
        fileNameLabel.setText(fileInformation.getFileName());
        fileInformationList.getItems().remove(fileNameLabel);
        fileInformationList.getItems().add(fileNameLabel);
    }

    public void displayFileLocation() {
        fileInformation.setFileLocation();
        fileLocationLabel.setText(fileInformation.getFileLocation());
        fileInformationList.getItems().remove(fileLocationLabel);
        fileInformationList.getItems().add(fileLocationLabel);
    }

    public void displayFileType() {
        fileInformation.setFileType();
        fileTypeLabel.setText(fileInformation.getFileType());
        fileInformationList.getItems().remove(fileTypeLabel);
        fileInformationList.getItems().add(fileTypeLabel);
    }

    public void displayNumberOfFiles() {
        fileInformation.setNumberOfFiles();
        fileNumberLabel.setText(Integer.toString(fileInformation.getNumberOfFiles()));
        fileInformationList.getItems().remove(fileNumberLabel);
        fileInformationList.getItems().add(fileNumberLabel);
    }

    public void displayFileSize() {
        fileInformation.setFileSize();
        fileSizeLabel.setText(fileInformation.getFileSize());
        fileInformationList.getItems().remove(fileSizeLabel);
        fileInformationList.getItems().add(fileSizeLabel);
    }

    @Override
    public void updateNumberOfFiles(int numberOfFiles) {
        fileNumberLabel.setText(Integer.toString(numberOfFiles));
        fileInformationList.getItems().remove(fileNumberLabel);
        fileInformationList.getItems().add(fileNumberLabel);
        fileInformationList.refresh();
    }

    @Override
    public void updateFileSize(Long fileSize) {
        fileSizeLabel.setText(Long.toString(fileSize));
        fileInformationList.getItems().remove(fileSizeLabel);
        fileInformationList.getItems().add(fileSizeLabel);
        fileInformationList.refresh();
    }

    @Override
    public void startLoading() {
        progressIndicator.setVisible(true);
    }

    @Override
    public void stopLoading() {
        progressIndicator.setVisible(false);
    }
}
