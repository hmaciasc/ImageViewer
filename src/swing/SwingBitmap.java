package swing;

import java.awt.image.BufferedImage;
import model.Bitmap;

public class SwingBitmap implements Bitmap{
    private BufferedImage bufferedImage;

    public SwingBitmap(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
    
    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }
}
