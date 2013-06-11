package nl.codebasesoftware.produx.service.business;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 15:54
 */
public class CourseUrlTest {

    @Test
    public void testCreateUrl() throws Exception {
        String url = CourseUrl.createUrl(15L, "Microsoft Office", "Dit is een leuk artikel over - denk ik -, Formule 1, zeg!");
        Assert.assertEquals("/microsoft-office/c15-dit-is-een-leuk-artikel-over-denk-ik-formule-1-zeg.html", url);
    }


}
