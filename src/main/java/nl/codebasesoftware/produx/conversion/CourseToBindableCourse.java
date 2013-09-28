package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import org.springframework.core.convert.converter.Converter;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        bindableCourse.setShortDescription(course.getListDescription());
        bindableCourse.setCertificateName(course.getCertificateName());
        bindableCourse.setTags(course.getTagNames());
        bindableCourse.setCertificate(course.isCertificate());
        bindableCourse.setTimes(getTimeIds(course));
        bindableCourse.setDates(getCourseDates(course));
        bindableCourse.setOptions(getOptions(course));
        bindableCourse.setPublished(course.isPublished());
        bindableCourse.setPublishable(course.isPublishable());
        return bindableCourse;
    }

    private List<Long> getOptions(Course course) {
        List<Long> bindableOptions = new ArrayList<Long>();

        for (CourseOption courseOption : course.getOptions()) {
            bindableOptions.add(courseOption.getId());
        }

        return bindableOptions;
    }

    private List<String> getCourseDates(Course course) {
        List<String> dates = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (CourseDate courseDate : course.getDates()) {
            String s = sdf.format(courseDate.getStartDate().getTime());
            dates.add(s);
        }
        return dates;
    }

    private List<Long> getTimeIds(Course course) {
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
