package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.AddArticleFormData;
import nl.codebasesoftware.produx.formdata.EditArticleFormData;
import nl.codebasesoftware.produx.service.ArticleService;
import nl.codebasesoftware.produx.service.ArticleSuggestionService;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.validator.ArticleFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 4-3-13
 * Time: 23:24
 */
@Controller
public class AdminArticleController {


    private ArticleService articleService;
    private CompanyService companyService;
    private MessageSource messageSource;
    private ArticleFormValidator articleValidator;
    private ArticleSuggestionService articleSuggestionService;


    @Autowired
    public AdminArticleController(ArticleService articleService,
                                  CompanyService companyService,
                                  MessageSource messageSource,
                                  ArticleFormValidator articleValidator,
                                  ArticleSuggestionService articleSuggestionService) {
        this.articleService = articleService;
        this.companyService = companyService;
        this.messageSource = messageSource;
        this.articleValidator = articleValidator;
        this.articleSuggestionService = articleSuggestionService;
    }


    @RequestMapping(value = "/admin/articles", method = RequestMethod.GET)
    public String companyArticles(Model model, Locale locale) {

        Company company = companyService.getCurrentlyLoggedInCompany();
        List<Article> articles = articleService.findByCompany(company.getId());
        List<ArticleSuggestion> suggestions = articleSuggestionService.findForUser(CurrentUser.get());

        model.addAttribute("numberOfSuggestions", suggestions.size());
        model.addAttribute("suggestions", suggestions);
        model.addAttribute("articles", articles);
        model.addAttribute("numberOfArticles", articles.size());
        model.addAttribute("mainContent", "content/adminArticles");
        model.addAttribute("headerText", messageSource.getMessage("admin.articles.header", new Object[]{}, locale));
        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/suggested/add/{suggestionId}", method = RequestMethod.GET)
    public String addArticleForm(@PathVariable("suggestionId") Long suggestiondId, Model model, Locale locale) {
        ArticleSuggestion suggestion = articleSuggestionService.findById(suggestiondId);

        if (suggestion == null || !suggestion.getSuggester().equals(CurrentUser.get()) || !suggestion.isApproved() || suggestion.isUsed() ) {
            throw new ResourceNotFoundException();
        }

        AddArticleFormData data = new AddArticleFormData();
        data.setSuggestionId(suggestion.getId());
        model.addAttribute("articleFormData", data);
        model.addAttribute("mainContent", "forms/addarticle");
        model.addAttribute("headerText", messageSource.getMessage("admin.articles.header.newarticle", new Object[]{}, locale));
        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/edit/{id}", method = RequestMethod.POST)
    public String saveArticle(@ModelAttribute("editArticleFormData") EditArticleFormData formData,
                              Model model, BindingResult result, Locale locale) {

        String valid = "false";
        articleValidator.validate(formData, result);

        if(!result.hasErrors()){
            valid = "true";
            articleService.updateArticle(formData);
        }

        setPageData(model, formData.getId(), formData, locale);
        model.addAttribute("valid", valid);

        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/edit/{id}", method = RequestMethod.GET)
    public String editArticle(@PathVariable("id") Long id, Model model, Locale locale) {

        Article article = articleService.findById(id);

        if (article == null) {
            throw new ResourceNotFoundException();
        }

        Company currentCompany = companyService.getCurrentlyLoggedInCompany();
        Company authorCompany = companyService.findByArticle(article);

        // Security
        if (!authorCompany.equals(currentCompany)) {
            throw new ResourceNotFoundException();
        }

        EditArticleFormData editArticleFormData = new EditArticleFormData();
        editArticleFormData.setPublished(article.isPublished());
        editArticleFormData.setId(article.getId());
        editArticleFormData.setTeaser(article.getTeaser());
        editArticleFormData.setTitle(article.getTitle());

        setPageData(model, id, editArticleFormData, locale);

        return "adminMain";
    }

    private void setPageData(Model model, long articleId, EditArticleFormData formData, Locale locale){
        Article article = articleService.findById(articleId);
        List<ArticlePage> pages = articleService.findPages(article);
        model.addAttribute("editArticleFormData", formData);
        model.addAttribute("mainContent", "forms/editarticle");
        model.addAttribute("numberOfPages", pages.size());
        model.addAttribute("pages", pages);
        model.addAttribute("articleform", true);
        model.addAttribute("publishingDisabled", pages.size() == 0 ? "true" : "false");
        model.addAttribute("headerText", messageSource.getMessage("admin.articles.header.editarticle", new Object[]{}, locale));
    }

    @RequestMapping(value = "/admin/articles/suggested/add/{suggestionId}", method = RequestMethod.POST)
    public String addArticle(@ModelAttribute("articleFormData") AddArticleFormData formData, BindingResult result, Model model, Locale locale) {

        articleValidator.validate(formData, result);

        if (!result.hasErrors()) {
            Article article = articleService.addArticle(formData, CurrentUser.get().getId());
            return String.format("redirect:/admin/articles/edit/%s", article.getId());
        }

        model.addAttribute("headerText", messageSource.getMessage("admin.articles.header.newarticle", new Object[]{}, locale));
        model.addAttribute("mainContent", "forms/addarticle");
        model.addAttribute("articleFormData", formData);
        model.addAttribute("valid", "false");

        return "adminMain";
    }


}

