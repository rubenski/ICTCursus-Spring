package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.ProductSettingsEntityDTO;
import nl.codebasesoftware.produx.domain.support.CourseListingType;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 30-11-13
 * Time: 15:08
 */
@Embeddable
public class ProductSettings {

    private CourseListingType courseListingType;
    private boolean companyInfoActive;
    private boolean externalCourseLinksActive;

    @Column(columnDefinition="tinyint(1) default 0")
    public CourseListingType getCourseListingType() {
        return courseListingType;
    }

    public void setCourseListingType(CourseListingType courseListingType) {
        this.courseListingType = courseListingType;
    }

    @Column(columnDefinition="tinyint(1) default 0")
    public boolean isCompanyInfoActive() {
        return companyInfoActive;
    }

    public void setCompanyInfoActive(boolean companyInfoActive) {
        this.companyInfoActive = companyInfoActive;
    }

    @Column(columnDefinition="tinyint(1) default 0")
    public boolean isExternalCourseLinksActive() {
        return externalCourseLinksActive;
    }

    public void setExternalCourseLinksActive(boolean externalCourseLinksActive) {
        this.externalCourseLinksActive = externalCourseLinksActive;
    }

    public ProductSettingsEntityDTO toDTO(){
        ProductSettingsEntityDTO pseDTO = new ProductSettingsEntityDTO();
        pseDTO.setExternalCourseLinksActive(externalCourseLinksActive);
        pseDTO.setCompanyInfoActive(companyInfoActive);
        pseDTO.setCourseListingType(courseListingType);
        return pseDTO;
    }
}
