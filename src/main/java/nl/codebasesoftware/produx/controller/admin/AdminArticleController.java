package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.AddArticleFormData;
import nl.codebasesoftware.produx.formdata.EditArticleFormData;
import nl.codebasesoftware.produx.service.ArticleService;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.UserProfileService;
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
    private AddArticleFormValidator addArticleValidator;
    private UserProfileService userProfileService;

    @Autowired
    public AdminArticleController(ArticleService articleService,
                                  CompanyService companyService,
                                  MessageSource messageSource,
                                  AddArticleFormValidator addArticleValidator,
                                  UserProfileService userProfileService) {
        this.articleService = articleService;
        this.companyService = companyService;
        this.messageSource = messageSource;
        this.addArticleValidator = addArticleValidator;
        this.userProfileService = userProfileService;
    }


    @RequestMapping(value = "/admin/articles", method = RequestMethod.GET)
    public String companyArticles(Model model, Locale locale) {
        Company company = companyService.getCurrentlyLoggedInCompany();
        List<Article> articles = articleService.findByCompany(company.getId());
        model.addAttribute("articles", articles);
        model.addAttribute("numberOfArticles", articles.size());
        model.addAttribute("mainContent", "content/adminArticles");
        model.addAttribute("headerText", messageSource.getMessage("admin.articles.header", new Object[]{}, locale));
        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/add", method = RequestMethod.GET)
    public String addArticleForm(Model model, Locale locale) {
        model.addAttribute("articleFormData", new AddArticleFormData());
        model.addAttribute("mainContent", "forms/addarticle");
        model.addAttribute("headerText", messageSource.getMessage("admin.articles.header.newarticle", new Object[]{}, locale));
        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/edit/{id}", method = RequestMethod.GET)
    public String editArticle(@PathVariable("id") Long id, Model model, Locale locale){
        Article article = articleService.findById(id);

        if(article == null){
            throw new ResourceNotFoundException();
        }

        Company currentCompany = companyService.getCurrentlyLoggedInCompany();
        Company authorCompany = companyService.findByArticle(article);

        // Security
        if(!authorCompany.equals(currentCompany)){
            throw new ResourceNotFoundException();
        }

        List<ArticlePage> pages = articleService.findPages(article);

        EditArticleFormData editArticleFormData = new EditArticleFormData();
        editArticleFormData.setPublished(article.isPublished());
        editArticleFormData.setId(article.getId());
        editArticleFormData.setTeaser(article.getTeaser());
        editArticleFormData.setTitle(article.getTitle());

        model.addAttribute("numberOfPages", pages.size());
        model.addAttribute("pages", pages);
        model.addAttribute("headerText", messageSource.getMessage("admin.articles.header.editarticle", new Object[]{}, locale));
        model.addAttribute("editArticleFormData", editArticleFormData);
        model.addAttribute("mainContent", "forms/editarticle");
        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/add", method = RequestMethod.POST)
    public String addArticle(@ModelAttribute("articleFormData") AddArticleFormData formData, BindingResult result, Model model, Locale locale) {

        addArticleValidator.validate(formData, result);

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

