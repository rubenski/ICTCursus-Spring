package nl.codebasesoftware.produx.service.business;

import nl.codebasesoftware.produx.service.business.CourseUrl;
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
        Assert.assertEquals("/c/microsoft-office/15-dit-is-een-leuk-artikel-over-denk-ik-formule-1-zeg.html", url);
    }

    @Test
    public void testExtractId() throws Exception {
        long l = CourseUrl.extractId("25-he-dit-is-een-leuke-url.html");
        Assert.assertEquals(25L, l);
    }
}
