package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.formdata.AccountRequestDecision;
import nl.codebasesoftware.produx.service.AccountRequestService;
import nl.codebasesoftware.produx.validator.AccountRequestDecisionValidator;
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
 * Date: 20-4-13
 * Time: 20:26
 */
@Controller
public class SysAdminAccountRequestController {

    private AccountRequestService accountRequestService;
    private MessageSource messageSource;
    private AccountRequestDecisionValidator validator;

    @Autowired
    public SysAdminAccountRequestController(AccountRequestService accountRequestService, MessageSource messageSource,
                                            AccountRequestDecisionValidator validator){
        this.accountRequestService = accountRequestService;
        this.messageSource = messageSource;
        this.validator = validator;
    }

    @RequestMapping(value="/admin/sys/accountrequests", method = RequestMethod.GET)
    public String overview(Model model, Locale locale){
        List<AccountRequest> accountRequests = accountRequestService.findNonEvaluated();
        model.addAttribute("headerText", messageSource.getMessage("sysadmin.sections.accountrequests", new Object[]{}, locale));
        model.addAttribute("numberOfRequests", accountRequests.size());
        model.addAttribute("requests", accountRequests);
        model.addAttribute("mainContent", "content/adminaccountrequests");
        return "sysAdminMain";
    }

    @RequestMapping(value="/admin/sys/accountrequests/{id}", method = RequestMethod.GET)
    public String showRequestForm(@PathVariable("id") Long id, Model model, Locale locale){
        AccountRequest request = accountRequestService.find(id);
        model.addAttribute("headerText", messageSource.getMessage("sysadmin.sections.accountrequests", new Object[]{}, locale));
        model.addAttribute("request", request);
        model.addAttribute("mainContent", "content/adminaccountrequest");
        model.addAttribute("accountRequestDecision", new AccountRequestDecision(id));
        return "sysAdminMain";
    }

    @RequestMapping(value="/admin/sys/accountrequests/{id}", method = RequestMethod.POST)
    public String submitRequestForm(@ModelAttribute("accountRequestDecision") AccountRequestDecision decision, Model model,
                                    BindingResult result, Locale locale){

        validator.validate(decision, result);
        AccountRequest request = accountRequestService.find(decision.getArticleRequestId());
        if(!result.hasErrors()){
            accountRequestService.decide(decision);
        }

        model.addAttribute("accountRequestDecision", decision);
        model.addAttribute("request", request);
        model.addAttribute("headerText", messageSource.getMessage("sysadmin.sections.accountrequests", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminaccountrequest");
        return "sysAdminMain";
    }
}
