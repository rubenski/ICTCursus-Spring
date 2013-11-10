package nl.codebasesoftware.produx.service.business.invoice;

/**
 * User: rvanloen
 * Date: 9-11-13
 * Time: 14:36
 */
public class MonthAndYear {

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
}
