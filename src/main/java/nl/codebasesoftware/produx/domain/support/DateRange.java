package nl.codebasesoftware.produx.domain.support;

import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 7-5-13
 * Time: 16:17
 */
public class DateRange {
    private Calendar startDate;
    private Calendar endDate;

    public DateRange(Calendar startDate, Calendar endDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public boolean isInRange(Calendar calendar){
        return (calendar.after(startDate) && calendar.before(endDate));
    }
}
