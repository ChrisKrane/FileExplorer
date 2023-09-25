package file_explorer.core;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class StartingDirectoryFinder {
    
    //Bruker FileSystemView til å finne brukerens skrivebord
    public File findDesktopLocation() {
        FileSystemView view = FileSystemView.getFileSystemView();
        File file = view.getHomeDirectory();
        return file;
    }

    //Bruker File.listRoots() til å finne alle stasjoner på maskinen
    public File[] findDriveLocations() {
        File[] file = File.listRoots();
        return file;
    }

    //getdefaultdirectory = documents
    
    //Main metode for enkel testing
    public static void main(String[] args) {
        StartingDirectoryFinder dl = new StartingDirectoryFinder();
        File[] file = dl.findDriveLocations();
        //File[] fileArray = file.listFiles();
        for (File f : file) {
            if(f.isDirectory()) {
                System.out.println(f.getPath());
            }
            
        }
    }
}
