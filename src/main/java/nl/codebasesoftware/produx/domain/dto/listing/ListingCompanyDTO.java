package nl.codebasesoftware.produx.domain.dto.listing;

import nl.codebasesoftware.produx.domain.dto.LogoUrl;
import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 28-8-13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class ListingCompanyDTO extends DomainEntityDTO {

    private Long id;
    private String name;

    public ListingCompanyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSmallLogoUrl(){
        return LogoUrl.getSmallAbsUrl(id);
    }
}
