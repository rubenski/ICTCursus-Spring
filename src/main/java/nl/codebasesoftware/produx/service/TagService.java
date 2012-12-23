package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Tag;

import java.util.List;

/**
 * User: rvanloen
 * Date: 6-11-12
 * Time: 2:36
 */
public interface TagService {
    Tag findByName(String tagName);
    List<Tag> findBySubString(String tagName);
}


