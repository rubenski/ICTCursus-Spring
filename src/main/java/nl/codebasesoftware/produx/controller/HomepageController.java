package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.service.PageBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:08
 */
@Controller
@RequestMapping("/")
public class HomepageController {


    private PageBlockService pageBlockService;

    @Autowired
    public HomepageController(PageBlockService pageBlockService) {
        this.pageBlockService = pageBlockService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {
        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setAuthentication(model);
        pageBlockService.setEmptyRightColumn(model);
        model.addAttribute("title", "ICT Cursus - cursussen en trainingen in de IT");
        model.addAttribute("mainContent", "content/home");
        return "main";
    }
}
