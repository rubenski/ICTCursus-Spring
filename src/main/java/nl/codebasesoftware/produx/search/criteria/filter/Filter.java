package nl.codebasesoftware.produx.search.criteria.filter;

import nl.codebasesoftware.produx.domain.dto.generic.UrlTokenizable;
import nl.codebasesoftware.produx.search.criteria.SolrParameter;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class Filter implements UrlTokenizable, SolrParameter {

    protected String field;
    protected String tag;

    protected Filter(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    protected String getTaggedField(){
        if(tag != null && tag.length() > 0){
            return String.format("{!tag=%s}%s", tag, field);
        }
        return field;
    }



}


