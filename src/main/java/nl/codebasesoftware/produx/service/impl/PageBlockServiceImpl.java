package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.PageBlockService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

/**
 * User: rvanloen
 * Date: 3-5-13
 * Time: 20:50
 */
@Service
public class PageBlockServiceImpl implements PageBlockService {

    private CourseService courseService;

    @Autowired
    public PageBlockServiceImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void setCourseCategoriesInLeftColumn(Model model) {
        setCategories(model);
        model.addAttribute("categoriesNav", true);
    }

    @Override
    public void setAuthentication(Model model) {
        UserProfile profile = CurrentUser.get();
        model.addAttribute("loggedIn", profile != null);
        model.addAttribute("currentUser", profile);
    }

    @Override
    public void setEmptyRightColumn(Model model) {
        model.addAttribute("rightColumn", "components/empty");
    }

    @Transactional(readOnly = true)
    private void setCategories(Model model){
        List<Category> firstLevelCategories = courseService.findFirstLevelCategories();
        model.addAttribute("categories", firstLevelCategories);
    }
}
