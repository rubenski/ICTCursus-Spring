package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 10-11-12
 * Time: 21:16
 */
@Controller
@RequestMapping(value = "/manage/course/{id}")
public class ManagementCourseController {

    private CourseService courseService;

    @Autowired
    public ManagementCourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {
        model.addAttribute("mainContent", "forms/editCourse");
        Course course = courseService.findById(id);
        if(course == null){
            throw new ResourceNotFoundException();
        }
        model.addAttribute("course", course);

        return "main";
    }
}
