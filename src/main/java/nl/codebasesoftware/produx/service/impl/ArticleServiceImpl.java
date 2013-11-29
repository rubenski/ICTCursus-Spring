package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.*;
import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.AddArticleFormData;
import nl.codebasesoftware.produx.formdata.ArticlePageFormData;
import nl.codebasesoftware.produx.formdata.EditArticleFormData;
import nl.codebasesoftware.produx.service.ArticleService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.util.collection.EntityCollectionConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 7-4-13
 * Time: 21:46
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    Logger LOG = Logger.getLogger(ArticleServiceImpl.class);

    private ArticleDao articleDao;
    private UserProfileDao userProfileDao;
    private ArticlePageDao articlePageDao;
    private ArticleSuggestionDao articleSuggestionDao;
    private CategoryDao categoryDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, UserProfileDao userProfileDao,
                              ArticlePageDao articlePageDao, ArticleSuggestionDao articleSuggestionDao, CategoryDao categoryDao) {
        this.articleDao = articleDao;
        this.userProfileDao = userProfileDao;
        this.articlePageDao = articlePageDao;
        this.articleSuggestionDao = articleSuggestionDao;
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleEntityDTO> findByCompany(long companyId) {
        return new EntityCollectionConverter<Article, ArticleEntityDTO>().convert(articleDao.findByCompany(companyId));
    }

    @Override
    @Transactional(readOnly = false)
    public Article addArticle(AddArticleFormData formData, long authorProfileId) {

        // Get the author and tie it to the article later
        UserProfile author = userProfileDao.find(authorProfileId);
        // Get the suggestion on which this article is based so we can addOrUpdate the article to the suggestion later
        ArticleSuggestion suggestion = articleSuggestionDao.find(formData.getSuggestionId());
        // Get the category
        Category category = categoryDao.find(formData.getCategory());

        if (author == null) {
            throw new IllegalArgumentException("No author found for id");
        }

        Article article = new Article();
        article.setCreationDate(Calendar.getInstance());
        article.setAuthor(author);
        article.setTeaser(formData.getTeaser());
        article.setTitle(formData.getTitle());
        article.setPublished(false);
        article.setCategory(category);
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
    public long saveArticlePage(ArticlePageFormData formData, long articleId) {

        ArticlePage articlePage = null;
        Article article = articleDao.find(articleId);

        if (formData.getId() != null) {
            articlePage = articlePageDao.find(formData.getId());
        } else {
            articlePage = new ArticlePage();
            articlePage.setPosition(article.getNextPageNumber());
        }

        articlePage.setArticle(article);
        articlePage.setText(formData.getText());
        articlePage.setDescription(formData.getMetaDescription());
        articlePage.setKeywords(formData.getMetaKeywords());
        articlePage.setTitle(formData.getTitle());

        articlePageDao.persist(articlePage);

        return articlePage.getId();

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
        Category category = categoryDao.find(formData.getCategory());

        nullifyPagePositions(article);
        updatePagePositions(formData);

        article.setTeaser(formData.getTeaser());
        article.setTitle(formData.getTitle());
        article.setPublished(formData.isPublished());
        article.setCategory(category);
        article.setText(formData.getText());

        // If the article if published for the first time, set the 'first publication date'
        if (article.getFirstPublicationDate() == null && formData.isPublished()) {
            article.setFirstPublicationDate(Calendar.getInstance());
        }
    }

    private void updatePagePositions(EditArticleFormData formData) {
        if (formData.getPageOrder() != null) {
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
        }
    }

    private void nullifyPagePositions(Article article) {
        for (ArticlePage articlePage : article.getPages()) {
            articlePage.setPosition(null);
            articlePageDao.persist(articlePage);
        }
        articlePageDao.flush();
    }

    @Override
    @Transactional(readOnly = false)
    public void removePage(long pageId) {
        ArticlePage page = articlePageDao.find(pageId);
        articlePageDao.delete(page);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleEntityDTO> findByCategory(long catgeoryId) {
        List<Article> categories = articleDao.findByCategory(catgeoryId);
        List<ArticleEntityDTO> articleEntityDTOs = asArticleEntityDTOs(categories);
        return articleEntityDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleEntityDTO findFull(long id) {
        return articleDao.findFull(id).toDTO();
    }

    private List<ArticleEntityDTO> asArticleEntityDTOs(List<Article> articles) {
        List<ArticleEntityDTO> dtos = new ArrayList<>();
        for (Article article : articles) {
            dtos.add(article.toDTO());
        }
        return dtos;
    }
}
