package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.optionlists.NumberOfParticipants;
import nl.codebasesoftware.produx.domain.optionlists.Prefixes;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.business.CourseUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * User: rvanloen
 * Date: 20-9-12
 * Time: 1:25
 */
@Controller
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/c/{categoryUrlNmame}/{title}", method = RequestMethod.GET)
    public String get(@PathVariable("title") String title, Model model) {

        long id = -1;
        try {
            id = CourseUrl.extractId(title);
        } catch (ProduxServiceException e) {
            e.printStackTrace();
        }

        Course course = courseService.findFull(id);
        List<Prefixes> prefixesList = Arrays.asList(Prefixes.values());

        model.addAttribute("course", course);
        model.addAttribute("prefixes", prefixesList);
        model.addAttribute("courseRequest", new CourseRequest());
        model.addAttribute("mainContent", "content/course");
        model.addAttribute("numberOfParticipants", NumberOfParticipants.NUMBERS);
        return "main";
    }


}
