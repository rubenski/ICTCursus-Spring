package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 5-9-13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class UrlToSolrFilters {



    public static void urlToFilters(String filters, SearchCriteria criteria){

        String[] filterTokens = filters.split("_");

        for (String filterToken : filterTokens) {
            System.out.println(filterToken);
        }

    }
}
