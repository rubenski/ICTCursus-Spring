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
        for (NormalFacetField normalFacetFieldField : criteria.getNormalFacetFieldFields()) {
            params.add(normalFacetFieldField.createSolrParams());
        }
        return params;
    }

    private static ModifiableSolrParams createRangeFacets(SearchCriteria criteria){

        ModifiableSolrParams params = new ModifiableSolrParams();

        for (RangeFacetField rangeFacetField : criteria.getRangeFacetFields()) {
            params.add(rangeFacetField.createSolrParams());
        }

        return params;
    }
}
