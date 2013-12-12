package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.support.CourseListingType;

/**
 * User: rvanloen
 * Date: 30-11-13
 * Time: 15:14
 */
public class ProductSettingsEntityDTO {

    private CourseListingType courseListingType;
    private boolean companyInfoActive;
    private boolean externalCourseLinksActive;

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

    public double getPercentagePerRequest(){
        return 1.0 + (companyInfoActive ? 0.5 : 0.0) + (courseListingType.equals(CourseListingType.Uitgebreid) ? 0.5 : 0.0);
    }

}
