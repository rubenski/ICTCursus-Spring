package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.net.mail.BudgetWarningMailer;
import nl.codebasesoftware.produx.service.*;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 24-12-13
 * Time: 16:33
 */
@Service
public class BudgetWarningServiceImpl implements BudgetWarningService {

    private final LinkClickService linkClickService;
    private final CourseRequestService requestService;
    private BudgetWarningMailer budgetWarningMailer;
    private CompanyService companyService;

    @Autowired
    public BudgetWarningServiceImpl(LinkClickService linkClickService,
                                    CourseRequestService requestService, BudgetWarningMailer budgetWarningMailer, CompanyService companyService) {
        this.linkClickService = linkClickService;
        this.requestService = requestService;
        this.budgetWarningMailer = budgetWarningMailer;
        this.companyService = companyService;
    }

    @Override
    @Transactional
    public void checkBudgetAndSendWarningOrIgnore(CompanyEntityDTO company){

        Integer budgetTriggerAmount = company.getBudgetTriggerAmount();

        if (budgetTriggerAmount != null) {

            MonthAndYear currentMonth = new MonthAndYear();
            int totalInvoiceAmount = getTotalInvoiceAmount(company.getId(), currentMonth);
            Calendar now = Calendar.getInstance();

            // Send the warning mail if the trigger amount was exceeded and no mail was sent yet this month
            if (totalInvoiceAmount > budgetTriggerAmount && !currentMonth.dateInMonth(company.getBudgetWarningMailSent())) {
                try {
                    budgetWarningMailer.sendBudgetWarningMail(company, LocaleContextHolder.getLocale());
                    companyService.updateWarningMailSent(company, now);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private int getTotalInvoiceAmount(long companyId, MonthAndYear monthAndYear) {
        List<CourseRequestEntityDTO> requests = requestService.findForMonth(companyId, monthAndYear);
        List<ClickEntityDTO> clicks = linkClickService.findForMonth(companyId, monthAndYear);

        int total = 0;
        for (CourseRequestEntityDTO request : requests) {
            total += request.getInvoicePriceInCents();
        }

        for (ClickEntityDTO click : clicks) {
            total += click.getInvoicePriceInCents();
        }

        return total;
    }
}
