package nl.codebasesoftware.produx.comparator;

import nl.codebasesoftware.produx.domain.HighlightedCoursePeriod;

import java.util.Comparator;

/**
 * User: rvanloen
 * Date: 7-5-13
 * Time: 14:19
 */
public class HighlightedCoursesEndDateDescending implements Comparator<HighlightedCoursePeriod> {

    @Override
    public int compare(HighlightedCoursePeriod one, HighlightedCoursePeriod two) {
        return one.getEndTime().compareTo(two.getEndTime());
    }
}
