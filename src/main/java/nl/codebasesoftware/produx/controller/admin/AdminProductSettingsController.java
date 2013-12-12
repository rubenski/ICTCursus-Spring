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

    @RequestMapping(value = "/admin/productsettings", method = RequestMethod.GET)
    public String showAllProducts(Model model, Locale locale){
        model.addAttribute("headerText", messageSource.getMessage("product.settings.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminproductsettings");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/productsettings", method = RequestMethod.POST)
    public String saveAllProducts(Model model, Locale locale, @ModelAttribute("productSettings") CompanyProductSettingsFormData companyFormData){
        companyService.update(companyFormData);
        model.addAttribute("headerText", messageSource.getMessage("product.settings.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminproductsettings");
        model.addAttribute("valid", "true");
        setData(model);
        return "adminMain";
    }

    /*
    @RequestMapping(value = "/admin/faq/products/listingtypes", method = RequestMethod.GET)
    public String showListingTypes(Model model, Locale locale){
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminlistingtypes");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.POST)
    public String saveListingTypes(Model model, Locale locale, @ModelAttribute("productSettings") CompanyProductSettingsFormData companyFormData){
        companyService.update(companyFormData);
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminlistingtypes");
        model.addAttribute("valid", "true");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/faq/products/companyinfo", method = RequestMethod.GET)
    public String showAdminCompanyInfoProduct(Model model, Locale locale){
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/admincompanyinfoproduct");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products/companyinfo", method = RequestMethod.POST)
    public String saveAdminCompanyInfoProduct(Model model, Locale locale, @ModelAttribute("productSettings") CompanyProductSettingsFormData productSettings){
        companyService.update(productSettings);
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/admincompanyinfoproduct");
        model.addAttribute("valid", "true");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/faq/products/directlinks", method = RequestMethod.GET)
    public String showAdminDirectLinksProduct(Model model, Locale locale){
        model.addAttribute("headerText", messageSource.getMessage("products.externalcourselinks.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/admindirectlinksproduct");
        setData(model);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products/directlinks", method = RequestMethod.POST)
    public String saveAdminDirectLinksProduct(Model model, Locale locale, @ModelAttribute("productSettings") CompanyProductSettingsFormData productSettings){
        model.addAttribute("headerText", messageSource.getMessage("products.externalcourselinks.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/admindirectlinksproduct");
        setData(model);
        return "adminMain";
    } */

    private void setData(Model model){
        CompanyEntityDTO currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        CompanyProductSettingsFormData formData = new CompanyProductSettingsFormData(currentlyLoggedInCompany);
        model.addAttribute("productSettings",formData);
    }
}
