package nl.codebasesoftware.produx.service.business;

import nl.codebasesoftware.produx.exception.ProduxServiceException;

/**
 * User: rvanloen
 * Date: 28-5-13
 * Time: 9:57
 */
public class ArticleUrl extends BaseUrl {

    public static String createUrl(Long courseId, String category, String title) {
        return String.format("/%s/a%d-%s.html", preprareStringForUrl(category), courseId, preprareStringForUrl(title));
    }

    public static String createAdminUrl(Long id) {
        return String.format("/admin/course/%d", id);
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
