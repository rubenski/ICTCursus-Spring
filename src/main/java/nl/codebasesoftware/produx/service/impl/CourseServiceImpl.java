package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CategoryHighlightPeriodDao;
import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.dao.jpa.HighlightedCoursePeriodDaoJpa;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Time;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.SolrService;
import nl.codebasesoftware.produx.service.SystemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:39
 */
@Service
public class CourseServiceImpl implements CourseService {


    private CourseDao courseDao;
    private SystemPropertyService systemPropertyService;
    private ConversionService conversionService;
    private CompanyDao companyDao;
    private SolrService solrService;
    private CategoryHighlightPeriodDao categoryHighlightPeriodDao;


    @Autowired
    public CourseServiceImpl(CourseDao courseDao,
                             SystemPropertyService systemPropertyService,
                             ConversionService conversionService, CompanyDao companyDao, SolrService solrService,
                             CategoryHighlightPeriodDao categoryHighlightPeriodDao) {
        this.courseDao = courseDao;
        this.systemPropertyService = systemPropertyService;
        this.conversionService = conversionService;
        this.companyDao = companyDao;
        this.solrService = solrService;
        this.categoryHighlightPeriodDao = categoryHighlightPeriodDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListingCourseDTO> findHighlightedCourses(long categoryId) {
        return asListingCourses(courseDao.findHighlightedCourses(categoryId, Calendar.getInstance()));
    }
     /*
    @Override
    @Transactional(readOnly = true)
    public List<Course> findNonHighlightedCoursesForCompany(long categoryId) {
        return courseDao.findNonHighlightedCoursesForCompany(categoryId, Calendar.getInstance());
    }    */

    @Override
    @Transactional(readOnly = true)
    public List<CourseEntityDTO> findByCompany(CompanyEntityDTO company) {
        return findByCompanyId(company.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseEntityDTO> findByCompanyId(long companyId) {
        return asCourseDTOs(courseDao.findCourses(companyId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListingCourseDTO> findForListing(List<Long> ids) {
        List<Course> list = courseDao.findForListing(ids);
        List<ListingCourseDTO> listingCourses = new ArrayList<>();
        for (Course course : list) {
            listingCourses.add(course.toListingCourseDTO());
        }
        return listingCourses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Time> findCourseTimes() {
        return courseDao.findCourseTimes();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseEntityDTO findFull(Long id) {
        return courseDao.findFull(id).toDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Course save(BindableCourse bindableCourse) {
        Course course = conversionService.convert(bindableCourse, Course.class);

        if (bindableCourse.isPublished() && !course.wasPreviouslyPublished()) {
            course.setFirstPublished(Calendar.getInstance());
        }
        courseDao.persist(course);
        solrService.addOrUpdate(course.toDTO());


        return course;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseEntityDTO> findIndexableCourses() {
        Calendar calendar = systemPropertyService.lastSolrUpdateDate();
        List<Long> indexableCourseIds = courseDao.findIndexableCourseIds(calendar);
        List<CourseEntityDTO> indexableCourses = new ArrayList<>();
        for (Long indexableCourseId : indexableCourseIds) {
            Course course = courseDao.findFull(indexableCourseId);
            indexableCourses.add(course.toDTO());
        }

        return indexableCourses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findNonHighlightedCoursesForCompanyAndCategory(CompanyEntityDTO currentlyLoggedInCompany, Category category) {
        //TODO: decide what exactly to return here.
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findCoursesForCompanyAndCategory(long companyId, long categoryId) {
        return courseDao.findCoursesForCompanyAndCategory(companyId, categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(Long courseId) {
        return courseDao.find(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseEntityDTO> findAllWithCompany() {
        return asCourseDTOs(courseDao.findAllWithCompany());
    }

    private List<ListingCourseDTO> asListingCourses(List<Course> courses) {
        List<ListingCourseDTO> dtos = new ArrayList<>();
        for (Course course : courses) {
            dtos.add(course.toListingCourseDTO());
        }
        return dtos;
    }

    private List<CourseEntityDTO> asCourseDTOs(List<Course> courses) {
        List<CourseEntityDTO> dtos = new ArrayList<>();
        for (Course course : courses) {
            dtos.add(course.toDTO());
        }
        return dtos;
    }

}
