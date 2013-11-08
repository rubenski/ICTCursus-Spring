package nl.codebasesoftware.produx.service.business.url;


/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 15:46
 */
public class CourseUrl extends AbstractUrl {

    public static String createUrl(Long courseId, String category, String title) {
        return String.format("/%s/%d/%s", preprareStringForUrl(category), courseId, preprareStringForUrl(title));
    }

    public static String createAdminUrl(Long id) {
        return String.format("/admin/course/%d", id);
    }
}
