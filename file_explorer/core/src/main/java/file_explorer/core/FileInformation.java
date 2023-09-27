package file_explorer.core;

import java.util.HashMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

public class FileInformation {
    
    private File file;
    private boolean isDirectory;
    private String fileName;
    private String fileType;
    private String fileSize;
    private String fileLocation;
    private Icon fileIcon;

    //hashmap probably not needed, only one file is being stored per instance of FileInformation
    private HashMap<String, String> fileSizeCache = new HashMap<String, String>();

    private Path path;
    private BasicFileAttributes attributes; 

    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();

    //Constructor
    public FileInformation(File file) throws IOException{
        this.file = file;
        this.isDirectory = file.isDirectory();
        this.path = file.toPath();
        this.attributes = Files.readAttributes(path, BasicFileAttributes.class);
        setFileInformation();
    }

    //Sets the file information 
    public void setFileInformation() {
        fileName = file.getName();
        fileType = fileSystemView.getSystemTypeDescription(file);
        fileSize = getFileSize(file);
        fileLocation = file.getAbsolutePath();
    }

    //Gets the file size
    public String getFileSize(File file) {
        long bytes = file.length();
        if(fileSizeCache.containsKey(file.getPath())) {
            return getSavedFileSize(file.getPath());
        }
        else if(isDirectory && file.listFiles().length > 0) {
            bytes = getDirectorySize(file);
        }

        if(bytes > 0) {
            if(bytes < 1024) {
                saveFileSize(file.getPath(), bytes + " bytes");
                return bytes + " bytes";
            }
            else if(bytes < 1048576) {
                saveFileSize(file.getPath(), bytes / 1024 + " KB");
                return bytes / 1024 + " KB";
            }
            else if(bytes < 1073741824) {
                saveFileSize(file.getPath(), bytes / 1048576 + " MB");
                return bytes / 1048576 + " MB";
            }
            else {
                saveFileSize(file.getPath(), bytes / 1073741824 + " GB");
                return bytes / 1073741824 + " GB";
            }
        }
        return "is empty";
    }

    //Gets the size of a directory
    public Long getDirectorySize(File directory) {
        Long size = 0L;
        File[] files = directory.listFiles();
        if(files != null) {
            for(File file : files) {
                if(file.isFile()) {
                    size += file.length();
                }
                else {
                    size += getDirectorySize(file);
                }
            }
        }
        else {
            return 0L;
        }
        return size;
    }

    public void saveFileSize(String path, String size) {
        fileSizeCache.put(path, size);
    }

    public String getSavedFileSize(String path) {
        if(!fileSizeCache.isEmpty()) {
            return fileSizeCache.get(path);
        }
        throw new IllegalStateException("Cache error");
    }

    //Getters for file information
    public boolean isDirectory() {
        return isDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public Icon getFileIcon() {
        return fileIcon;
    }
}
