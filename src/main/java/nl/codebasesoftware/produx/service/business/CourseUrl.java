package nl.codebasesoftware.produx.service.business;


/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 15:46
 */
public class CourseUrl extends BaseUrl {

    public static String createUrl(Long courseId, String category, String title) {
        return String.format("/%s/c%d-%s.html", preprareStringForUrl(category), courseId, preprareStringForUrl(title));
    }

    public static String createAdminUrl(Long id) {
        return String.format("/admin/course/%d", id);
    }
    /*
    public static long extractId(String url) throws ProduxServiceException {
        String s = url.split("-")[0].substring(1);

        long id = -1;
        try {
            id = Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new ProduxServiceException("Url doesn't contain an id");
        }

        return id;
    }*/
}
