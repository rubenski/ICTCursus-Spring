package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.dao.*;
import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 1-3-13
 * Time: 23:05
 */
@Component
public class BindableCourseToCourse implements Converter<BindableCourse, Course> {

    private CategoryDao categoryDao;
    private RegionDao regionDao;
    private CourseDao courseDao;
    private CourseOptionDao optionDao;
    private TagDao tagDao;

    @Autowired
    public BindableCourseToCourse(CategoryDao categoryDao, RegionDao regionDao, CourseDao courseDao, CourseOptionDao optionDao, TagDao tagDao) {

        this.categoryDao = categoryDao;
        this.regionDao = regionDao;
        this.courseDao = courseDao;
        this.optionDao = optionDao;
        this.tagDao = tagDao;
    }

    @Override
    public Course convert(BindableCourse bindableCourse) {

        Course course = null;

        if (bindableCourse.getId() != null) {
            course = courseDao.findFull(bindableCourse.getId());
        } else {
            course = new Course();
        }

        // Get the category
        Category category = categoryDao.find(bindableCourse.getCategory());

        // Get the company
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = (UserProfile) authentication.getPrincipal();
        Company company = userProfile.getCompany();

        // Get the regions
        Set<Region> newRegions = new HashSet<Region>();
        if (bindableCourse.getRegions() != null) {
            for (Long id : bindableCourse.getRegions()) {
                Region region = regionDao.find(id);
                newRegions.add(region);
            }
        }
        course.setRegions(newRegions);

        // Get the course times
        if (bindableCourse.getTimes() != null) {
            Set<Time> newTimes = new HashSet<Time>();
            for (Long timeId : bindableCourse.getTimes()) {
                newTimes.add(courseDao.getCourseTime(timeId));
            }
            course.setTimes(newTimes);
        }

        // Get course options
        if (bindableCourse.getOptions() != null) {
            Set<CourseOption> options = new HashSet<CourseOption>();
            for (Long optionId : bindableCourse.getOptions()) {
                CourseOption courseOption = optionDao.find(optionId);
                options.add(courseOption);
            }
            course.setOptions(options);
        }

        // Get course dates
        if (bindableCourse.getDates() != null) {
            Set<CourseDate> dates = new HashSet<CourseDate>();
            for (String dateString : bindableCourse.getDates()) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date = null;
                try {
                    date = sdf.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cal.setTime(date);

                CourseDate courseDate = new CourseDate();
                courseDate.setStartDate(cal);
                dates.add(courseDate);
            }
            course.replaceDates(dates);
        }

        // Get tags
        if (bindableCourse.getTags() != null) {
            Set<Tag> newTags = new HashSet<Tag>();
            for (String tagName : bindableCourse.getTags()) {
                Tag tag = tagDao.findByName(tagName);
                if (tag == null) {
                    tag = new Tag();
                    tag.setName(tagName);
                    tagDao.persist(tag);
                }
                newTags.add(tag);
            }
            course.setTags(newTags);
        }


        course.setId(bindableCourse.getId());
        course.setCategory(category);
        course.setShortDescription(bindableCourse.getShortDescription());
        course.setCompany(company);
        course.setDuration(bindableCourse.getDuration());
        course.setLastUpdated(Calendar.getInstance());
        course.setName(bindableCourse.getName());
        course.setPrice(bindableCourse.getPriceAsLong());
        course.setLongDescription(bindableCourse.getLongDescription());
        course.setCertificate(bindableCourse.isCertificate());
        course.setCertificateName(bindableCourse.getCertificateName());

        return course;
    }
}
