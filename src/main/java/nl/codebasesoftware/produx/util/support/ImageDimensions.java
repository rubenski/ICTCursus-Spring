package nl.codebasesoftware.produx.util.support;

/**
 * User: rvanloen
 * Date: 30-12-12
 * Time: 14:02
 */
public class ImageDimensions {

    private int width;
    private int height;

    public ImageDimensions(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
