package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 13:54
 */
public class CompanySettingsFormData {

    private Long companyId;
    private Integer budgetTriggerAmount;
    private String courseRequestEmailAddress;
    private boolean allCoursesDeactivated;

    public boolean isAllCoursesDeactivated() {
        return allCoursesDeactivated;
    }

    public void setAllCoursesDeactivated(boolean allCoursesDeactivated) {
        this.allCoursesDeactivated = allCoursesDeactivated;
    }

    public Integer getBudgetTriggerAmount() {
        return budgetTriggerAmount;
    }

    public void setBudgetTriggerAmount(Integer budgetTriggerAmount) {
        this.budgetTriggerAmount = budgetTriggerAmount;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCourseRequestEmailAddress() {
        return courseRequestEmailAddress;
    }

    public void setCourseRequestEmailAddress(String courseRequestEmailAddress) {
        this.courseRequestEmailAddress = courseRequestEmailAddress;
    }
}
