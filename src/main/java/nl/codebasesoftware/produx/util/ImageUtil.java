package nl.codebasesoftware.produx.util;

import nl.codebasesoftware.produx.util.support.ImageDimensions;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

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

    public static BufferedImage scaleImage(InputStream imageData, int width, int height) {

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        int maxLength = width >= height ? width : height;
        ImageDimensions dimensions = getScaledImageDimensions(bufferedImage, maxLength);

        g.drawImage(bufferedImage, 0, 0, dimensions.getWidth(), dimensions.getHeight(), null);
        g.dispose();

        return resizedImage;
    }

    public static boolean saveImage(BufferedImage image, String filePath) {

        boolean success = false;
        try {
            success = ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static String encodeBase64(File file) {

        BufferedImage img = null;
        String formatName = null;
        ByteArrayOutputStream baos = null;
        org.apache.commons.codec.binary.Base64 base = new org.apache.commons.codec.binary.Base64();
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
            while (imageReaders.hasNext()) {
                ImageReader reader = imageReaders.next();
                formatName = reader.getFormatName();
            }

            img = ImageIO.read(file);
            baos = new ByteArrayOutputStream();
            ImageIO.write(img, formatName, baos);
            baos.flush();
            baos.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

         return base.encodeToString(baos.toByteArray());
    }

}
