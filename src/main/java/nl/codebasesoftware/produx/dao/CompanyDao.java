package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.Company;

/**
 * User: rvanloen
 * Date: 8-11-12
 * Time: 2:31
 */
public interface CompanyDao extends GenericDao<Company> {

    Company findByArticle(Article article);

}
