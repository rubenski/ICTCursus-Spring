package nl.codebasesoftware.produx.domain.optionlists;

import nl.codebasesoftware.produx.domain.support.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 21-4-13
 * Time: 0:57
 */
@Component
public class ListOptions {

    private MessageSource messageSource;

    @Autowired
    public ListOptions(MessageSource messageSource){

        this.messageSource = messageSource;
    }

    public  List<Country> getCountries(Locale locale) {
        List<Country> countries = new ArrayList<Country>();
        countries.add(new Country("NL", messageSource.getMessage("countries.netherlands", new Object[]{}, locale)));
        countries.add(new Country("BE", messageSource.getMessage("countries.belgium", new Object[]{}, locale)));
        return countries;
    }
}
