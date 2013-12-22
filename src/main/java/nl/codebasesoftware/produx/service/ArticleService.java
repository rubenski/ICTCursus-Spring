package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxSecurityException;
import nl.codebasesoftware.produx.formdata.AddArticleFormData;
import nl.codebasesoftware.produx.formdata.ArticlePageFormData;
import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.formdata.EditArticleFormData;

import java.util.List;

/**
 * User: rvanloen
 * Date: 4-3-13
 * Time: 23:26
 */
public interface ArticleService {
    List<ArticleEntityDTO> findByCompany(long companyId);

    Article addArticle(AddArticleFormData formData, long authorProfileId);

    ArticleEntityDTO findById(long id);

    List<ArticlePage> findPages(ArticleEntityDTO article);

    long saveArticlePage(ArticlePageFormData formData, long articleId);

    ArticlePage findPage(long pageId);

    void updateArticle(EditArticleFormData formData);

    void removePage(long pageId);

    List<ArticleEntityDTO> findByCategory(long categoryId);

    ArticleEntityDTO findFull(long id);

    void updateLogo(BindableFileUpload bindableFileUpload, long articleId, CompanyEntityDTO companyId) throws ProduxSecurityException;

    void removePicture(long articleId);
}
