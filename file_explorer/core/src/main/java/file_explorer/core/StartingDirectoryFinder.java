package file_explorer.core;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class StartingDirectoryFinder {
    
    public File findDesktopLocation() {
        FileSystemView view = FileSystemView.getFileSystemView();
        File file = view.getHomeDirectory();
        return file;
    }

    public File[] findDriveLocations() {
        File[] file = File.listRoots();
        return file;
    }

    //getdefaultdirectory = documents
    
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
