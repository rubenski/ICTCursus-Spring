package nl.codebasesoftware.produx.search;

import nl.codebasesoftware.produx.search.solrquery.RangeFacetOtherBehavior;
import org.apache.solr.common.params.ModifiableSolrParams;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 18:39
 * To change this template use File | Settings | File Templates.
 */
public class FacetCreator {

    public static ModifiableSolrParams createFacets(SearchCriteria criteria){
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.add(createNormalFacets(criteria));
        params.add(createRangeFacets(criteria));
        return params;
    }

    private static ModifiableSolrParams createNormalFacets(SearchCriteria criteria){
        ModifiableSolrParams params = new ModifiableSolrParams();
        for (Facet facetField : criteria.getFacetFields()) {
            params.add("facet.field", facetField.getField());
            params.add(String.format("f.%s.facet.sort", facetField.getField()), facetField.getSorting().getValue());
            params.add(String.format("f.%s.facet.mincount", facetField.getField()), "" + facetField.getMinCount());
        }
        return params;
    }

    private static ModifiableSolrParams createRangeFacets(SearchCriteria criteria){

        ModifiableSolrParams params = new ModifiableSolrParams();

        for (RangeFacet rangeFacet : criteria.getRangeFacets()) {

            String fieldStart = String.format("f.%s.facet.range.", rangeFacet.getField());

            params.add(fieldStart +  "start", rangeFacet.getStart().toString());
            params.add(fieldStart +  "end", rangeFacet.getEnd().toString());
            params.add(fieldStart +  "gap", rangeFacet.getGap().toString());
            params.add("facet.range", rangeFacet.getField());
            params.add(String.format("f.%s.facet.sort", rangeFacet.getField()), rangeFacet.getSorting().getValue());
            params.add(String.format("f.%s.facet.mincount", rangeFacet.getField()), "" + rangeFacet.getMinCount());

            if(rangeFacet.hasOther()){
                for(RangeFacetOtherBehavior otherBehavior : rangeFacet.getOtherBehaviors()){
                    params.add(fieldStart + "other", otherBehavior.getValue());
                }
            }
        }

        return params;
    }
}
