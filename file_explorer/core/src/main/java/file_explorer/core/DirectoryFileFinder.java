package file_explorer.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class DirectoryFileFinder {

    public static void main(String[] args) {
        
        StartingDirectoryFinder dl = new StartingDirectoryFinder();
        /*
        DirectoryFileFinder dff = new DirectoryFileFinder();
        List<File> listOfFiles = dff.findFilesInDirectory(dl.findDesktopLocation());
        for(File file : listOfFiles) {
            System.out.println(file.getName());
        }
        */
        File[] roots = File.listRoots();
        for(File file : roots) 
            System.out.println(file.getPath());
            System.out.println(dl.findDesktopLocation().getParent());
        }   

    //Finner alle filer og mapper i en gitt mappe
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

    //Finner alle filer av en gitt type i en gitt mappe
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

