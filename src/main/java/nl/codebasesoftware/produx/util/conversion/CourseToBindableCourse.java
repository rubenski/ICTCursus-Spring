package nl.codebasesoftware.produx.util.conversion;

import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.formdata.BindableCourseOption;
import org.springframework.core.convert.converter.Converter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * User: rvanloen
 * Date: 17-2-13
 * Time: 13:46
 */
public class CourseToBindableCourse implements Converter<Course, BindableCourse> {

    @Override
    public BindableCourse convert(Course course) {
        BindableCourse bindableCourse = new BindableCourse();
        bindableCourse.setCategory(course.getCategory().getId());
        bindableCourse.setDuration(course.getDuration());
        bindableCourse.setId(course.getId());
        bindableCourse.setLongDescription(course.getLongDescription());
        bindableCourse.setName(course.getName());
        bindableCourse.setFormattedPrice(getFormattedPrice(course.getPrice()));
        bindableCourse.setRegions(getRegionIds(course));
        bindableCourse.setShortDescription(course.getShortDescription());
        bindableCourse.setTags(course.getTagNames());
        bindableCourse.setCertificate(course.isCertificate());
        bindableCourse.setCertificateText(course.getCertificateText());
        bindableCourse.setTimes(getTimeIds(course));
        bindableCourse.setDates(getCourseDates(course));
        bindableCourse.setOptions(getOptions(course));
        return bindableCourse;
    }

    private List<BindableCourseOption> getOptions(Course course) {
        List<BindableCourseOption> bindableOptions = new ArrayList<BindableCourseOption>();

        for (CourseOption courseOption : course.getOptions()) {
            bindableOptions.add(courseOption.toBindable());
        }

        return bindableOptions;
    }

    private List<Calendar> getCourseDates(Course course){
        List<Calendar> dates = new ArrayList<Calendar>();
        for (CourseDate courseDate : course.getDates()) {
            dates.add(courseDate.getStartDate());
        }
        return dates;
    }

    private List<Long> getTimeIds(Course course){
        List<Long> idList = new ArrayList<Long>();
        Iterator<Time> timeIterator = course.getTimes().iterator();
        for (int i = 0; timeIterator.hasNext(); i++) {
            Time time = timeIterator.next();
            idList.add(time.getId());
        }
        return idList;
    }

    private List<Long> getRegionIds(Course course) {
        List<Long> idList = new ArrayList<Long>();
        Iterator<Region> regionsIterator = course.getRegions().iterator();
        for (int i = 0; regionsIterator.hasNext(); i++) {
            Region region = regionsIterator.next();
            idList.add(region.getId());
        }
        return idList;
    }

    private String getFormattedPrice(Long priceCents) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        Double priceDouble = priceCents / 100d;
        String s = numberFormat.format(priceDouble);
        return s;
    }
}
