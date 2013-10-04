package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.comparator.RankOrderable;
import nl.codebasesoftware.produx.search.SolrIdName;
import nl.codebasesoftware.produx.search.SolrNameAndId;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 16-8-13
 * Time: 8:32
 * To change this template use File | Settings | File Templates.
 */
public class RegionEntityDTO extends DomainEntityDTO implements SolrNameAndId, RankOrderable {

    private Long id;
    private String name;
    private int displayRank;

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

    public int setDisplayRank() {
        return displayRank;
    }

    public void setDisplayRank(int displayRank) {
        this.displayRank = displayRank;
    }

    @Override
    public String getSolrIdName() {
        return SolrIdName.createForSolr(id, name);
    }

    @Override
    public int getDisplayRank() {
        return displayRank;
    }

}
