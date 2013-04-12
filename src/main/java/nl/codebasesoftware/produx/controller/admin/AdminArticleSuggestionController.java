package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.ArticleSuggestionFormData;
import nl.codebasesoftware.produx.service.ArticleSuggestionService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.validator.ArticleSuggestionFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @Autowired
    public AdminArticleSuggestionController(MessageSource messageSource, ArticleSuggestionFormValidator formValidator,
                                            ArticleSuggestionService articleSuggestionService) {
        this.messageSource = messageSource;
        this.formValidator = formValidator;
        this.articleSuggestionService = articleSuggestionService;
    }

    @RequestMapping(value = "/admin/articles/suggest", method = RequestMethod.GET)
    public String suggestionForm(Model model, Locale locale){

        setHeader(model, locale);
        model.addAttribute("adrticleSuggestionFormData", new ArticleSuggestionFormData());
        model.addAttribute("mainContent", "forms/articlesuggestion");

        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/suggest", method = RequestMethod.POST)
    public String send(@ModelAttribute("adrticleSuggestionFormData") ArticleSuggestionFormData formData,
                       Model model, Locale locale, BindingResult result){


        formValidator.validate(formData, result);



        if(!result.hasErrors()){
            ArticleSuggestion suggestion = articleSuggestionService.save(formData);
            return String.format("redirect:/admin/articles/suggest/success/%s", suggestion.getId());
        }

        setHeader(model, locale);
        model.addAttribute("active", "false");
        model.addAttribute("adrticleSuggestionFormData", formData);
        model.addAttribute("mainContent", "forms/articlesuggestion");

        return "adminMain";
    }

    @RequestMapping(value = "/admin/articles/suggest/success/{id}", method = RequestMethod.GET)
    public String success(@PathVariable("id") Long id, Model model, Locale locale){

        ArticleSuggestion suggestion = articleSuggestionService.findById(id);

        if(suggestion == null || !suggestion.getSuggester().equals(CurrentUser.get())){
            throw new ResourceNotFoundException();
        }

        model.addAttribute("mainContent", "content/suggestionsuccess");

        return "adminMain";
    }

    private void setHeader(Model model, Locale locale){
        model.addAttribute("headerText", messageSource.getMessage("article.suggest.header", new Object[]{}, locale));
    }

}
