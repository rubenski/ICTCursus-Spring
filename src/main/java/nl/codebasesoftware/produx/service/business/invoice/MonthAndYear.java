package nl.codebasesoftware.produx.service.business.invoice;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Calendar;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 9-11-13
 * Time: 14:36
 */
public class MonthAndYear {


    private final Locale locale = LocaleContextHolder.getLocale();
    private final int month;
    private final int year;

    public MonthAndYear(int month, int year){
        this.month = month;
        this.year = year;
        if (month > 12){
            throw new IllegalArgumentException("Month cannot be greater than 12");
        }

        if(year < 2012 || year > 2200){
            throw new IllegalArgumentException("Please provide a realistic year in yyyy format");
        }
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Calendar getFirstDayOfMonth(){
        Calendar firstDay = Calendar.getInstance(locale);
        firstDay.set(Calendar.YEAR, year);
        firstDay.set(Calendar.MONTH, month -1);
        firstDay.set(Calendar.DAY_OF_MONTH, firstDay.getActualMinimum(Calendar.DAY_OF_MONTH));
        return firstDay;
    }

    public Calendar getLastDayOfMonth(){
        Calendar lastDay = Calendar.getInstance(locale);
        lastDay.set(Calendar.YEAR, year);
        lastDay.set(Calendar.MONTH, month -1);
        lastDay.set(Calendar.DAY_OF_MONTH, lastDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        return lastDay;
    }
}
