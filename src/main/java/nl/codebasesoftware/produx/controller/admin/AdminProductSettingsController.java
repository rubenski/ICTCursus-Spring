package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.formdata.CompanyFormData;
import nl.codebasesoftware.produx.formdata.CompanyProductSettingsFormData;
import nl.codebasesoftware.produx.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * User: rvanloen
 * Date: 30-11-13
 * Time: 15:03
 */
@Controller
public class AdminProductSettingsController {

    private CompanyService companyService;
    private MessageSource messageSource;

    @Autowired
    public AdminProductSettingsController(CompanyService companyService, MessageSource messageSource) {
        this.companyService = companyService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.GET)
    public String showListingTypes(Model model, Locale locale){
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminlistingtypes");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.POST)
    public String saveListingTypes(Model model, Locale locale, @ModelAttribute("productSettings") CompanyProductSettingsFormData companyFormData){
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminlistingtypes");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products/companyinfo", method = RequestMethod.GET)
    public String showAdminCompanyInfoProduct(Model model, Locale locale){
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/admincompanyinfoproduct");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products/companyinfo", method = RequestMethod.POST)
    public String saveAdminCompanyInfoProduct(Model model, Locale locale, @ModelAttribute("company") CompanyFormData companyFormData){
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/admincompanyinfoproduct");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products/directlinks", method = RequestMethod.GET)
    public String showAdminDirectLinksProduct(Model model, Locale locale){
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/admindirectlinksproduct");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products/directlinks", method = RequestMethod.POST)
    public String saveAdminDirectLinksProduct(Model model, Locale locale, @ModelAttribute("company") CompanyFormData companyFormData){
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/admindirectlinksproduct");
        setData(model);
        return "adminMain";
    }

    private void setData(Model model){
        CompanyEntityDTO currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        CompanyProductSettingsFormData formData = new CompanyProductSettingsFormData(currentlyLoggedInCompany.getProductSettings());
        model.addAttribute("productSettings",formData);
    }
}
