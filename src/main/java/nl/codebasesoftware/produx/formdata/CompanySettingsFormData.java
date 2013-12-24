package nl.codebasesoftware.produx.formdata;

import nl.codebasesoftware.produx.util.StringUtil;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 13:54
 */
public class CompanySettingsFormData {

    private Long companyId;
    private String budgetTriggerAmount;
    //private String courseRequestEmailAddress;
    private boolean allCoursesDeactivated;

    public boolean isAllCoursesDeactivated() {
        return allCoursesDeactivated;
    }

    public void setAllCoursesDeactivated(boolean allCoursesDeactivated) {
        this.allCoursesDeactivated = allCoursesDeactivated;
    }

    public String getBudgetTriggerAmount() {
        return budgetTriggerAmount;
    }

    public void setBudgetTriggerAmount(String budgetTriggerAmount) {
        this.budgetTriggerAmount = budgetTriggerAmount;
    }

    public Integer getBudgetTriggerAmountInCents(){

        if(StringUtil.isNullOrEmpty(budgetTriggerAmount)) return null;

        NumberFormat nf = NumberFormat.getInstance();
        Number number = null;

        try {
            number = nf.parse(budgetTriggerAmount);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int i = number.intValue() * 100;
        return i;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /*
    public String getCourseRequestEmailAddress() {
        return courseRequestEmailAddress;
    }

    public void setCourseRequestEmailAddress(String courseRequestEmailAddress) {
        this.courseRequestEmailAddress = courseRequestEmailAddress;
    }
    */
}
