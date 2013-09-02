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
        for (String facetField : criteria.getFacetFields()) {
            params.add("facet.field", facetField);
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



            if(rangeFacet.hasOther()){
                for(RangeFacetOtherBehavior otherBehavior : rangeFacet.getOtherBehaviors()){
                    params.add(fieldStart + "other", otherBehavior.getValue());
                }
            }

            if(rangeFacet.getMinResultCount() != null){
                params.add("facet.mincount", "" + rangeFacet.getMinResultCount());
            }
        }

        return params;
    }
}
