package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.dto.generic.UrlToken;
import nl.codebasesoftware.produx.search.SolrParameter;
import org.apache.solr.common.params.ModifiableSolrParams;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 15-8-13
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
public class CategoryEntityDTO extends DomainEntityDTO {

    private  static final String SOLR_NAME_SEPARATOR = "---";

    private Long id;
    private String name;
    private String urlTitle;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getSolrValue(){
        return String.format("%d%s%s%s%s", id, SOLR_NAME_SEPARATOR, urlTitle, SOLR_NAME_SEPARATOR, name);
    }

    public static CategoryEntityDTO fromSolrValue(String solrValue){
        String[] strings = solrValue.split(SOLR_NAME_SEPARATOR);
        long id = Long.parseLong(strings[0]);
        String urlTitle = strings[1];
        String name = strings[2];
        CategoryEntityDTO dto = new CategoryEntityDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setUrlTitle(urlTitle);
        return dto;
    }

}
