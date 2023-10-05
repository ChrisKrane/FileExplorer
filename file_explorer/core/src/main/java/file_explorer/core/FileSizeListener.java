package file_explorer.core;

public interface FileSizeListener {
    
    public void updateNumberOfFiles(int numberOfFiles);
    public void updateFileSize(Long fileSize);
    public void startLoading();
    public void stopLoading();
}
