package nl.codebasesoftware.produx.search;

import org.apache.solr.common.params.ModifiableSolrParams;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class NormalFilter extends Filter {

    private final Object value;

    public NormalFilter(String field, Object value) {
        super(field);
        this.value = value;
    }

    @Override
    public String getUrlToken(){
        return String.format("%s:%s", field, value);
    }

    @Override
    public ModifiableSolrParams createSolrParams() {
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.add("fq", String.format("%s:\"%s\"", field, value));
        return params;
    }

    @Override
    protected boolean equalsFilterLink(FacetFilterLink link) {
        return link.getFieldName().equals(getTaggedField()) && link.getValue().equals(value);
    }
}
