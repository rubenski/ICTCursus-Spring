package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.ArticlePage;

import java.util.List;

/**
 * User: rvanloen
 * Date: 4-3-13
 * Time: 23:26
 */
public interface ArticleService {
    List<ArticlePage> findByCompany(long companyId);
}
