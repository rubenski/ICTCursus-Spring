package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 9-5-13
 * Time: 0:44
 */
public class HighlightCourseFormData {
    long course;
    long category;
    String startDate;
    int numberOfMonths;

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public long getCourse() {
        return course;
    }

    public void setCourse(long course) {
        this.course = course;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
