package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CourseOptionDao;
import nl.codebasesoftware.produx.domain.OptionCategory;
import nl.codebasesoftware.produx.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-2-13
 * Time: 21:43
 */
@Service
public class OptionServiceImpl implements OptionService {

    private CourseOptionDao courseOptionDao;

    @Autowired
    public OptionServiceImpl(CourseOptionDao courseOptionDao) {
        this.courseOptionDao = courseOptionDao;
    }

    @Override
    public List<OptionCategory> findAllOptionCategories() {
        return courseOptionDao.getCategoriesWithOptions();
    }
}
