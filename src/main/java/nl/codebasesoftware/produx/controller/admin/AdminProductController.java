package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.formdata.CompanyProductSettingsFormData;
import nl.codebasesoftware.produx.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * User: rvanloen
 * Date: 30-11-13
 * Time: 15:03
 */
@Controller
public class AdminProductController {

    private CompanyService companyService;
    private MessageSource messageSource;

    @Autowired
    public AdminProductController(CompanyService companyService, MessageSource messageSource) {
        this.companyService = companyService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.GET)
    public String showAdminProducts(Model model, Locale locale){
        CompanyEntityDTO currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminlistingtypes");
        model.addAttribute("productSettings", new CompanyProductSettingsFormData());
        model.addAttribute("company", currentlyLoggedInCompany);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products/companyinfo", method = RequestMethod.GET)
    public String showAdminCompanyInfoProduct(Model model, Locale locale){
        CompanyEntityDTO currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/companyinfoproduct");
        model.addAttribute("productSettings", new CompanyProductSettingsFormData());
        model.addAttribute("company", currentlyLoggedInCompany);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/products/directlinks", method = RequestMethod.GET)
    public String showAdminDirectLinksProduct(Model model, Locale locale){
        CompanyEntityDTO currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        model.addAttribute("headerText", messageSource.getMessage("products.listingtype.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/companyinfoproduct");
        model.addAttribute("productSettings", new CompanyProductSettingsFormData());
        model.addAttribute("company", currentlyLoggedInCompany);
        return "adminMain";
    }
}
