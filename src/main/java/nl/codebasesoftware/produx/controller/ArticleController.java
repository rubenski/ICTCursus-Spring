package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.service.ArticleService;
import nl.codebasesoftware.produx.service.PageBlockService;
import nl.codebasesoftware.produx.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: rvanloen
 * Date: 28-5-13
 * Time: 16:54
 */
@Controller
public class ArticleController {

    private ArticleService articleService;
    private PageBlockService pageBlockService;
    private Properties properties;

    @Autowired
    public ArticleController(ArticleService articleService, PageBlockService pageBlockService, Properties properties) {
        this.articleService = articleService;
        this.pageBlockService = pageBlockService;
        this.properties = properties;
    }


    @RequestMapping(value = "/{category}/a{aid:[0-9]+}/{title}")
    public String getArticle(@PathVariable("aid") Long articleId, @PathVariable("title") String title, Model model) {

        Article article = articleService.findFull(articleId);

        IdAndTitle idAndTitle = isArticlePage(title);

        if (idAndTitle != null) {
            ArticlePage articlePage = article.getArticlePage(idAndTitle.pageNumber);
            setData(model, article, articlePage);
        } else {
            setData(model, article, null);
            model.addAttribute("title", article.getTitle() + " - " + properties.getProperty("domain"));
        }


        return "main";
    }


    // Method to extract the page number. The mapping "/{category}/a{aid:[0-9]+}/p[0-9]-{title}" is not chosen
    // unfortunately. Probably a Spring bug.
    private IdAndTitle isArticlePage(String title) {
        Pattern p = Pattern.compile("p([0-9]+)[-]((.*))");
        Matcher m = p.matcher(title);
        List<String> parts = new ArrayList<String>();
        IdAndTitle idAndTitle = null;
        if (m.matches()) {
            idAndTitle = new IdAndTitle();
            for (int i = 0; i < m.groupCount(); i++) {
                String group = m.group(i);
                if (i == 1) {
                    idAndTitle.pageNumber = Integer.parseInt(group);
                } else if (i == 2) {
                    idAndTitle.utlTitle = group;
                }
            }
        }

        return idAndTitle;
    }

    private void setData(Model model, Article article, ArticlePage page) {

        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setEmptyRightColumn(model);


        if (page != null) {
            model.addAttribute("currentArticlePage", page);
        } else {
            model.addAttribute("currentArticlePage", article);
        }

        model.addAttribute("article", article);
        model.addAttribute("mainContent", "content/article");
        model.addAttribute("articleNav", article.getPages().size() > 0);
    }

    private static class IdAndTitle {
        public int pageNumber;
        public String utlTitle;
    }
}
