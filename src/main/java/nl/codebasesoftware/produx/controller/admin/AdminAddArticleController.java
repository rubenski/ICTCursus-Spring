package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleSuggestionEntityDTO;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.AddArticleFormData;
import nl.codebasesoftware.produx.service.ArticleService;
import nl.codebasesoftware.produx.service.ArticleSuggestionService;
import nl.codebasesoftware.produx.service.CategoryService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.validator.AddArticleFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 26-5-13
 * Time: 0:37
 */
@Controller
public class AdminAddArticleController {


    @Resource(name = "addArticleValidator")
    private AddArticleFormValidator formValidator;

    private ArticleSuggestionService articleSuggestionService;
    private CategoryService categoryService;
    private ArticleService articleService;
    private MessageSource messageSource;

    @Autowired
    public AdminAddArticleController(ArticleSuggestionService articleSuggestionService,
                                     CategoryService categoryService,
                                     ArticleService articleService,
                                     MessageSource messageSource) {
        this.articleSuggestionService = articleSuggestionService;
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.messageSource = messageSource;
    }


    @RequestMapping(value = "/admin/articles/suggested/add/{suggestionId}", method = RequestMethod.GET)
    public String addArticleForm(@PathVariable("suggestionId") Long suggestiondId, Model model, Locale locale) {
        ArticleSuggestionEntityDTO suggestion = articleSuggestionService.findById(suggestiondId);

        if (suggestion == null || !suggestion.getSuggester().equals(CurrentUser.get()) || !suggestion.isApproved() || suggestion.isUsed()) {
            throw new ResourceNotFoundException();
        }

        AddArticleFormData formData = new AddArticleFormData();
        formData.setSuggestionId(suggestion.getId());
        formData.setTitle(suggestion.getSuggestedTitle());
        setAddArticleFormData(model, locale, formData);

        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/suggested/add/{suggestionId}", method = RequestMethod.POST)
    public String addArticle(@ModelAttribute("articleFormData") AddArticleFormData formData, BindingResult result, Model model, Locale locale) {

        formValidator.validate(formData, result);

        if (!result.hasErrors()) {
            Article article = articleService.addArticle(formData, CurrentUser.get().getId());
            return String.format("redirect:/admin/articles/edit/%s", article.getId());
        }

        setAddArticleFormData(model, locale, formData);
        model.addAttribute("valid", "false");

        return "adminMain";
    }

    private void setAddArticleFormData(Model model, Locale locale, AddArticleFormData formData) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("headerText", messageSource.getMessage("admin.articles.header.newarticle", new Object[]{}, locale));
        model.addAttribute("mainContent", "forms/addarticle");
        model.addAttribute("articleFormData", formData);
    }
}
