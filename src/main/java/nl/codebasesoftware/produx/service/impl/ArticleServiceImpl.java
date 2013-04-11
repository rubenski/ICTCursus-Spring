package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.ArticleDao;
import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.AddArticleFormData;
import nl.codebasesoftware.produx.service.ArticleService;
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 7-4-13
 * Time: 21:46
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;
    private UserProfileService userProfileService;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, UserProfileService userProfileService) {
        this.articleDao = articleDao;
        this.userProfileService = userProfileService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> findByCompany(long companyId) {
        return articleDao.findByCompany(companyId);
    }

    @Override
    @Transactional(readOnly = false)
    public Article addArticle(AddArticleFormData formData, long authorProfileId) {
        UserProfile author = userProfileService.findById(authorProfileId);

        if (author == null) {
            throw new IllegalArgumentException("No author found for id");
        }

        Article article = new Article();
        article.setCreationDate(Calendar.getInstance());
        article.setAuthor(author);
        article.setTeaser(formData.getTeaser());
        article.setTitle(formData.getTitle());
        article.setPublished(false);
        articleDao.persist(article);
        return article;
    }

    @Override
    @Transactional(readOnly = true)
    public Article findById(long id) {
        return articleDao.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticlePage> findPages(Article article) {
        return articleDao.findPages(article);
    }
}
