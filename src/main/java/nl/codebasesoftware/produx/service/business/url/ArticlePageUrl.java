package nl.codebasesoftware.produx.service.business.url;


/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 14-6-13
 * Time: 20:47
 */
public class ArticlePageUrl extends AbstractUrl {

    public static String create(Long articleId, String category, int pageNumber, String title) {
        return String.format("/%s/a%d/p%d-%s", preprareStringForUrl(category), articleId, pageNumber, preprareStringForUrl(title));
    }

    /*
    // Method to extract the page number. The mapping "/{category}/a{aid:[0-9]+}/p[0-9]-{title}" is not chosen
    // unfortunately. Probably a Spring bug.
    public static PageNumberAndTitle extractPageNumberAndTitle(String title) throws ProduxServiceException {
        Pattern p = Pattern.compile("p([0-9]+)[-]((.*))");
        Matcher m = p.matcher(title);
        PageNumberAndTitle idAndTitle = null;
        if (m.matches()) {
            idAndTitle = new PageNumberAndTitle();
            for (int i = 0; i < m.groupCount(); i++) {
                String group = m.group(i);
                if (i == 1) {
                    idAndTitle.pageNumber = Integer.parseInt(group);
                } else if (i == 2) {
                    idAndTitle.urlTitle = group;
                }
            }
        }else {
            throw new ProduxServiceException("Invalid article page url");
        }

        return idAndTitle;
    } */
}
