package nl.codebasesoftware.produx.service.business;

import nl.codebasesoftware.produx.search.criteria.filter.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 11-9-13
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public class FilterFromUrlExtractor {


    private Map<String, String> urlToSorlFieldMapping = new HashMap<>();

    public FilterFromUrlExtractor() {
        urlToSorlFieldMapping.put("regions", "region_ids");
        urlToSorlFieldMapping.put("tags", "tag_ids");
    }

    public List<Filter> stringToFilters(String filters){

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

        Filter priceFilter = extractPriceFilter(filterMap);
        if(priceFilter != null){
            filterList.add(priceFilter);
        }

        Filter regionsFilter = extractMultiValueNormalFilter(filterMap, "regions", FilterConditions.OR);
        if(regionsFilter != null){
            filterList.add(regionsFilter);
        }

        Filter tagsFilter = extractMultiValueNormalFilter(filterMap, "tags", FilterConditions.OR);
        if(tagsFilter != null){
            filterList.add(tagsFilter);
        }

        return filterList;
    }

    private Filter extractMultiValueNormalFilter(Map<String, List<String>> filters, String field, FilterConditions filterCondition){
        List<String> values = filters.get(field);
        if(values == null || values.size() == 0){
            return null;
        }

        String solrTargetField = urlToSorlFieldMapping.get(field);
        if(solrTargetField == null){
            solrTargetField = field;
        }

        MultiValueNormalFilter filter = new MultiValueNormalFilter(solrTargetField, field, values, filterCondition);
        filter.setTag(String.format("_%s", field));
        return filter;
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

        MultiValueRangeFilter priceFilter = new MultiValueRangeFilter("price", "price", ranges);
        priceFilter.setTag("_price");

        return priceFilter;
    }
}
