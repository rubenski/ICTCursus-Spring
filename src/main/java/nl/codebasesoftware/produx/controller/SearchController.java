package nl.codebasesoftware.produx.controller;


import nl.codebasesoftware.produx.search.SolrQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 12:46
 */
@Controller
public class SearchController {

    @RequestMapping(value = "/search/{query}", method = RequestMethod.GET)
    public String search(@PathVariable("query") String query, Model model) {
        String q = query;

        SolrQueryBuilder builder = new SolrQueryBuilder();

        model.addAttribute("mainContent", "content/searchResults");
        return "main";
    }

}
