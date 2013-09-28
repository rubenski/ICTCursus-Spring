package nl.codebasesoftware.produx.search.result;

import com.google.common.base.Joiner;
import nl.codebasesoftware.produx.search.SolrIdName;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 3-9-13
 * Time: 17:05
 */
public class NormalFacetFilterLink extends FacetFilterLink {

    private NormalFacetFilterLink() {
    }


    @Override
    public String getCompleteUrl() {
        return criteria.getUrlFilterParameters(this);
    }

    @Override
    public String getUrlToken() {
        return String.format("%s:%s", field, getHrefValue());
    }

    @Override
    public String getLabel() {
        boolean b = SolrIdName.isIdAndName(value);
        if (b) {
            return SolrIdName.createFromSolr(value).getName();
        }
        return value;
    }

    @Override
    public String getHrefValue() {
        boolean b = SolrIdName.isIdAndName(value);
        if (b) {
            return SolrIdName.createFromSolr(value).getId();
        }
        return value;
    }


    public static class Builder {

        private String field;
        private Long count;
        private SearchCriteria criteria;
        private String value;

        public NormalFacetFilterLink build() {
            validate();
            NormalFacetFilterLink link = new NormalFacetFilterLink();
            link.count = this.count;
            link.criteria = this.criteria;
            link.field = this.field;
            link.value = this.value;
            return link;
        }

        public void validate() {
            List<String> erroneousFields = new ArrayList<>();

            if (count == null) {
                erroneousFields.add("count");
            }
            if (criteria == null) {
                erroneousFields.add("criteria");
            }
            if (field == null) {
                erroneousFields.add("field");
            }
            if (value == null) {
                erroneousFields.add("value");
            }
            if (erroneousFields.size() > 0) {
                Joiner joiner = Joiner.on(", ");
                String s = joiner.join(erroneousFields);
                throw new IllegalArgumentException("The following fields must be set: " + s);
            }
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setField(String field) {
            this.field = field;
            return this;
        }

        public Builder setCount(long count) {
            this.count = count;
            return this;
        }

        public Builder setCriteria(SearchCriteria criteria) {
            this.criteria = criteria;
            return this;
        }


    }

}
