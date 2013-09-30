package nl.codebasesoftware.produx.search.criteria.filter;

import nl.codebasesoftware.produx.domain.dto.generic.UrlTokenizable;
import nl.codebasesoftware.produx.search.criteria.SolrParameters;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class Filter implements UrlTokenizable, SolrParameters {

    protected String solrFieldName;
    protected String tag;
    protected String urlField;

    protected Filter(String solrFieldName, String urlField) {
        this.solrFieldName = solrFieldName;
        this.urlField = urlField;
    }

    protected Filter(String solrFieldName) {
        this.solrFieldName = solrFieldName;
    }

    public String getSolrFieldName() {
        return solrFieldName;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    protected String getTaggedField() {
        if (tag != null && tag.length() > 0) {
            return String.format("{!tag=%s}%s", tag, solrFieldName);
        }
        return solrFieldName;
    }


}


