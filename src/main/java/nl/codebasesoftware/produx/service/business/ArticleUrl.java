package nl.codebasesoftware.produx.service.business;

/**
 * User: rvanloen
 * Date: 28-5-13
 * Time: 9:57
 */
public class ArticleUrl extends AbstractUrl {

    public static String createArticleUrl(Long articleId, String category, String title) {
        return String.format("/%s/a%d/%s", preprareStringForUrl(category), articleId, preprareStringForUrl(title));
    }


}
