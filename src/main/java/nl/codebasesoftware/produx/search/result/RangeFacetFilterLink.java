package nl.codebasesoftware.produx.search.result;


import com.google.common.base.Joiner;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 4-9-13
 * Time: 11:39
 */
public class RangeFacetFilterLink extends FacetFilterLink {

    private Object gap;
    private RangeFilterLinkStrategy rangeLinkStrategy;

    private RangeFacetFilterLink(){}


    @Override
    public String getCompleteUrl() {
        return criteria.getUrlFilterParameters(this);
    }

    @Override
    public String getUrlToken() {
        return rangeLinkStrategy.createUrlToken(field, value, gap);
    }

    @Override
    public String getLabel() {
        return rangeLinkStrategy.createLabel(value, gap);
    }

    @Override
    public String getHrefValue() {
        return getLabel();
    }

    public String getValue() {
        return value;
    }

    public Object getGap() {
        return gap;
    }

    public static class Builder {

        private Integer count;
        private SearchCriteria criteria;
        private String field;
        private Object gap;
        private RangeFilterLinkStrategy rangeLinkStrategy;
        private String value;

        public RangeFacetFilterLink build() {
            validate();
            RangeFacetFilterLink link = new RangeFacetFilterLink();
            link.count = this.count;
            link.criteria = this.criteria;
            link.field = this.field;
            link.gap = this.gap;
            link.rangeLinkStrategy = this.rangeLinkStrategy;
            link.value = this.value;
            return link;
        }


        public void validate(){
            List<String> erroneousFields = new ArrayList<>();

            if(count == null){
                erroneousFields.add("count");
            }
            if(criteria == null){
                erroneousFields.add("criteria");
            }
            if(field == null){
                erroneousFields.add("field");
            }
            if(gap == null){
                erroneousFields.add("gap");
            }
            if(rangeLinkStrategy == null){
                erroneousFields.add("rangeLinkStrategy");
            }
            if(value == null){
                erroneousFields.add("value");
            }
            if(erroneousFields.size() > 0){
                Joiner joiner = Joiner.on(", ");
                String s = joiner.join(erroneousFields);
                throw new IllegalArgumentException("The following fields must be set: " + s);
            }
        }

        public Builder setGap(Object gap) {
            this.gap = gap;
            return this;
        }

        public Builder setRangeLinkStrategy(RangeFilterLinkStrategy rangeLinkStrategy) {
            this.rangeLinkStrategy = rangeLinkStrategy;
            return this;
        }

        public Builder setCount(int count) {
            this.count = count;
            return this;
        }

        public Builder setCriteria(SearchCriteria criteria) {
            this.criteria = criteria;
            return this;
        }

        public Builder setField(String field) {
            this.field = field;
            return this;
        }

        public Builder setValue(String value){
            this.value = value;
            return this;
        }
    }
}
