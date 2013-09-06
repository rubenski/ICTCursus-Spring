package nl.codebasesoftware.produx.search;

import nl.codebasesoftware.produx.domain.dto.generic.UrlToken;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class Filter implements UrlToken, SolrParameter {

    protected String field;

    protected Filter(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
