package nl.codebasesoftware.produx.search;


import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 4-9-13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class RangeFacetFilterLink extends FacetFilterLink {


    private final int gap;
    private final int value;


    public RangeFacetFilterLink(String fieldName, int value, int count, Integer gap, CategoryEntityDTO categoryEntityDTO, SearchCriteria criteria) {
        super(fieldName, value, count, categoryEntityDTO, criteria);
        this.value = value;
        this.gap = gap;
    }


    @Override
    public String getNameKey() {
        return String.format("rangefacet.%s.%d", fieldName, value);
    }

    @Override
    public String getUrl() {

        StringBuilder builder = new StringBuilder();
        builder.append("/").append(category.getUrlTitle()).append("/");

        for (Filter filter : criteria.getFilters()) {
            if(!filter.equalsFilterLink(this) && !filter.getField().equals("category")){
                builder.append(filter.getUrlToken()).append(CommonParams.SPLIT_FILTERS);
            }
        }

        if(!criteria.isFilterApplied(this)){
            builder.append(fieldName).append(CommonParams.SPLIT_FIELD_VALUE).append(value).append(CommonParams.SPLIT_RANGE_VALUES).append(value + gap);
        } else{
            builder.deleteCharAt(builder.length() - 1);
        }



        return builder.toString();
    }




}
