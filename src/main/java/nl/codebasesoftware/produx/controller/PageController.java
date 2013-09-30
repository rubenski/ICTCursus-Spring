package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.service.PageBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 25-9-13
 * Time: 13:43
 */
@Controller
public class PageController {


    private PageBlockService pageBlockService;

    @Autowired
    public PageController(PageBlockService pageBlockService) {
        this.pageBlockService = pageBlockService;
    }


    @RequestMapping(value = "/page/cursusaanbieders", method = RequestMethod.GET)
    public String getVoorCursusaanbieders(Model model) {

        pageBlockService.setEmptyRightColumn(model);
        pageBlockService.setAboutIctCursusMenuInLeftColumn(model);
        model.addAttribute("title", "ICT Cursus voor cursusaanbieders");
        model.addAttribute("mainContent", "content/forcompanies");
        return "main";
    }
}
