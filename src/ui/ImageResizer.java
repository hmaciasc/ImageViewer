package ui;

public class ImageResizer {
    private final int width;
    private final int height;

    public ImageResizer(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public ImageSize resize(int width, int height){
        double wRatio = 1.0 * this.width / width;
        double hRatio = 1.0 * this.height / height;
        if (hRatio < wRatio) {
            return new ImageSize((int) (width*hRatio), (int) (height * hRatio));
        }
        return new ImageSize((int) (width*wRatio), (int) (height * wRatio));
    }
    
    public class ImageSize {
        private final int width;
        private final int height;

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
