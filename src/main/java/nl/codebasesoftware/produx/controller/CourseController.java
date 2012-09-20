package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 20-9-12
 * Time: 1:25
 */
@Controller
@RequestMapping("/c/{categoryUrlNmame}/{courseUrlName}")
public class CourseController {

    @RequestMapping(method = RequestMethod.GET)
    public String get(@PathVariable("courseUrlName") String courseUrlName, Model model) {
        String courseIdString = courseUrlName.replaceAll("(-(.)*)*", "");
        int courseId = 0;
        try {
            courseId = Integer.parseInt(courseIdString);
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("mainContent", "content/course");
        return "main";
    }
}
