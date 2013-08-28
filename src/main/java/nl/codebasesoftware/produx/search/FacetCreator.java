package nl.codebasesoftware.produx.search;

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

            params.add("facet.field", rangeFacet.getField());
            params.add(fieldStart +  "start", rangeFacet.getStart().toString());
            params.add(fieldStart +  "end", rangeFacet.getEnd().toString());
            params.add(fieldStart +  "gap", rangeFacet.getGap().toString());

            if(rangeFacet.hasOther()){
                params.add(fieldStart + "other", rangeFacet.getOther());
            }
        }

        return params;
    }
}
