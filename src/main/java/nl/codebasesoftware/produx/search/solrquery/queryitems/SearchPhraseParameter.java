package nl.codebasesoftware.produx.search.solrquery.queryitems;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-8-13
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */
public class SearchPhraseParameter implements SolrQueryParameters {

    private final String phrase;

    public SearchPhraseParameter(String phrase) {
        this.phrase = phrase;
    }

    private String encoded(){
        // TODO: Implement encoding here
        return phrase;
    }

    @Override
    public List<String> toQueryParameters() {
        return Arrays.asList(String.format("q=%s", encoded()));
    }
}

