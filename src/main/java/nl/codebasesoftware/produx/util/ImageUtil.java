package nl.codebasesoftware.produx.util;

import nl.codebasesoftware.produx.util.support.ImageDimensions;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * User: rvanloen
 * Date: 30-12-12
 * Time: 14:01
 */
public class ImageUtil {

    private static ImageDimensions getScaledImageDimensions(BufferedImage image, int desiredMaxLength) {

        int width = image.getWidth();
        int height = image.getHeight();

        int longestLength = 0;
        if (width >= height) {
            longestLength = width;
        } else {
            longestLength = height;
        }

        double divideBy = (double) longestLength / desiredMaxLength;

        int newWidth = (int) Math.floor(width / divideBy);
        int newHeight = (int) Math.floor(height / divideBy);

        return new ImageDimensions(newWidth, newHeight);
    }

    public static byte[] resizeWithImgScalr(InputStream imageData, int maxLength){
        BufferedImage result = null;
        try {
            BufferedImage source = ImageIO.read(imageData);
            ImageDimensions dimensions = getScaledImageDimensions(source, maxLength);
            result = Scalr.resize(source, Scalr.Method.ULTRA_QUALITY, dimensions.getWidth(), dimensions.getHeight());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return toBytes(result);
    }

    private static byte[] toBytes(BufferedImage bufferedImage) {
        byte[] imageBytes = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBytes;
    }

    public static void saveImage(byte[] bytes, String filePath){

        try {
            File f = new File(filePath);
            OutputStream os = new FileOutputStream(f);
            IOUtils.write(bytes, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
