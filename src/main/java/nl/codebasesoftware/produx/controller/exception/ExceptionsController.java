package nl.codebasesoftware.produx.controller.exception;

import nl.codebasesoftware.produx.service.PageBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: rvanloen
 * Date: 26-11-13
 * Time: 0:10
 */
@Controller
public class ExceptionsController {

    private PageBlockService pageBlockService;

    @Autowired
    public ExceptionsController(PageBlockService pageBlockService) {
        this.pageBlockService = pageBlockService;
    }

    @RequestMapping("/ex/notfound")
    public String notFound(Model model){

        pageBlockService.setEmptyRightColumn(model);
        model.addAttribute("mainContent", "error/404");
        model.addAttribute("title", "bla");
        model.addAttribute("message", "this is the message");
        return "main";
    }
}
