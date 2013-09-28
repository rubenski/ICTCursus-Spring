package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.dto.entity.*;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import org.springframework.core.convert.converter.Converter;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 2-9-13
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public class CourseEntityDTOToBindableCourse implements Converter<CourseEntityDTO, BindableCourse> {

    @Override
    public BindableCourse convert(CourseEntityDTO course) {

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

    private List<Long> getOptions(CourseEntityDTO course) {
        List<Long> bindableOptions = new ArrayList<>();

        for (CourseOptionEntityDTO courseOption : course.getOptions()) {
            bindableOptions.add(courseOption.getId());
        }

        return bindableOptions;
    }

    private List<String> getCourseDates(CourseEntityDTO course) {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (CourseDateEntityDTO courseDate : course.getDates()) {
            String s = sdf.format(courseDate.getStartDate().getTime());
            dates.add(s);
        }
        return dates;
    }

    private List<Long> getTimeIds(CourseEntityDTO course) {
        List<Long> idList = new ArrayList<>();
        Iterator<TimeEntityDTO> timeIterator = course.getTimes().iterator();
        for (int i = 0; timeIterator.hasNext(); i++) {
            TimeEntityDTO time = timeIterator.next();
            idList.add(time.getId());
        }
        return idList;
    }

    private List<Long> getRegionIds(CourseEntityDTO course) {
        List<Long> idList = new ArrayList<>();
        Iterator<RegionEntityDTO> regionsIterator = course.getRegions().iterator();
        for (int i = 0; regionsIterator.hasNext(); i++) {
            RegionEntityDTO region = regionsIterator.next();
            idList.add(region.getId());
        }
        return idList;
    }

    private String getFormattedPrice(Long priceCents) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        Double priceDouble = priceCents / 100d;
        String s = numberFormat.format(priceDouble);
        return s;
    }
}
