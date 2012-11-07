package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.TagDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Tag;
import nl.codebasesoftware.produx.domain.support.FacetContent;
import nl.codebasesoftware.produx.service.FacetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 26-9-12
 * Time: 23:50
 */
@Service
public class FacetServiceImpl implements FacetService {

    private TagDao tagDao;

    @Autowired
    public FacetServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    @Transactional
    public FacetContent findFacetContent(Category category) {
        FacetContent facetContent = new FacetContent();
        facetContent.setCategories(category.getChildren());

        Set<Course> categoryCourses = category.getCourses();

        List<Tag> tags = tagDao.findTagsForCourses(categoryCourses);


        return null;


    }
}
