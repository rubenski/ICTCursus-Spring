package nl.codebasesoftware.produx.service.business;

import nl.codebasesoftware.produx.search.criteria.filter.Filter;
import nl.codebasesoftware.produx.search.criteria.filter.MultiValueRangeFilter;
import nl.codebasesoftware.produx.search.criteria.filter.Range;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 11-9-13
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public class SearchQueryProcessor {

    public static List<Filter> stringToFilters(String filters){

        if(filters == null || filters.length() == 0){
            return Collections.EMPTY_LIST;
        }

        Map<String, List<String>> filterMap = new HashMap<>();
        List<Filter> filterList = new ArrayList<>();

        String[] filterStrings = filters.split("~");

        for (String filterString : filterStrings) {

            String[] nameValue = filterString.split(":");
            String name = nameValue[0];
            String value = nameValue[1];

            List<String> filterValues = filterMap.get(name);
            if(filterValues == null){
                filterValues = new ArrayList<>();
                filterMap.put(name, filterValues);
            }

            filterValues.add(value);

        }

        filterList.add(extractPriceFilter(filterMap));

        return filterList;
    }

    private static Filter extractPriceFilter(Map<String, List<String>> filters){

        List<String> priceRanges = filters.get("price");

        if(priceRanges == null || priceRanges.size() == 0){
            return null;
        }

        List<Range> ranges = new ArrayList<>();
        for (String priceRange : priceRanges) {
            String[] rangeValues = priceRange.split("-");
            int from = Integer.parseInt(rangeValues[0]);
            int to = Integer.parseInt(rangeValues[1]);
            ranges.add(new Range<>(from, to));
        }

        MultiValueRangeFilter priceFilter = new MultiValueRangeFilter("price", ranges);
        priceFilter.setTag("_price");

        return priceFilter;
    }
}
