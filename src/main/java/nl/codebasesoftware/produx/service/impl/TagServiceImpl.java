package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.TagDao;
import nl.codebasesoftware.produx.domain.Tag;
import nl.codebasesoftware.produx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:44
 */
@Service
public class TagServiceImpl implements TagService {


    private TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag findByName(String tagName) {
        return tagDao.findByName(tagName);
    }

    @Override
    public List<Tag> findBySubString(String tagName) {
        return tagDao.findBySubString(tagName);
    }
}
