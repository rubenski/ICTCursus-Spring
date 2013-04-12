package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.formdata.AddArticleFormData;
import nl.codebasesoftware.produx.formdata.ArticlePageFormData;

import java.util.List;

/**
 * User: rvanloen
 * Date: 4-3-13
 * Time: 23:26
 */
public interface ArticleService {
    List<Article> findByCompany(long companyId);
    Article addArticle(AddArticleFormData formData, long authorProfileId);
    Article findById(long id);
    List<ArticlePage> findPages(Article article);
    void saveArticlePage(ArticlePageFormData formData, long articleId);
    ArticlePage findPage(long pageId);
}
