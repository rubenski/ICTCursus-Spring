package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.dao.CourseOptionDao;
import nl.codebasesoftware.produx.domain.CourseOption;
import nl.codebasesoftware.produx.domain.OptionCategory;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: rvanloen
 * Date: 12-8-12
 * Time: 15:17
 */
@Component
public class CourseFormValidator implements Validator {

    Logger LOG = Logger.getLogger(CourseFormValidator.class);
    private CourseOptionDao optionDao;

    @Autowired
    public CourseFormValidator(CourseOptionDao optionDao) {
        this.optionDao = optionDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BindableCourse.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BindableCourse bindableCourse = (BindableCourse) target;

        String regex = "[0-9]{1,5}(,[0-9]{2})?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(bindableCourse.getFormattedPrice());


        if(!StringUtil.isNullOrEmpty(bindableCourse.getLinkToSite())){
            if(!bindableCourse.getLinkToSite().startsWith("http://")){
                errors.rejectValue("linkToSite", "errors.linktosite.startwithhttp");
            }else {
                try {
                    URL url = new URL(bindableCourse.getLinkToSite());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(3000);
                    int responseCode = conn.getResponseCode();
                    conn.connect();
                    if(responseCode != 200){
                        errors.rejectValue("linkToSite", "errors.linktosite.notfound");
                    }
                } catch (MalformedURLException e) {
                    errors.rejectValue("linkToSite", "errors.linktosite.malformed");
                } catch (IOException e) {
                    errors.rejectValue("linkToSite", "errors.linktosite.notfound");
                }
            }
        }




        if (bindableCourse.getName().length() <= 3) {
            errors.rejectValue("name", "errors.name.invalid");
        }

        if (bindableCourse.getShortDescription().length() < 10) {
            errors.rejectValue("shortDescription", "errors.shortdescription.invalid");
        }

        if (bindableCourse.getLongDescription().length() < 20) {
            errors.rejectValue("longDescription", "errors.longdescription.invalid");
        }

        if (bindableCourse.getCategory() == -1) {
            errors.rejectValue("category", "errors.category.invalid");
        }

        if (bindableCourse.getDuration().length() < 5) {
            errors.rejectValue("duration", "errors.duration.invalid");
        }

        if (bindableCourse.getRegions() == null || bindableCourse.getRegions().size() == 0) {
            errors.rejectValue("regions", "errors.regions.invalid");
        }

        if (!matcher.matches()) {
            errors.rejectValue("formattedPrice", "errors.formattedPrice.invalid");
        }

        if (bindableCourse.getDuration().length() > 255) {
            errors.rejectValue("duration", "errors.duration.too.long");
        }

        if (bindableCourse.getTags().size() < 2) {
            errors.rejectValue("tags", "errors.no.tags");
        }

        List<OptionCategory> categoriesWithOptions = optionDao.getCategoriesWithOptions();

        boolean hasOptionError = false;
        if (bindableCourse.getOptions() != null) {
            for (OptionCategory categoryWithOptions : categoriesWithOptions) {
                boolean optionChecked = false;
                for (CourseOption courseOption : categoryWithOptions.getOptions()) {
                    if (bindableCourse.getOptions().contains(courseOption.getId())) {
                        optionChecked = true;
                        break;
                    }
                }
                if (!optionChecked) {
                    hasOptionError = true;
                    break;
                }
            }
        } else {
            hasOptionError = true;
        }


        if (hasOptionError) {
            errors.rejectValue("options", "errors.missing.option");
        }
    }
}
