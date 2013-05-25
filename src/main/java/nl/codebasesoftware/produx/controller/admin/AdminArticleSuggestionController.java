package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.ArticleSuggestionFormData;
import nl.codebasesoftware.produx.service.ArticleSuggestionService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.validator.ArticleSuggestionFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
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
 * Date: 12-4-13
 * Time: 20:10
 */
@Controller
public class AdminArticleSuggestionController {

    private MessageSource messageSource;
    private ArticleSuggestionFormValidator formValidator;
    private ArticleSuggestionService articleSuggestionService;
    private ConversionService conversionService;
    private UserProfileService userProfileService;

    @Autowired
    public AdminArticleSuggestionController(MessageSource messageSource, ArticleSuggestionFormValidator formValidator,
                                            ArticleSuggestionService articleSuggestionService, ConversionService conversionService,
                                            UserProfileService userProfileService) {
        this.messageSource = messageSource;
        this.formValidator = formValidator;
        this.articleSuggestionService = articleSuggestionService;
        this.conversionService = conversionService;
        this.userProfileService = userProfileService;
    }

    @RequestMapping(value = "/admin/articles/suggest", method = RequestMethod.GET)
    public String suggestionForm(Model model, Locale locale) {

        setHeader(model, locale);
        UserProfile user = userProfileService.getCurrentlyLoggedInUser();

        ArticleSuggestionFormData formData = new ArticleSuggestionFormData();
        formData.setEmail(user.getEmail());

        model.addAttribute("articleSuggestionFormData", formData);
        model.addAttribute("mainContent", "forms/articlesuggestion");

        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/suggest", method = RequestMethod.POST)
    public String send(@ModelAttribute("articleSuggestionFormData") ArticleSuggestionFormData formData,
                       Model model, Locale locale, BindingResult result) {

        formValidator.validate(formData, result);

        if (!result.hasErrors()) {
            ArticleSuggestion suggestion = articleSuggestionService.insert(formData);
            return String.format("redirect:/admin/articles/suggest/success/%s", suggestion.getId());
        }

        setHeader(model, locale);
        model.addAttribute("active", "false");
        model.addAttribute("articleSuggestionFormData", formData);
        model.addAttribute("mainContent", "forms/articlesuggestion");

        return "adminMain";
    }

    @RequestMapping(value = "/admin/sys/articlesuggestions", method = RequestMethod.GET)
    public String showSuggestions(Model model, Locale locale) {

        List<ArticleSuggestion> suggestions = articleSuggestionService.findAllDateSortedDesc();
        model.addAttribute("suggestions", suggestions);
        model.addAttribute("numberOfSuggestions", suggestions.size());
        model.addAttribute("mainContent", "content/sysAdminArticleSuggestions");
        model.addAttribute("headerText", messageSource.getMessage("sysadmin.article.suggest.header", new Object[]{}, locale));

        return "sysAdminMain";
    }


    @RequestMapping(value = "/admin/sys/articlesuggestions/{id}", method = RequestMethod.GET)
    public String showSuggestion(@PathVariable("id") Long id, Model model, Locale locale) {

        ArticleSuggestion suggestion = articleSuggestionService.findFull(id);
        ArticleSuggestionFormData formData = conversionService.convert(suggestion, ArticleSuggestionFormData.class);
        setSuggestionFormData(formData, model, locale);

        return "sysAdminMain";
    }

    @RequestMapping(value = "/admin/sys/articlesuggestions/{id}", method = RequestMethod.POST)
    public String saveSuggestion(@PathVariable("id") Long id,
                                 @ModelAttribute("articleSuggestionFormData") ArticleSuggestionFormData formData, Model model,
                                 Locale locale) {

        articleSuggestionService.setApproval(id, formData.isApproved());

        // Reload the suggestion, because the form fields are disabled and the values are not in the Spring bound formdata objec
        ArticleSuggestion suggestion = articleSuggestionService.findFull(id);
        formData = conversionService.convert(suggestion, ArticleSuggestionFormData.class);

        setSuggestionFormData(formData, model, locale);

        model.addAttribute("valid", "true");

        return "sysAdminMain";
    }

    private void setSuggestionFormData(ArticleSuggestionFormData formData, Model model, Locale locale) {
        model.addAttribute("articleSuggestionFormData", formData);
        model.addAttribute("mainContent", "forms/sysAdminArticleSuggestion");
        model.addAttribute("headerText", messageSource.getMessage("sysadmin.article.suggest.header", new Object[]{}, locale));
    }

    @RequestMapping(value = "/admin/articles/suggest/success/{id}", method = RequestMethod.GET)
    public String success(@PathVariable("id") Long id, Model model, Locale locale) {

        ArticleSuggestion suggestion = articleSuggestionService.findById(id);
        setHeader(model, locale);
        if (suggestion == null || !suggestion.getSuggester().equals(CurrentUser.get())) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("mainContent", "content/suggestionsuccess");

        return "adminMain";
    }

    private void setHeader(Model model, Locale locale) {
        model.addAttribute("headerText", messageSource.getMessage("article.suggest.header", new Object[]{}, locale));
    }

}
