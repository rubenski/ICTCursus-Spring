package nl.codebasesoftware.produx.search.criteria.filter;

import org.apache.solr.common.params.ModifiableSolrParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
public class MultiValueNormalFilter extends Filter {

    private final List<? extends Object> values;
    private final FilterConditions condition;

    public MultiValueNormalFilter(String field, List<? extends Object> values, FilterConditions condition) {
        super(field);

        if (values.size() == 0) {
            throw new IllegalArgumentException("No filterLinks found in filter");
        }

        this.values = values;
        this.condition = condition;
    }

    @Override
    public ModifiableSolrParams createSolrParams() {

        ModifiableSolrParams params = new ModifiableSolrParams();

        int size = values.size();

        String valueString = "";

        for (int i = 0; i < size; i++) {
            valueString += (i != 0 && i != size - 1 ? condition.getValue() : "") + values.get(i);
        }

        // fq={!tag=dt}doctype:pdf
        // TODO: implement this for all createSolrParams / filters
        String filter = String.format("%s:(\"%s\")", getTaggedField(), valueString);
        if (tag != null) {
            filter = String.format("{!tag=%s}", tag, filter);
        }

        params.add("fq", filter);
        return params;
    }

    @Override
    public List<String> getUrlTokens() {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (Object value : values) {
            tokens.add(token.append(field).append(":").append(value).toString());
        }

        return tokens;
    }

}
