package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.HighlightedCoursePeriod;
import nl.codebasesoftware.produx.domain.support.DateRange;

import java.util.Date;
import java.util.List;

/**
 * User: rvanloen
 * Date: 11-5-13
 * Time: 11:04
 */
public interface HighlightedCoursePeriodService {
    void addHighlightedCourse(Long courseId, Long categoryId, Date startDate, int numberOfMonths);

    DateRange findDateRangeForHighlightStart(long categoryId);

    DateRange findDateRangeForHighlightStart(Category category);

    List<HighlightedCoursePeriod> findHighlightedForCompany(long companyId);

}
