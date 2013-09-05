package nl.codebasesoftware.produx.search;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import org.apache.solr.common.params.ModifiableSolrParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
public class FilterCreator {

    public static ModifiableSolrParams createFilters(SearchCriteria criteria) {
        ModifiableSolrParams params = new ModifiableSolrParams();
        String param = "";

        if(criteria.getCategories().size() > 0){
            List<String> names = new ArrayList<>();
            for (CategoryEntityDTO category : criteria.getCategories()) {
                names.add(category.getSolrValue());
            }
            param += createMultiValueFilter(names, "category");
        }

        if(criteria.getTagIds().size() > 0){
            param += " AND " + createMultiValueFilter(criteria.getTagIds(), "tag_id");
        }

        if(criteria.getRegionIds().size() > 0){
            param += " AND " + createMultiValueFilter(criteria.getRegionIds(), "region_id");
        }

        if(criteria.getRangeFilters().size() > 0){
            param += " AND " + createRangeFilters(criteria.getRangeFilters(), "price");
        }

        params.add("fq", param);
        return params;
    }

    private static String createMultiValueFilter(List<? extends Object> values, String fieldName) {

        int size = values.size();

        if (size == 0) {
            return "";
        }

        String valueString = "";
        for (int i = 0; i < size; i++) {
            valueString += (i != 0 && i != size - 1 ? " OR " : "") + values.get(i);
        }
        String filter = String.format("%s:(\"%s\")", fieldName, valueString);

        return filter;
    }


    private static String createRangeFilters(List<? extends RangeFilter> ranges, String fieldName) {
        String filter = "";
        for (int i = 0; i < ranges.size(); i++) {
            RangeFilter range = ranges.get(i);
            filter += String.format("%s:[%d TO %d]", fieldName, range.getMin(), range.getMax())
                    + (i < ranges.size() - 1 ? " OR " : "");
        }
        return filter;
    }

}
