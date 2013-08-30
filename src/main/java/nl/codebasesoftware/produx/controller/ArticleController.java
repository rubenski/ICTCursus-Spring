package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.ArticlePageEntityDTO;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.service.ArticleService;
import nl.codebasesoftware.produx.service.PageBlockService;
import nl.codebasesoftware.produx.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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


    @RequestMapping(value = "/{category}/a{aid:[0-9]+}/{title:[^p0-9:-].+}")
    public String getArticle(@PathVariable("aid") Long articleId,
                             HttpServletRequest request,
                             Model model) {

        ArticleEntityDTO article = articleService.findFull(articleId);
        String url = article.getUrl();
        String requestUri = request.getRequestURI();

        if(!requestUri.equals(url)){
            throw new ResourceNotFoundException();
        }

        setData(model, article, null);
        model.addAttribute("title", article.getTitle() + " - " + properties.getProperty("domain"));

        return "main";
    }

    @RequestMapping(value = "/{category}/a{aid:[0-9]+}/p{pnr:[0-9]+}-{title:.+}")
    public String getArticlePage(@PathVariable("aid") Long articleId,
                                 @PathVariable("pnr") int pageNumber,
                                 HttpServletRequest request,
                                 Model model) {

        ArticleEntityDTO article = articleService.findFull(articleId);
        ArticlePageEntityDTO articlePage = article.getArticlePage(pageNumber);

        String url = articlePage.getUrl();
        String requestURI = request.getRequestURI();

        if (!requestURI.equals(url)) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("title", articlePage.getTitle() + " - " + properties.getProperty("domain"));
        setData(model, article, articlePage);

        return "main";
    }



    private void setData(Model model, ArticleEntityDTO article, ArticlePageEntityDTO page) {

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
        model.addAttribute("includeHighlighter", true);
    }


}
