package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.domain.support.DateRange;
import nl.codebasesoftware.produx.formdata.HighlightCourseFormData;
import nl.codebasesoftware.produx.service.HighlightedCoursePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: rvanloen
 * Date: 9-5-13
 * Time: 0:12
 */
@Component
public class HighlightedCourseFormValidator implements Validator {


    private HighlightedCoursePeriodService highlightedCoursePeriodService;

    @Autowired
    public HighlightedCourseFormValidator(HighlightedCoursePeriodService highlightedCoursePeriodService) {
        this.highlightedCoursePeriodService = highlightedCoursePeriodService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(HighlightedCourseFormValidator.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        HighlightCourseFormData highLightedCourse = (HighlightCourseFormData) o;


        DateRange dateRangeForHighlightStart = highlightedCoursePeriodService.findDateRangeForHighlightStart(highLightedCourse.getCategory());

        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
        Date startDate = null;
        try {
            startDate = sdf.parse(highLightedCourse.getStartDate());
            Calendar startDateCalendar = Calendar.getInstance();
            startDateCalendar.setTime(startDate);
            if (!dateRangeForHighlightStart.isInRange(startDateCalendar)) {
                String startDateRange = new SimpleDateFormat("dd-MM-yyyy").format(dateRangeForHighlightStart.getStartDate().getTime());
                String endDateRange = new SimpleDateFormat("dd-MM-yyyy").format(dateRangeForHighlightStart.getEndDate().getTime());
                errors.rejectValue("startDate", "highlight.courses.wrong.startdate", new Object[]{startDateRange, endDateRange}, "Start date out of range");
            }
        } catch (ParseException e) {
            errors.rejectValue("startDate", "highlight.courses.invalid.date");

        }


    }
}
