package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Time;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.formdata.BindableCourse;

import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:13
 */
public interface CourseService {
    List<CourseEntityDTO> findByCompanyId(long companyId);

    CourseEntityDTO findFull(Long id);

    Course save(BindableCourse bindableCourse);

    List<CourseEntityDTO> findIndexableCourses();

    List<ListingCourseDTO> findForListing(List<Long> ids);

    List<Time> findCourseTimes();

    List<Course> findAll();

    List<ListingCourseDTO> findHighlightedCourses(long categoryId);

    //List<Course> findNonHighlightedCoursesForCompany(long categoryId);

    List<Course> findNonHighlightedCoursesForCompanyAndCategory(CompanyEntityDTO currentlyLoggedInCompany, Category category);

    List<Course> findCoursesForCompanyAndCategory(long categoryId, long companyId);

    Course findById(Long courseId);

    List<CourseEntityDTO> findAllWithCompany();

    List<CourseEntityDTO> findByCompany(CompanyEntityDTO company);
}
