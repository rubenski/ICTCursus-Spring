package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.ArticleDao;
import nl.codebasesoftware.produx.dao.ArticlePageDao;
import nl.codebasesoftware.produx.dao.ArticleSuggestionDao;
import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.AddArticleFormData;
import nl.codebasesoftware.produx.formdata.ArticlePageFormData;
import nl.codebasesoftware.produx.formdata.EditArticleFormData;
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
    private ArticlePageDao articlePageDao;
    private ArticleSuggestionDao articleSuggestionDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, UserProfileService userProfileService,
                              ArticlePageDao articlePageDao, ArticleSuggestionDao articleSuggestionDao) {
        this.articleDao = articleDao;
        this.userProfileService = userProfileService;
        this.articlePageDao = articlePageDao;
        this.articleSuggestionDao = articleSuggestionDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> findByCompany(long companyId) {
        return articleDao.findByCompany(companyId);
    }

    @Override
    @Transactional(readOnly = false)
    public Article addArticle(AddArticleFormData formData, long authorProfileId) {

        // Get the author and tie it to the article later
        UserProfile author = userProfileService.findById(authorProfileId);
        // Get the suggestion on which this article is based so we can add the article to the suggestion later
        ArticleSuggestion suggestion = articleSuggestionDao.find(formData.getSuggestionId());

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

        suggestion.setArticle(article);

        articleSuggestionDao.persist(suggestion);

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

    @Override
    @Transactional(readOnly = false)
    public void saveArticlePage(ArticlePageFormData formData, long articleId) {

        ArticlePage articlePage = null;
        Article article = articleDao.find(articleId);

        if (formData.getId() != null) {
            articlePage = articlePageDao.find(formData.getId());
            articlePage.setPosition(formData.getPosition());
        } else {
            articlePage = new ArticlePage();
            articlePage.setPosition(article.getNextPageNumber());
        }

        articlePage.setArticle(article);
        articlePage.setBody(formData.getText());
        articlePage.setDescription(formData.getMetaDescription());
        articlePage.setKeywords(formData.getMetaKeywords());
        articlePage.setTitle(formData.getTitle());

        articlePageDao.persist(articlePage);

    }


    @Override
    @Transactional(readOnly = true)
    public ArticlePage findPage(long pageId) {
        return articlePageDao.find(pageId);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateArticle(EditArticleFormData formData) {
        Article article = articleDao.find(formData.getId());

        nullifyPagePositions(article);

        for (String s : formData.getPageOrder()) {
            String[] elements = s.split("-");
            String position = elements[0];
            String pageId = elements[1];

            long i = Long.parseLong(pageId);
            int pos = Integer.parseInt(position);
            ArticlePage articlePage = articlePageDao.find(i);
            articlePage.setPosition(pos);
            articlePageDao.persist(articlePage);
        }

        article.setTeaser(formData.getTeaser());
        article.setTitle(formData.getTitle());
        article.setPublished(formData.isPublished());

        // If the article if published for the first time, set the 'first publication date'
        if(article.getFirstPublicationDate() == null && formData.isPublished()){
            article.setFirstPublicationDate(Calendar.getInstance());
        }
    }

    private void nullifyPagePositions(Article article){
        for (ArticlePage articlePage : article.getArticlePages()) {
            articlePage.setPosition(null);
            articlePageDao.persist(articlePage);
        }
        articlePageDao.flush();
    }

    @Override
    @Transactional(readOnly = false)
    public void removePage(Long pageId) {
        ArticlePage page = articlePageDao.find(pageId);
        articlePageDao.delete(page);
    }
}
