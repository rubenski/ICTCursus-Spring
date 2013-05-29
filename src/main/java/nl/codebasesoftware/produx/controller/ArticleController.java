package nl.codebasesoftware.produx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: rvanloen
 * Date: 28-5-13
 * Time: 16:54
 */
@Controller
public class ArticleController {

    public ArticleController() {
    }

    @RequestMapping(value = "/{category}/a{id:[0-9]}-{title}")
    public String getArticle(@PathVariable("id") Long id, Model model){
        return "article";
    }
}
