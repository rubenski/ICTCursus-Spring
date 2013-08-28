package nl.codebasesoftware.produx.domain.dto.entity;

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

    public String getSolrName(){
        return String.format("%s%s%s", urlTitle, SOLR_NAME_SEPARATOR, name);
    }

}
