package filesystem;

import java.io.File;
import java.io.FileFilter;
import model.Image;
import persistence.ImageLoader;
import swing.SwingProxyImage;

public class FileImageLoader implements ImageLoader{

    private final File file;

    public FileImageLoader(File file) {
        this.file = file;
    }
    
    public FileImageLoader (String filename){
        this(new File(filename));
    }
    
    @Override
    public Image load() {
        File[] files = getFiles(file.getParentFile());
        Image[] images = link(map(files));
        return images[indexOf(file, files)];
    }

    private File[] getFiles(File parentFile) {
        final String[] fileExtensions = {"png", "jpg", "jpeg"};
        return parentFile.listFiles(new FileFilter(){
            @Override
            public boolean accept(File file){
                for (String fileExtension : fileExtensions) {
                    if (file.getName().endsWith(fileExtension)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private int indexOf(File file, File[] files) {
        for (int i = 0; i < files.length; i++) {
            if (files[i].equals(file)) {
                return i;
            }
        }
        return 0;
    }
    
    public Image[] map(File[] files){
        Image[] images = new Image[files.length];
        for (int i = 0; i < files.length; i++) {
            images[i] = new SwingProxyImage(files[i]);
        }
        return images;
    }
    
    private Image[] link(Image[] images){
        linkNext(images);
        linkPrev(images);
        return images;
    }

    private void linkNext(Image[] images) {
        for (int i = 0; i < images.length - 1; i++) {
            images[i].setNext(images[i+1]);
        }
        images[images.length-1].setNext(images[0]);
    }

    private void linkPrev(Image[] images) {
        for (int i = 1; i < images.length; i++) {
            images[i].setPrev(images[i-1]);
        }
        images[0].setPrev(images[images.length-1]);
    }
}
