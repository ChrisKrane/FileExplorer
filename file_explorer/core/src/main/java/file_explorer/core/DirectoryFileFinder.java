package file_explorer.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class DirectoryFileFinder {

    public static void main(String[] args) {
        DesktopLocator dl = new DesktopLocator();
        DirectoryFileFinder dff = new DirectoryFileFinder();
        List<File> listOfFiles = dff.findFileTypeInDirectory(dl.findDesktopLocation(), ".pdf");
        System.out.println(listOfFiles);
    }

    public List<File> findFilesInDirectory(File directory) {
        File[] filesInDirectory = directory.listFiles();
        
        if(filesInDirectory.length != 0) {
            List<File> listOfFiles = new ArrayList<>();
            for(File file : filesInDirectory) {
                listOfFiles.add(file);
            }
            return listOfFiles;
        }
        throw new IllegalStateException("This directory is empty.");
    }

    public List<File> findFileTypeInDirectory(File directory, String filter) {

        FilenameFilter fileFilter = new FilenameFilter() {
            public boolean accept(File directory , String name) {
                return name.endsWith(filter);
            }
        };
        File[] fileOfTypeInDirectory = directory.listFiles(fileFilter);
        
        if(fileOfTypeInDirectory.length != 0) {
            List<File> listOfFiles = new ArrayList<>();
            for(File file : fileOfTypeInDirectory) {
                listOfFiles.add(file);
            }
            return listOfFiles;
        }
        throw new IllegalStateException("There is no such filetype in this directory.");
    }
}

