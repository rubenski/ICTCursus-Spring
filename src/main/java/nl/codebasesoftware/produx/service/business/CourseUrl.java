package nl.codebasesoftware.produx.service.business;

import nl.codebasesoftware.produx.exception.ProduxServiceException;

import java.util.regex.Pattern;

/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 15:46
 */
public class CourseUrl {

    public static String createUrl(Long courseId, String category, String title) {
        return String.format("/c/%s/%d-%s.html", preprareStringForUrl(category), courseId, preprareStringForUrl(title));
    }

    public static String createAdminUrl(Long id) {
        return String.format("/admin/course/%d", id);
    }

    private static String preprareStringForUrl(String title) {
        Pattern specialCharsPattern = Pattern.compile("([^a-zA-z0-9])");
        String s = specialCharsPattern.matcher(title).replaceAll("-");
        // Remove a potential trailing hyphen
        if (s.endsWith("-")) {
            s = s.substring(0, s.length() - 1);
        }

        String replacedRepeatingHyphens = s.replaceAll("[-]+", "-");
        String toLower = replacedRepeatingHyphens.toLowerCase();
        return toLower;
    }

    public static long extractId(String url) throws ProduxServiceException {
        String s = url.split("-")[0];

        long id = -1;
        try {
            id = Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new ProduxServiceException("Url doesn't contain an id");
        }

        return id;
    }


}
