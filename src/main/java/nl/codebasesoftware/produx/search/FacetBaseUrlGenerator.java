package nl.codebasesoftware.produx.search;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 4-9-13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public class FacetBaseUrlGenerator {

    public static String generate(SearchCriteria criteria){

        StringBuilder builder = new StringBuilder();

        for (RangeFilter rangeFilter : criteria.getRangeFilters()) {
            builder.append(rangeFilter.getUrlToken());
        }

        return builder.toString();
    }

}
