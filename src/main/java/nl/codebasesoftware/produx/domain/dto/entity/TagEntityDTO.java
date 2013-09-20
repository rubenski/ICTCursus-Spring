package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.search.SolrIdName;
import nl.codebasesoftware.produx.search.SolrNameAndId;

/**
 * User: rvanloen
 * Date: 21-12-12
 * Time: 16:45
 */
public class TagEntityDTO extends DomainEntityDTO implements SolrNameAndId {
    private Long id;
    private String name;

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

    @Override
    public String getSolrIdName() {
        return SolrIdName.createForSolr(id, name);
    }
}
