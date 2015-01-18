package swing;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import model.Image;
import ui.ImageDisplay;
import ui.Resizer;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private Image image;
    
    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage bufferedImage = getBufferedImage();
        Rectangle box = calculateBox(bufferedImage);
        g.drawImage(bufferedImage, box.x, box.y, box.width, box.height, null);
    }
    
    private Rectangle calculateBox(BufferedImage bufferedImage){
        Resizer.ImageSize size = new Resizer(this.getWidth(),
                this.getHeight()).resize(bufferedImage.getWidth(),
                                         bufferedImage.getHeight());
        return new Rectangle(
                (this.getWidth() - size.getWidth()) / 2,
                (this.getHeight() - size.getHeight()) / 2,
                size.getWidth(),
                size.getHeight()
        );
    }
    

    private BufferedImage getBufferedImage() {
        return getSwingBitmap().getBufferedImage();
    }

    private SwingBitmap getSwingBitmap() {
        return (SwingBitmap) image.getBitmap();
    }
}
