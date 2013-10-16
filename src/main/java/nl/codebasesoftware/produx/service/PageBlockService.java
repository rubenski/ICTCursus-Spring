package nl.codebasesoftware.produx.service;

import org.springframework.ui.Model;

/**
 * User: rvanloen
 * Date: 3-5-13
 * Time: 20:43
 */
public interface PageBlockService {
    void setCourseCategoriesInLeftColumn(Model model);

    void setAuthentication(Model model);

    void setEmptyRightColumn(Model model);

    void setAboutIctCursusMenuInLeftColumn(Model model);

}
