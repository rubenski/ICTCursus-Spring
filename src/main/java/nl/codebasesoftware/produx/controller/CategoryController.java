package nl.codebasesoftware.produx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 12:03
 */
@Controller
@RequestMapping("/c/**")
public class CategoryController {

    @RequestMapping(method = RequestMethod.GET)
    public String setup(Model model){


        return "main";
    }
}


