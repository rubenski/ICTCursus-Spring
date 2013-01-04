package nl.codebasesoftware.produx.util.support;

/**
 * User: rvanloen
 * Date: 31-12-12
 * Time: 13:12
 */
public enum ImageType {

    JPG("image/jpeg"),
    PJPG("image/pjpeg"),
    GIF("image/gif"),
    PNG("image/png");

    ImageType(String type) {
        this.type = type;
    }

    private final String type;

    @Override
    public String toString() {
        return type;
    }

    public static ImageType get(String type) {
        for (ImageType s : values()) {
            if (s.type.equals(type)) {
                return s;
            }
        }
        return null;
    }

}
