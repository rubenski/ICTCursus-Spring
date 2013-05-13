package nl.codebasesoftware.produx.comparator;

import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;

import java.util.Comparator;

/**
 * User: rvanloen
 * Date: 7-5-13
 * Time: 14:19
 */
public class HighlightedCoursesEndDataDescending implements Comparator<HighlightedCourseOnCategory> {

    @Override
    public int compare(HighlightedCourseOnCategory o1, HighlightedCourseOnCategory o2) {
        return o1.getEndTime().compareTo(o2.getEndTime());
    }
}
