package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.service.HighlightedCoursePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * User: rvanloen
 * Date: 29-11-13
 * Time: 23:05
 */
@Controller
public class HighlightedCoursesController {

    private HighlightedCoursePeriodService highlightedCoursePeriodService;

    @Autowired
    public HighlightedCoursesController(HighlightedCoursePeriodService highlightedCoursePeriodService) {
        this.highlightedCoursePeriodService = highlightedCoursePeriodService;
    }
}
