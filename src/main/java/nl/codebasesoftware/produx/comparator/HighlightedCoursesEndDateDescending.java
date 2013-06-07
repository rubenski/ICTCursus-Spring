package nl.codebasesoftware.produx.comparator;

import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;

import java.util.Comparator;

/**
 * User: rvanloen
 * Date: 7-5-13
 * Time: 14:19
 */
public class HighlightedCoursesEndDateDescending implements Comparator<HighlightedCourseOnCategory> {

    @Override
    public int compare(HighlightedCourseOnCategory one, HighlightedCourseOnCategory two) {
        return one.getEndTime().compareTo(two.getEndTime());
    }
}
