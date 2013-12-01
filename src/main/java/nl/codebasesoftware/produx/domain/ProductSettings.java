package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.support.CourseListingType;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

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
}
