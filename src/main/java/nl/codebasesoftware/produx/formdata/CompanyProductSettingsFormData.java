package nl.codebasesoftware.produx.formdata;

import nl.codebasesoftware.produx.domain.dto.entity.ProductSettingsEntityDTO;
import nl.codebasesoftware.produx.domain.support.CourseListingType;

import java.util.List;

/**
 * User: rvanloen
 * Date: 30-11-13
 * Time: 16:51
 */

public class CompanyProductSettingsFormData {

    private CourseListingType courseListingType;
    private boolean companyInfoActive;
    private boolean externalCourseLinksActive;
    private CourseListingType[] listingTypes = CourseListingType.values();

    public CompanyProductSettingsFormData(ProductSettingsEntityDTO productSettings) {
        courseListingType = productSettings.getCourseListingType();
        companyInfoActive = productSettings.isCompanyInfoActive();
        externalCourseLinksActive = productSettings.isExternalCourseLinksActive();
    }

    public CourseListingType getCourseListingType() {

        return courseListingType;
    }

    public void setCourseListingType(CourseListingType courseListingType) {
        this.courseListingType = courseListingType;
    }

    public boolean isCompanyInfoActive() {
        return companyInfoActive;
    }

    public void setCompanyInfoActive(boolean companyInfoActive) {
        this.companyInfoActive = companyInfoActive;
    }

    public boolean isExternalCourseLinksActive() {
        return externalCourseLinksActive;
    }

    public void setExternalCourseLinksActive(boolean externalCourseLinksActive) {
        this.externalCourseLinksActive = externalCourseLinksActive;
    }

    public CourseListingType[] getListingTypes() {
        return listingTypes;
    }

    public void setListingTypes(CourseListingType[] listingTypes) {
        this.listingTypes = listingTypes;
    }
}

