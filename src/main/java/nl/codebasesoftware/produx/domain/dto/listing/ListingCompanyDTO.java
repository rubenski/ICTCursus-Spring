package nl.codebasesoftware.produx.domain.dto.listing;

import nl.codebasesoftware.produx.domain.dto.LogoUrl;
import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.ProductSettingsEntityDTO;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 28-8-13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class ListingCompanyDTO extends DomainEntityDTO {

    protected Long id;
    protected String name;
    protected boolean logo;
    protected ProductSettingsEntityDTO productSettings;

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

    public void setLogo(boolean logo) {
        this.logo = logo;
    }

    // Utility methods
    public String getNormalLogoUrl() {
        return LogoUrl.getNormalAbsUrl(id);
    }

    public String getSmallLogoUrl() {
        return LogoUrl.getSmallAbsUrl(id);
    }

    public boolean hasLogo() {
        return logo;
    }

    public ProductSettingsEntityDTO getProductSettings() {
        return productSettings;
    }

    public void setProductSettings(ProductSettingsEntityDTO productSettings) {
        this.productSettings = productSettings;
    }
}
