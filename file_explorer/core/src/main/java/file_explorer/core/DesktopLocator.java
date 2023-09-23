package file_explorer.core;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.filechooser.FileSystemView;

public class DesktopLocator {
    
    public File findDesktopLocation() {
        FileSystemView view = FileSystemView.getFileSystemView();
        File file = view.getHomeDirectory();
        return file;
    }
    
    public static void main(String[] args) {
        DesktopLocator dl = new DesktopLocator();
        File file = dl.findDesktopLocation();
        File[] fileArray = file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".pdf");
            }
        });
        for (File f : fileArray) {
            System.out.println(f.getName());
        }
    }
}
