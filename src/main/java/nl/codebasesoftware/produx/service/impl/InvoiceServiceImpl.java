package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;
import nl.codebasesoftware.produx.service.CourseRequestService;
import nl.codebasesoftware.produx.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 27-10-13
 * Time: 12:45
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {


    private CourseRequestService courseRequestService;

    @Autowired
    public InvoiceServiceImpl(CourseRequestService courseRequestService) {
        this.courseRequestService = courseRequestService;
    }

    @Override
    public List<InvoiceEntityDTO> generateNewForMonth(int month){
        Locale locale = LocaleContextHolder.getLocale();

        Calendar firstDay = Calendar.getInstance(locale);
        firstDay.set(Calendar.MONTH, month -1);
        firstDay.set(Calendar.DAY_OF_MONTH, firstDay.getActualMinimum(Calendar.DAY_OF_MONTH));

        Calendar lastDay = Calendar.getInstance(locale);
        lastDay.set(Calendar.MONTH, month -1);
        lastDay.set(Calendar.DAY_OF_MONTH, lastDay.getActualMaximum(Calendar.DAY_OF_MONTH));


        List<CourseRequestEntityDTO> between = courseRequestService.findBetween(firstDay, lastDay);


    }
}
